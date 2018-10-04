/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("demo.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Hospital Database");
        File file = new File("images\\icon.png");
        stage.getIcons().add(new Image(file.toURI().toString()));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Doctor getDocFromDbID(int n) {
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
            return req;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Doctor getDocFromDbNAME(String s) {
        try {
            //Derby embeded database codes
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            s = s.toUpperCase();
            ResultSet myDoc = stmt.executeQuery("SELECT * FROM DOCTORS WHERE upper(name)='" + s + "'");
            myDoc.next();
            String name = myDoc.getString("name");
            int id = myDoc.getInt("id");
            String spec = myDoc.getString("spec");

            Doctor req = new Doctor(name, id, spec);
            return req;

        } catch (SQLException e) {
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

    public void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE DOCTORS (ID INT PRIMARY KEY, NAME VARCHAR(40), SPEC VARCHAR(40))");
            System.out.println("Created Table DOCTORS");
        } catch (SQLException e) {
            System.out.println("Table DOCTORS already exist");
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE PATIENTS (ID INT PRIMARY KEY, NAME VARCHAR(40), AGE INT, GENDER VARCHAR(12),  PHONE INT, DISEASE VARCHAR(40), DATE VARCHAR(20))");
            System.out.println("Created Table PATIENTS");

        } catch (SQLException e) {
            System.out.println("Table PATIENTS already exist");
        }
        
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

}
