package View;

import Helper.*;

import javax.swing.*;

public class AdminGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_logut;
    private JTable tbl_employee;
    private JTextField fld_firstName;
    private JTextField fld_lastName;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_rol;
    private JButton EKLEButton;
    private JTextField fld_id;
    private JButton SİLButton;
    private JTextField textField1;
    private JTextField fld_sh_lastName;
    private JComboBox cmb_sh_rol;
    private JTextField fld_sh_username;


    public AdminGUI(){
        setContentPane(wrapper);
        setSize(800, 600);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



    public static void main(String[] args) {
        AdminGUI adminGUI = new AdminGUI();
    }

}
