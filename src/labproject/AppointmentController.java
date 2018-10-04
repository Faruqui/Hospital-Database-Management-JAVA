/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class AppointmentController extends NewFXMain implements Initializable {

    @FXML
    private TextField pID;
    @FXML
    private TextField dID;
    @FXML
    private TextArea info;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private DatePicker dateP;

    private String date1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\appointment.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);
    }

    @FXML
    private void setAppointment(ActionEvent event) {
        int p = Integer.parseInt(pID.getText());
        int d = Integer.parseInt(dID.getText());

        String s1 = null;
        try {
            Patient reqP = getPatientFromDbID(p);
            //info.setText("An appointmnet for patient " +reqP.getName()+" (" +reqP.getId()+") has been set with ");
            s1 = "Appointment has been set for : \n\nPatient: " + reqP.getName() + " (ID: " + reqP.getId() + ")\nHealth problem: " + reqP.getDisease() + "\n";
        } catch (Exception e) {
            info.setText("No patient or doctor with your provided ID \nexist in the database\n\nPlease try Again");
        }

        try {
            Doctor reqD = getDocFromDbID(d);
            s1 = s1.concat("Doctor: " + reqD.getName() + " (" + reqD.getSpecialization() + ") \nDate: " + date1);
            info.setText(s1);
        } catch (Exception e) {
            info.setText("No patient or doctor with your provided ID \nexist in the database\n\nPlease try Again");
        }

    }

    @FXML
    private void gotoHome(ActionEvent event
    ) {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("NewHomePage.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void setDate(ActionEvent event) {
        try {
            this.date1 = dateP.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println("Date set to: " + date1);
        } catch (Exception e) {
            System.out.println("Date error");
        }
    }
}
