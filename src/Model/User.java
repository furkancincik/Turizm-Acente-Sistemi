package Model;

import Helper.DBConnector;
import View.AdminGUI;

import java.sql.*;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String firsName;
    private String lastName;
    private String role;


    public User(){

    }

    public User(int id, String username, String password, String firsName, String lastName, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firsName = firsName;
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

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
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

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users;";
        User obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setFirsName(rs.getString("first_name"));
                obj.setLastName(rs.getString("last_name"));
                obj.setRole(rs.getString("role"));
                userList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static ArrayList<User> searchUser(String username, String firstName, String lastName) {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users WHERE username LIKE ? AND first_name LIKE ? AND last_name LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(query);
            pst.setString(1, "%" + username + "%");
            pst.setString(2, "%" + firstName + "%");
            pst.setString(3, "%" + lastName + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setFirsName(rs.getString("first_name"));
                obj.setLastName(rs.getString("last_name"));
                obj.setRole(rs.getString("role"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public static User getFetch(String username) {
        User obj = null;
        String sqlQuery = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setFirsName(rs.getString("first_name"));
                obj.setLastName(rs.getString("last_name"));
                obj.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static User getFetch(String username, String password){
        User obj = null;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                String userRole = rs.getString("role");
                if ("admin".equals(userRole)){
                    obj = new Admin();
                }else {
                    obj = new Employee();
                }
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setFirsName(rs.getString("first_name"));
                obj.setLastName(rs.getString("last_name"));
                obj.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }



}
