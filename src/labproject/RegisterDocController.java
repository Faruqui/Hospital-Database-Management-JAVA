/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RegisterDocController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField spec;
    @FXML
    private ProgressBar pbar;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;
    @FXML
    private Label error;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\regdoc.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);
    }

    @FXML
    private void submit(ActionEvent event) {
        pbar.setProgress(0.0);
        int id1 = Integer.parseInt(id.getText());
        String name1 = name.getText();
        String spec1 = spec.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO DOCTORS VALUES (" + id1 + ",'" + name1 + "', '" + spec1 + "')";
            stmt.executeUpdate(sql);
            //stmt.executeUpdate("CREATE TABLE DOCTORS (ID INT PRIMARY KEY, NAME VARCHAR(40), SPEC VARCHAR(40))");
            //System.out.println("Created Table DOCTORS");

            System.out.println("Populated Table");
            msg.setText("Doctor registraion successful...");

            error.setText("    ");
            id.clear();
            name.clear();
            spec.clear();
            pbar.setProgress(0);
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
    private void setPbar1(KeyEvent event) {
        pbar.setProgress(.3);
    }

    @FXML
    private void setPbar2(KeyEvent event) {
        pbar.setProgress(.6);
    }

    @FXML
    private void setPbar3(KeyEvent event) {
        pbar.setProgress(1);
    }

}
