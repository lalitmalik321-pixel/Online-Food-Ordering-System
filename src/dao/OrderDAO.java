package dao;
import java.sql.ResultSet;
import database.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO {

    public void placeOrder(Order order) {

        try {

            Connection con = DBConnection.getConnection();

           String sql = "INSERT INTO orders(user_id, total) VALUES(?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Order Placed Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewOrders() {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM orders";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n========== ORDER HISTORY ==========");

        while (rs.next()) {

            System.out.println(
                    "Order ID: " + rs.getInt("order_id") +
                    " | User ID: " + rs.getInt("user_id") +
                    " | Total: ₹" + rs.getDouble("total") +
                    " | Date: " + rs.getTimestamp("order_date")
            );
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}