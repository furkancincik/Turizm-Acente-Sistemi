package Model;

import Helper.DBConnector;

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

    public int getId(int id) {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getUsername(String username) {
        return username;
    }

    public void setUsername() {
        this.username = username;
    }

    public String getPassword(String password) {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public String getFirstName(String firstName) {
        return firstName;
    }

    public void setFirstName() {
        this.firstName = firstName;
    }

    public String getLastName(String lastName) {
        return lastName;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public String getRole(String role) {
        return role;
    }

    public void setRole() {
        this.role = role;
    }


    public static boolean addUser(String name, String username, String password, String firstName, String lastName, String role) throws SQLException {
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




}
