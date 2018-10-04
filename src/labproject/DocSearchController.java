/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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


public class DocSearchController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextArea info;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\docfind.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);

    }

    @FXML
    private void find(ActionEvent event) {
        int n = Integer.parseInt(id.getText());

        try {
            //Derby embeded database codes
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            ResultSet myDoc = stmt.executeQuery("SELECT * FROM DOCTORS WHERE ID=" + n);
            myDoc.next();
            String name = myDoc.getString("name");
            int id = myDoc.getInt("id");
            String spec = myDoc.getString("spec");

            Doctor req = new Doctor(name, id, spec);
            System.out.println("Doctor 1:zz " + name + id + spec);
            info.setText(req.getInfo());
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            info.setText("No doctor with ID: " + n + " exist in the database\n\nPlease try Again");
        }
        id.clear();
    }

    private Doctor getDoctor(Doctor[] arr, int id) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (id == arr[i].id) {
                return arr[i];
            }
        }
        return null;
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

    public Doctor getDoctorDisease(Doctor[] arr, String prob) {
        int n = arr.length;

        if (prob.equalsIgnoreCase("cold") || prob.equalsIgnoreCase("fever")) {
            Doctor d = getDoctor(arr, "Medicine");
            return d;
        }
        return null;
    }

    public Doctor getDoctor(Doctor[] arr, String spec) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (spec.equalsIgnoreCase(arr[i].specialization)) {
                return arr[i];
            }
        }
        return null;
    }
}
