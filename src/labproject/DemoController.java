/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;


public class DemoController extends CreateAccountController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pass;
    @FXML
    private Label status;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView HLogo;
    @FXML
    private ImageView nsuLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //File file = new File("D:\\Academic Stuffs\\Semester 3 (181)\\CSE 215 LAB\\labproject\\images\\logIn.png");
        File file = new File("images\\hicon.png");
        Image ss = new Image(file.toURI().toString());
        HLogo.setImage(ss);

        File file11 = new File("images\\nsu.png");
        nsuLogo.setImage(new Image(file11.toURI().toString()));

    }

    @FXML
    private void signIn(ActionEvent event) {
        try {
            String name = username.getText();
            String password = pass.getText();

            String realPass = getPass(name);

            if (password.equals(realPass)) {
                status.setText("Establised connection to database...");
                //rootPane.getChildren().setAll(pane);
                BorderPane pane = FXMLLoader.load(getClass().getResource("NewHomePage.fxml"));
                rootPane.getChildren().setAll(pane);
                /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewHomePage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();*/
                username.clear();
                pass.clear();
            } else {
                status.setText("Wrong Username or password");
            }

        } catch (Exception e) {
            status.setText("Wrong Username or password");
        }

    }

    @FXML
    private void gotoRegistration(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Account Registration");
            File file = new File("images\\icon.png");
            stage.getIcons().add(new Image(file.toURI().toString()));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
