package View;

import Helper.Config;
import Helper.Helper;
import Model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_logut;
    private JTextField fld_sh_hotelName;
    private JTextField fld_sh_hotelCity;
    private JTextField fld_sh_hotelRegion;
    private JComboBox<String> cmb_sh_hotelStars;
    private JButton btn_search;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_address;
    private JButton btn_add_hotel;
    private JTextField fld_hotelID;
    private JButton btn_delete_hotel;
    private JTable tbl_employee;
    private JTextField fld_hotel_phone;
    private JComboBox<Integer> cmb_hotel_stars;
    private JButton btn_tesisOzellikler;
    private JComboBox<String> cmb_pTipleri;
    private DefaultTableModel mdl_hotelList;
    private Object[] row_hotelList;

    public EmployeeGUI(){

        setContentPane(wrapper);
        setSize(1000, 650);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mdl_hotelList = new DefaultTableModel();
        Object[] col_hotelList = {"ID", "İsim", "Şehir", "Bölge", "Adres", "Email", "Telefon", "Yıldız", "Pansiyon Türleri", "Özellikler"};
        mdl_hotelList.setColumnIdentifiers(col_hotelList);
        tbl_employee.setModel(mdl_hotelList);
        row_hotelList = new Object[col_hotelList.length];

        loadHotelModel();

        btn_logut.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        btn_add_hotel.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) ||
                    Helper.isFieldEmpty(fld_hotel_region) || Helper.isFieldEmpty(fld_hotel_address) ||
                    Helper.isFieldEmpty(fld_hotel_phone) || cmb_hotel_stars.getSelectedItem() == null) {
                Helper.showMsg("fill");
            } else {
                String name = fld_hotel_name.getText();
                String city = fld_hotel_city.getText();
                String region  = fld_hotel_region.getText();
                String address = fld_hotel_address.getText();
                String email = "";
                String phone = fld_hotel_phone.getText();
                int starsRating = cmb_hotel_stars.getItemAt(cmb_hotel_stars.getSelectedIndex());
                String pensionTypes = "";
                String features = "";

                if (Hotel.add(name, city, region, address, email, phone, starsRating, pensionTypes, features)) {
                    Helper.showMsg("done");
                    loadHotelModel();
                    clearHotelFields();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_tesisOzellikler.addActionListener(e -> {
            new TesisÖzelliklerGUI();
        });

        btn_search.addActionListener(e -> {
            String hotelName = fld_sh_hotelName.getText();
            String hotelCity = fld_sh_hotelCity.getText();
            String hotelRegion = fld_sh_hotelRegion.getText();
            String selectedStarsStr = cmb_sh_hotelStars.getSelectedItem().toString();

            int starsRating = -1; // Default value, to retrieve all hotels regardless of stars

            try {
                if (!selectedStarsStr.isEmpty()) {
                    starsRating = Integer.parseInt(selectedStarsStr);
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace(); // For debugging purposes
                Helper.showMsg("Invalid star rating");
                return;
            }

            ArrayList<Hotel> searchedHotels = Hotel.searchHotels(hotelName, hotelCity, hotelRegion, starsRating);

            DefaultTableModel model = (DefaultTableModel) tbl_employee.getModel();
            model.setRowCount(0);

            for (Hotel hotel : searchedHotels) {
                Object[] row = new Object[10];
                row[0] = hotel.getId();
                row[1] = hotel.getName();
                row[2] = hotel.getCity();
                row[3] = hotel.getRegion();
                row[4] = hotel.getAddress();
                row[5] = hotel.getEmail();
                row[6] = hotel.getPhone();
                row[7] = hotel.getStarsRating();
                row[8] = hotel.getPension_types();
                row[9] = hotel.getFeatures();
                model.addRow(row);
            }

            tbl_employee.setModel(model);
        });
        tbl_employee.setDefaultEditor(Object.class, null);
    }



    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_employee.getModel();
        clearModel.setRowCount(0);

        ArrayList<Hotel> hotels = Hotel.getList();

        for (Hotel hotel : hotels) {
            Object[] row = new Object[10];
            row[0] = hotel.getId();
            row[1] = hotel.getName();
            row[2] = hotel.getCity();
            row[3] = hotel.getRegion();
            row[4] = hotel.getAddress();
            row[5] = hotel.getEmail();
            row[6] = hotel.getPhone();
            row[7] = hotel.getStarsRating();
            row[8] = hotel.getPension_types();
            row[9] = hotel.getFeatures();
            clearModel.addRow(row);
        }


        tbl_employee.setModel(clearModel);
    }

    private void clearHotelFields() {
        fld_hotel_name.setText("");
        fld_hotel_city.setText("");
        fld_hotel_region.setText("");
        fld_hotel_address.setText("");
        fld_hotel_phone.setText("");
        cmb_hotel_stars.setSelectedIndex(0); // or any desired index
    }

    public static void main(String[] args) {
        EmployeeGUI employeeGUI = new EmployeeGUI();
    }
}
