/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class NewHomePageController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView mysql;
    @FXML
    private ImageView docRec;
    @FXML
    private ImageView setApp;
    @FXML
    private ImageView patReg;
    @FXML
    private ImageView docReg;
    @FXML
    private ImageView patSearch;
    @FXML
    private ImageView docSearch;
    @FXML
    private Button databaseButton;
    @FXML
    private ImageView logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File file4 = new File("images\\database.png");
        Image ss3 = new Image(file4.toURI().toString());
        mysql.setImage(ss3);

        File file5 = new File("images\\setApp.png");
        setApp.setImage(new Image(file5.toURI().toString()));

        File file6 = new File("images\\recDoc.png");
        docRec.setImage(new Image(file6.toURI().toString()));

        File file7 = new File("images\\adduser.png");
        patReg.setImage(new Image(file7.toURI().toString()));

        File file8 = new File("images\\regdoc.png");
        docReg.setImage(new Image(file8.toURI().toString()));

        File file9 = new File("images\\findpat_1.png");
        patSearch.setImage(new Image(file9.toURI().toString()));

        File file10 = new File("images\\docfind.png");
        docSearch.setImage(new Image(file10.toURI().toString()));

        File file1 = new File("images\\logout.png");
        logout.setImage(new Image(file1.toURI().toString()));
    }

    /**
     *
     * @param stage * public void start(Stage stage){ // load the image Image
     * image = new Image("D:\\444.png");
     *
     * // simple displays ImageView the image as is logo.setImage(image); }
     */
    @FXML
    private void gotoPatientSearch(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("PatientSearch.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void gotoDoctorRegistraion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("RegisterDoc.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void gotoDoctorSearch(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DocSearch.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {

    }

    @FXML
    private void gotoPatientRegistraion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("RegisterPatient.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void gotoDocRecommend(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DocRec.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void gotoAppointmentPage(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void gotoDocDatabase(ActionEvent event) {
        /*try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("DocDatabase.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }*/
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DocDatabase.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Database");
            File file = new File("images\\database.png");
            stage.getIcons().add(new Image(file.toURI().toString()));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoLoginPage(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("demo.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
