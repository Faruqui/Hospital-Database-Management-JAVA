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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class PatientSearchController implements Initializable {

    @FXML
    private TextArea info;
    private TextField nameP;
    @FXML
    private TextField idP;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\findpat_1.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);

    }

    @FXML
    private void search(ActionEvent event) {
        int n = Integer.parseInt(idP.getText());

        try {
            Patient req = getPatientFromDbID(n);
            info.setText(req.getInfo());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            info.setText("No patient with ID: " + n + " exist in the database\n\nPlease try Again");
        }

        idP.clear();
    }

    public Patient getPatientFromDbID(int n) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            ResultSet myPat = stmt.executeQuery("select * from patients WHERE ID=" + n);
            myPat.next();

            String name = myPat.getString("Name");
            int id = myPat.getInt("ID");
            long phone = myPat.getLong("Phone");
            String gender = myPat.getString("Gender");
            int age = myPat.getInt("Age");
            String date = myPat.getString("Date");
            String disease = myPat.getString("Disease");

            Patient req = new Patient(id, name, phone, gender, age, date, disease);
            return req;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Patient getPatientFromDbNAME(String s) {
        try {
            s = s.toUpperCase();
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            ResultSet myPat = stmt.executeQuery("select * from patients WHERE upper(NAME)='" + s + "'");
            myPat.next();

            String name = myPat.getString("Name");
            int id = myPat.getInt("ID");
            long phone = myPat.getLong("Phone");
            String gender = myPat.getString("Gender");
            int age = myPat.getInt("Age");
            String date = myPat.getString("Date");
            String disease = myPat.getString("Disease");

            Patient req = new Patient(id, name, phone, gender, age, date, disease);
            return req;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @FXML
    private void goToHome(ActionEvent event) {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("NewHomePage.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
