package Model;

import Helper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(int id, String username, String password, String firstName, String lastName, String role) {
        super(id, username, password, firstName, lastName, role);
    }

    public static boolean add(String username, String password, String firstName, String lastName, String role) {
        String sqlQuery = "INSERT INTO users (username, password, first_name, last_name, role) VALUES (?,?,?,?,?)";
        User findUser = User.getFetch(username);
        if (findUser != null) {
            Helper.showMsg("Bu kullanıcı adı kullanılıyor.Başka bir kullanıcı adı giriniz.");
            return false;
        }
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3,firstName);
            pst.setString(4,lastName);
            pst.setString(5, role);
            int result = pst.executeUpdate();

            if (result == -1) {
                Helper.showMsg("error");
            }
            return result != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    public static boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser(int id, String firstName, String lastName, String username, String password, String role) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, firstName);
            pr.setString(2, lastName);
            pr.setString(3, username);
            pr.setString(4, password);
            pr.setString(5, role);
            pr.setInt(6, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





}
