package View;

import javax.swing.*;
import Helper.*;
import Model.User;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton girişButton;

    public LoginGUI(){
        setContentPane(wrapper);
        setSize(350, 340);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        girişButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_pass)){
                Helper.showMsg("fill");
            }else {
                User user = User.getFetch(fld_username.getText(), new String(fld_pass.getText()));
                if (user == null){
                    Helper.showMsg("Kullanıcı adı bulunamadı.");
                }else {
                    switch (user.getRole()){
                        case "admin":
                            AdminGUI adminGUI = new AdminGUI();
                            break;
                        case "employee":
                            EmployeeGUI employeeGUI = new EmployeeGUI();
                            break;
                        default:
                            Helper.showMsg("Bilinmeyen rol!");
                            break;
                    }
                    dispose();
                }
            }
        });


    }


    public static void main(String[] args) {
        LoginGUI l1 = new LoginGUI();
    }
}
