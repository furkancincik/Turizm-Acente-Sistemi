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
    private JComboBox cmb_sh_hotelStars;
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
    private JComboBox cmb_hotel_stars;

    public EmployeeGUI(){

        setContentPane(wrapper);
        setSize(1000, 550);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                String email = ""; // Burada email alanı için gerekli işlemleri ekleyebilirsiniz.
                String phone = fld_hotel_phone.getText();
                int starsRating = Integer.parseInt(cmb_hotel_stars.getSelectedItem().toString());
                String pensionTypes = ""; // Burada pension types alanı için gerekli işlemleri ekleyebilirsiniz.
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
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_employee.getModel();
        clearModel.setRowCount(0);

        // Otellerin listesini al
        ArrayList<Hotel> hotels = Hotel.getList();

        // Tabloya otel bilgilerini ekle
        for (Hotel hotel : hotels) {
            Object[] row = new Object[9];
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

        // Tabloyu güncelle
        tbl_employee.setModel(clearModel);
    }

    private void clearHotelFields() {
        fld_hotel_name.setText("");
        fld_hotel_city.setText("");
        fld_hotel_region.setText("");
        fld_hotel_address.setText("");
        fld_hotel_phone.setText("");
        cmb_hotel_stars.setSelectedIndex(0); // veya istediğiniz bir index
    }

    public static void main(String[] args) {
        EmployeeGUI employeeGUI = new EmployeeGUI();
    }
}
