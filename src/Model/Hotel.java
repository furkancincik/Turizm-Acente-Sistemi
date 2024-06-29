package Model;

import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String region;
    private String address;
    private String email;
    private String phone;
    private int starsRating;
    private String pension_types;
    private String features;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStarsRating() {
        return starsRating;
    }

    public void setStarsRating(int starsRating) {
        this.starsRating = starsRating;
    }

    public String getPension_types() {
        return pension_types;
    }

    public void setPension_types(String pension_types) {
        this.pension_types = pension_types;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }


    public static boolean add(String name, String city, String region, String address, String email, String phone, int starsRating, String pension_types, String features) {
        String query = "INSERT INTO hotels (name, city, region, address, email, phone, stars, pension_types, features) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,address);
            pr.setString(5,email);
            pr.setString(6,phone);
            pr.setInt(7,starsRating);
            pr.setString(8,pension_types);
            pr.setString(9,features);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public static ArrayList<Hotel> getFetch(String query) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try (Statement st = DBConnector.getInstance().createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAddress(rs.getString("address"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setStarsRating(rs.getInt("stars"));
                hotel.setPension_types(rs.getString("pension_types"));
                hotel.setFeatures(rs.getString("features"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public static ArrayList<Hotel> getList() {
        String query = "SELECT * FROM hotels";
        return getFetch(query);
    }

    public static PreparedStatement searchQuery(String name, String city, String region) {
        String sqlQuery = "SELECT * FROM hotels WHERE name LIKE ? AND city LIKE ? AND region LIKE ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, "%" + name + "%");
            pst.setString(2, "%" + city + "%");
            pst.setString(3, "%" + region + "%");
            return pst;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Hotel> searchHotelList(PreparedStatement pst) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAddress(rs.getString("address"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setStarsRating(rs.getInt("stars"));
                hotel.setPension_types(rs.getString("pension_types"));
                hotel.setFeatures(rs.getString("features"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }


    public static ArrayList<Hotel> searchHotels(String hotelName, String hotelCity, String hotelRegion, int starsRating) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM hotels WHERE name LIKE ? AND city LIKE ? AND region LIKE ? AND stars = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, "%" + hotelName + "%");
            pst.setString(2, "%" + hotelCity + "%");
            pst.setString(3, "%" + hotelRegion + "%");
            pst.setInt(4, starsRating);
            rs = pst.executeQuery();

            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setRegion(rs.getString("region"));
                hotel.setAddress(rs.getString("address"));
                hotel.setEmail(rs.getString("email"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setStarsRating(rs.getInt("stars"));
                hotel.setPension_types(rs.getString("pension_types"));
                hotel.setFeatures(rs.getString("features"));
                hotelList.add(hotel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hotelList;
    }


    public static PreparedStatement searchQuery(String hotelName, String hotelCity, String hotelRegion, int starsRating) {
        String sqlQuery = "SELECT * FROM hotels WHERE name LIKE ? AND city LIKE ? AND region LIKE ? AND stars = ?";
        PreparedStatement pst = null;

        try {
            pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setString(1, "%" + hotelName + "%");
            pst.setString(2, "%" + hotelCity + "%");
            pst.setString(3, "%" + hotelRegion + "%");
            pst.setInt(4, starsRating);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pst;
    }
}
