package Model;

import Helper.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public Admin(int id, String username, String password, String firstName, String lastName, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public static boolean addUser(String username, String password, String firstName, String lastName, String role) throws SQLException {
        String query = "INSERT INTO users (username, password, first_name, last_name, role) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, firstName);
            pr.setString(4, lastName);
            pr.setString(5, role);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
