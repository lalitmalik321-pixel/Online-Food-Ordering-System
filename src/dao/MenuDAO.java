package dao;

import database.DBConnection;
import java.sql.Connection;
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
}