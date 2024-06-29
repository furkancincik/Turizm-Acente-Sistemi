package View;

import Helper.Config;
import Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TesisÖzelliklerGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox ücretsizOtoparkCheckBox;
    private JCheckBox ücretsizWiFiCheckBox;
    private JButton OKButton;

    public TesisÖzelliklerGUI(){
        setContentPane(wrapper);
        setSize(250, 300);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        OKButton.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        TesisÖzelliklerGUI tesisÖzelliklerGUI = new TesisÖzelliklerGUI();
    }

}
