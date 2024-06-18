package Model;

import Helper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public Admin() {
        super();
    }


    public Admin(int id, String username, String password, String firstName, String lastName, String role) {
        super(id, username, password, firstName, lastName, role);
    }

    public static boolean add(String name, String username, String password, String type) {
        String sqlQuery = "INSERT INTO users (name, username, password, type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(username);
        if (findUser != null) {
            Helper.showMsg("Bu kullanıcı adı kullanılıyor.Başka bir kullanıcı adı giriniz.");
            return false;
        }
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, type);
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






}
