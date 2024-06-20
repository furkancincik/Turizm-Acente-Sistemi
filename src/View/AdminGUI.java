package View;

import Helper.*;
import Model.Admin;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_logut;
    private JTable tbl_employee;
    private JTextField fld_firstName;
    private JTextField fld_lastName;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_rol;
    private JButton btn_add_user;
    private JTextField fld_id;
    private JButton btn_delete_user;
    private JTextField fld_sh_firstName;
    private JTextField fld_sh_lastName;
    private JComboBox cmb_sh_role;
    private JTextField fld_sh_username;
    private JButton btn_search;
    private JLabel lbl_welcome;


    public AdminGUI() {

        setContentPane(wrapper);
        setSize(1100, 600);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadEmployeeModel();

        tbl_employee.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "First Name", "Last Name", "Username", "Password", "Role"}
        ));

        tbl_employee.setDefaultEditor(Object.class, null);
        loadEmployeeModel();


        btn_logut.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });


        btn_add_user.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_firstName) || Helper.isFieldEmpty(fld_lastName) ||
                    Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            } else {
                String firstName = fld_firstName.getText();
                String lastName = fld_lastName.getText();
                String username = fld_username.getText();
                String password = fld_password.getText();
                String role = cmb_rol.getSelectedItem().toString();

                if (Admin.add(username, password, firstName, lastName, role)) {
                    Helper.showMsg("done");
                    loadEmployeeModel();
                } else {
                    Helper.showMsg("error");
                }
            }
        });


        btn_delete_user.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int id = Integer.parseInt(fld_id.getText());
                    if (Admin.deleteUser(id)) {
                        Helper.showMsg("done");
                        loadEmployeeModel();
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_search.addActionListener(e -> {
            String firstName = fld_sh_firstName.getText();
            String lastName = fld_sh_lastName.getText();
            String username = fld_sh_username.getText();
            String role = cmb_sh_role.getSelectedItem().toString();
            String pst = User.searchQuery(firstName, lastName, username, role).toString();
            ArrayList<User> searchingUser = User.searchUserList(pst);

            DefaultTableModel clearModel = (DefaultTableModel) tbl_employee.getModel();
            clearModel.setRowCount(0);

            for (User obj : searchingUser) {
                Object[] row = new Object[6];
                row[0] = obj.getId();
                row[1] = obj.getFirstName();
                row[2] = obj.getLastName();
                row[3] = obj.getUsername();
                row[4] = obj.getPassword();
                row[5] = obj.getRole();
                clearModel.addRow(row);
            }
            tbl_employee.setModel(clearModel);
        });

        tbl_employee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl_employee.rowAtPoint(e.getPoint());
                if (row != -1) {
                    String selectedUserId = tbl_employee.getValueAt(row, 0).toString();
                    fld_id.setText(selectedUserId);
                }
            }
        });

        tbl_employee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl_employee.rowAtPoint(e.getPoint());
                if (row != -1) {
                    String selectedUserId = tbl_employee.getValueAt(row, 0).toString();
                    fld_id.setText(selectedUserId);
                }
            }
        });

    }


    private void loadEmployeeModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_employee.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : User.getList()) {
            i = 0;
            Object[] row = new Object[6];
            row[i++] = obj.getId();
            row[i++] = obj.getFirstName();
            row[i++] = obj.getLastName();
            row[i++] = obj.getUsername();
            row[i++] = obj.getPassword();
            row[i++] = obj.getRole();
            clearModel.addRow(row);
        }
    }


    public static void main(String[] args) {
        AdminGUI adminGUI = new AdminGUI();
    }

}
