package dao;

import database.DBConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public void registerUser(User user) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ User Registered Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String email, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Login Successful!");
                return true;
            }

            System.out.println("❌ Invalid Email or Password!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}