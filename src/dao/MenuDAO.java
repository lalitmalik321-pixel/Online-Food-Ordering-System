package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class MenuDAO {

    public void displayMenu() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM menu";

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n========== MENU ==========");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("food_name") + " | ₹" +
                        rs.getDouble("price") + " | " +
                        rs.getString("category")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double getPriceByFoodId(int foodId) {

    double price = 0;

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT price FROM menu WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, foodId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            price = rs.getDouble("price");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return price;
}
public String getFoodNameById(int foodId) {

    String foodName = "";

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT food_name FROM menu WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, foodId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            foodName = rs.getString("food_name");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return foodName;
}
}