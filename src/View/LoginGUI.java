package View;

import javax.swing.*;
import Helper.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        });


    }


    public static void main(String[] args) {
        LoginGUI l1 = new LoginGUI();
    }
}
