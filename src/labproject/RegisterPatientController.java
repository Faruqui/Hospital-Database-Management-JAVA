/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RegisterPatientController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField phone;
    @FXML
    private TextField disease;
    @FXML
    private ProgressBar pbar;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;
    @FXML
    private DatePicker datePick;

    private String date1;
    String gender1;

    @FXML
    private ToggleGroup gendergrp;
    @FXML
    private Label error;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\adduser.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);
    }

    @FXML
    private void save(ActionEvent event) {
        pbar.setProgress(0.0);
        int id1 = Integer.parseInt(id.getText());
        int age1 = Integer.parseInt(age.getText());
        String name1 = name.getText();
        //String gender1 = gender.getText();
        String disease1 = disease.getText();
        //String date1 = date.getText();
        int phone1 = Integer.parseInt(phone.getText());

        pbar.setProgress(.2);

        try {

            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            //stmt.executeUpdate("CREATE TABLE PATIENTS (ID INT PRIMARY KEY, NAME VARCHAR(40), AGE INT, GENDER VARCHAR(12),  PHONE INT, DISEASE VARCHAR(40), DATE VARCHAR(20))");
            //System.out.println("Created Table PATIENTS");
            String sql = "INSERT INTO PATIENTS VALUES (" + id1 + ",'" + name1 + "', " + age1 + ", '" + gender1 + "', " + phone1 + ", '" + disease1 + "', '" + date1 + "')";

            stmt.executeUpdate(sql);
            System.out.println("Populated Table");
            msg.setText("Patient registraion successfull...");
            error.setText("  ");
            id.clear();
            name.clear();
            age.clear();
            phone.clear();
            disease.clear();
            pbar.setProgress(.2);
        } catch (Exception exc) {
            error.setText("ID: " + id1 + " is not availabe!!");
            exc.printStackTrace();
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

    @FXML
    private void setDate(ActionEvent event) {
        try {
            this.date1 = datePick.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println("Date set to: " + date1);
            pbar.setProgress(.2);
        } catch (Exception e) {
            System.out.println("Date error");
        }
    }

    @FXML
    private void setGenderMale(ActionEvent event) {
        gender1 = "Male";
        try {
            File file = new File("images\\regmale.png");
            Image ss = new Image(file.toURI().toString());
            image.setImage(ss);
            pbar.setProgress(.65);
        } catch (Exception e) {

        }

    }

    @FXML
    private void setGenderFemale(ActionEvent event) {
        gender1 = "Female";
        try {
            File file = new File("images\\regfem.png");
            Image ss = new Image(file.toURI().toString());
            image.setImage(ss);
            pbar.setProgress(.65);
        } catch (Exception e) {

        }

    }

    @FXML
    private void setPro1(KeyEvent event) {
        pbar.setProgress(.1);
    }

    @FXML
    private void setPro2(KeyEvent event) {
        pbar.setProgress(.35);
    }

    @FXML
    private void setPro3(KeyEvent event) {
        pbar.setProgress(.5);
    }

    @FXML
    private void setPro4(KeyEvent event) {
        pbar.setProgress(.8);
    }

    @FXML
    private void setPro5(KeyEvent event) {
        pbar.setProgress(1);
    }
}
