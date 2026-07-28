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

        // Insert into orders table
        String orderSql = "INSERT INTO orders(user_id, total) VALUES(?, ?)";

        PreparedStatement orderPs = con.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS);

        orderPs.setInt(1, order.getUserId());
        orderPs.setDouble(2, order.getTotalAmount());

        int rows = orderPs.executeUpdate();

        if (rows > 0) {

            ResultSet generatedKeys = orderPs.getGeneratedKeys();

            if (generatedKeys.next()) {

                int orderId = generatedKeys.getInt(1);

                // Insert into order_items table
                String itemSql = "INSERT INTO order_items(order_id, food_id, quantity, price) VALUES(?, ?, ?, ?)";

                PreparedStatement itemPs = con.prepareStatement(itemSql);

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, order.getFoodId());
                itemPs.setInt(3, order.getQuantity());
                itemPs.setDouble(4, order.getTotalAmount());

                itemPs.executeUpdate();
System.out.println("\n==============================");
System.out.println("      ONLINE FOOD ORDER");
System.out.println("==============================");
System.out.println("User ID      : " + order.getUserId());
System.out.println("Food ID      : " + order.getFoodId());
System.out.println("Quantity     : " + order.getQuantity());
System.out.println("Total Bill   : ₹" + order.getTotalAmount());
System.out.println("==============================");
System.out.println("✅ Order Placed Successfully!");
System.out.println("Thank You for Ordering!");
            }
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void viewOrders(int userId) {

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT o.order_id, m.food_name, oi.quantity, oi.price, o.total, o.order_date " +
                "FROM orders o " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN menu m ON oi.food_id = m.id " +
                "WHERE o.user_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n========== MY ORDERS ==========");

        boolean found = false;

        while (rs.next()) {

            found = true;

            System.out.println("------------------------------------");
            System.out.println("Order ID   : " + rs.getInt("order_id"));
            System.out.println("Food       : " + rs.getString("food_name"));
            System.out.println("Quantity   : " + rs.getInt("quantity"));
            System.out.println("Price      : ₹" + rs.getDouble("price"));
            System.out.println("Total      : ₹" + rs.getDouble("total"));
            System.out.println("Date       : " + rs.getTimestamp("order_date"));
        }

        if (!found) {
            System.out.println("No Orders Found!");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}