/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class CreateAccountController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField conPass;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void register(ActionEvent event) {
        String name1 = name.getText();
        String pass1 = pass.getText();
        String conPass1 = conPass.getText();
        
        
        if ((pass1.equals(conPass1)) && !name.getText().trim().isEmpty() && !pass.getText().trim().isEmpty()) {
            registerAccount(name1, pass1);
            status.setText("Registration Complete!");
            status.setTextFill(Color.web("#008000"));
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            status.setTextFill(Color.web("#ff0000"));
            status.setText("Passwords don't match or empty fields");
        }

    }

    public static void registerAccount(String name, String pass) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO account VALUES ('" + name + "','" + pass + "')";
            stmt.executeUpdate(sql);
            System.out.println("updated" + name + pass);
        } catch (SQLException e) {

        }
    }
    
    
    
    public static String getPass(String name) {
        name = name.toUpperCase();
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            ResultSet mySql = stmt.executeQuery("select * from account WHERE upper(NAME)='" + name + "'");

            mySql.next();
            String pass = mySql.getString("pass");

            return pass;
        } catch (SQLException e) {
            System.out.println("not getting pass");
        }
        return null;
    }

    public static void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE account (name VARCHAR(40), pass VARCHAR(40))");
            System.out.println("Created Table account");
        } catch (SQLException e) {
            System.out.println("Table account already exist");
        }
    }

    public void deleteTable(String table) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM "+table;
            stmt.executeUpdate(sql);
            System.out.println("Deleted");

        } catch (SQLException ex) {
            System.out.println("Not deleted");
        }

    }
}
