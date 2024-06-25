package View;

import Helper.Config;
import Helper.Helper;

import javax.swing.*;

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
    private JButton btn_add_user;
    private JTextField fld_id;
    private JButton btn_delete_user;
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
    }


    public static void main(String[] args) {
        EmployeeGUI employeeGUI = new EmployeeGUI();
    }
}
