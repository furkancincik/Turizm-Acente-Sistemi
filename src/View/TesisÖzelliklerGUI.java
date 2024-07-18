package View;

import Helper.Config;
import Helper.DBConnector;
import Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesisÖzelliklerGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox ücretsizOtoparkCheckBox;
    private JCheckBox ücretsizWiFiCheckBox;
    private JButton OKButton;

    private boolean isFreeParkingSelected;
    private boolean isFreeWiFiSelected;

    public TesisÖzelliklerGUI() {
        setContentPane(wrapper);
        setSize(250, 300);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(true);
        setLocation(Helper.screenLoc("x", getSize()), Helper.screenLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        fetchHotelFeatures();

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFreeParkingSelected = ücretsizOtoparkCheckBox.isSelected();
                isFreeWiFiSelected = ücretsizWiFiCheckBox.isSelected();
                addOrUpdateHotelFeatures(isFreeParkingSelected, isFreeWiFiSelected);
                OKButton.setText("Seçim yapıldı");
                dispose();
            }
        });
    }

    private void fetchHotelFeatures() {
        String sqlQuery = "SELECT features FROM hotels WHERE id = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);
            pst.setInt(1, 1); // Burada 1 yerine gerçek hotelID değerini kullanmalısınız

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String features = rs.getString("features");
                if (features != null) {
                    if (features.contains("Ücretsiz Otopark")) {
                        ücretsizOtoparkCheckBox.setSelected(true);
                    } else {
                        ücretsizOtoparkCheckBox.setSelected(false);
                    }
                    if (features.contains("Ücretsiz WiFi")) {
                        ücretsizWiFiCheckBox.setSelected(true);
                    } else {
                        ücretsizWiFiCheckBox.setSelected(false);
                    }
                } else {
                    ücretsizOtoparkCheckBox.setSelected(false);
                    ücretsizWiFiCheckBox.setSelected(false);
                }
            }

            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addOrUpdateHotelFeatures(boolean freeParking, boolean freeWiFi) {
        String sqlQuery = "UPDATE hotels SET features = ? WHERE id = ?";
        try {
            PreparedStatement pst = DBConnector.getInstance().prepareStatement(sqlQuery);

            String features = "";
            if (freeParking) {
                features += "Ücretsiz Otopark, ";
            }
            if (freeWiFi) {
                features += "Ücretsiz WiFi, ";
            }

            if (!features.isEmpty()) {
                features = features.substring(0, features.length() - 2);  
            }

            pst.setString(1, features);
            pst.setInt(2, 1);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                Helper.showMsg("Özellikler başarıyla eklendi veya güncellendi.");
            } else {
                Helper.showMsg("Özellikler eklenirken veya güncellenirken bir hata oluştu.");
            }

            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TesisÖzelliklerGUI tesisÖzelliklerGUI = new TesisÖzelliklerGUI();
    }
}
