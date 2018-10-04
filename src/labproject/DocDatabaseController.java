/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labproject;

import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class DocDatabaseController extends NewFXMain implements Initializable {

    @FXML
    private TableView<Doctor> docList;
    @FXML
    private BorderPane rootPane;
    @FXML
    private TableColumn<Doctor, Integer> idDoc;
    @FXML
    private TableColumn<Doctor, String> nameDoc;
    @FXML
    private TableColumn<Doctor, String> specDoc;

    @FXML
    private TableView<Patient> patList;
    @FXML
    private TableColumn<Patient, Integer> idPat;
    @FXML
    private TableColumn<Patient, String> namePat;
    @FXML
    private TableColumn<Patient, String> genderPat;
    @FXML
    private TableColumn<Patient, Integer> agePat;
    @FXML
    private TableColumn<Patient, Long> phonePat;
    @FXML
    private TableColumn<Patient, String> diseasePat;
    @FXML
    private TableColumn<Patient, String> datePat;
    @FXML
    private TextField IDDoc;
    @FXML
    private TextField IDPat;
    @FXML
    private TextField docSpec;
    @FXML
    private TextField docID;
    @FXML
    private TextField docName;
    @FXML
    private ImageView docImg;
    @FXML
    private ImageView patImg;
    @FXML
    private TextField patID;
    @FXML
    private TextField patName;
    @FXML
    private ImageView docImg1;
    @FXML
    private ImageView patImg1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //For Doctor table
        idDoc.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameDoc.setCellValueFactory(new PropertyValueFactory<>("name"));
        specDoc.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        //For Patient Table
        idPat.setCellValueFactory(new PropertyValueFactory<>("id"));
        namePat.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderPat.setCellValueFactory(new PropertyValueFactory<>("gender"));
        agePat.setCellValueFactory(new PropertyValueFactory<>("age"));
        phonePat.setCellValueFactory(new PropertyValueFactory<>("phone"));
        datePat.setCellValueFactory(new PropertyValueFactory<>("date"));
        diseasePat.setCellValueFactory(new PropertyValueFactory<>("disease"));

        File file9 = new File("images\\findpat_1.png");
        patImg.setImage(new Image(file9.toURI().toString()));
        patImg1.setImage(new Image(file9.toURI().toString()));

        File file10 = new File("images\\docfind.png");
        docImg.setImage(new Image(file10.toURI().toString()));
        docImg1.setImage(new Image(file10.toURI().toString()));

        updateTable();
    }

    @FXML
    private void goHome(ActionEvent event) {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("NewHomePage.fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void deleteDoc(ActionEvent event) {
        int id = Integer.parseInt(IDDoc.getText());
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM DOCTORS WHERE ID = " + id + "";
            stmt.executeUpdate(sql);
            System.out.println("Deleted");
            updateTable();

        } catch (SQLException ex) {
            System.out.println("Not deleted");
            updateTable();
        }
    }

    @FXML
    private void deletePat(ActionEvent event) {
        int id = Integer.parseInt(IDPat.getText());
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM patients WHERE ID = " + id + "";
            stmt.executeUpdate(sql);
            System.out.println("Deleted");
            updateTable();

        } catch (SQLException ex) {
            System.out.println("Not deleted");
            updateTable();
        }
    }

    public void updateTable() {
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            System.out.println("Connected !!");
            Statement stmt = conn.createStatement();

            ResultSet myDoc = stmt.executeQuery("select * from doctors");
            while (myDoc.next()) {
                String name = myDoc.getString("name");
                int id = myDoc.getInt("id");
                String spec = myDoc.getString("spec");

                Doctor d = new Doctor(name, id, spec);
                doctors.add(d);
            }

            //Getting patients
            ResultSet myPat = stmt.executeQuery("select * from patients");

            while (myPat.next()) {
                String name = myPat.getString("Name");
                int id = myPat.getInt("ID");
                long phone = myPat.getLong("Phone");
                String gender = myPat.getString("Gender");
                int age = myPat.getInt("Age");
                String date = myPat.getString("Date");
                String disease = myPat.getString("Disease");

                Patient p = new Patient(id, name, phone, gender, age, date, disease);
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        docList.setItems(doctors);
        patList.setItems(patients);
    }

    @FXML
    private void searchDocId(ActionEvent event) {
        try {
            int d = Integer.parseInt(docID.getText());
            Doctor reqD = getDocFromDbID(d);
            ObservableList<Doctor> doctors = FXCollections.observableArrayList();
            doctors.add(reqD);

            docList.setItems(doctors);
            docID.clear();
        } catch (Exception e) {
            updateTable();
        }

    }

    @FXML
    private void searchDocName(ActionEvent event) {
        String s = null;
        s = docName.getText();
        if (!s.equals("")) {
            Doctor reqD = getDocFromDbNAME(s);
            ObservableList<Doctor> doctors = FXCollections.observableArrayList();
            doctors.add(reqD);
            
            docList.setItems(doctors);
            docName.clear();
        } else {
            updateTable();
        }
    }

    @FXML
    private void searchDocSpec(ActionEvent event) {
        String s = docSpec.getText().toUpperCase();
        docSpec.clear();
        if (!s.equals("")) {
            try {
                ObservableList<Doctor> doctors = FXCollections.observableArrayList();

                Connection conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
                System.out.println("Connected !!");
                Statement stmt = conn.createStatement();
                ResultSet myDoc = stmt.executeQuery("SELECT * FROM DOCTORS WHERE upper(spec)='" + s + "'");

                while (myDoc.next()) {
                    String name = myDoc.getString("name");
                    int id = myDoc.getInt("id");
                    String spec = myDoc.getString("spec");

                    Doctor req = new Doctor(name, id, spec);
                    doctors.add(req);
                }
                docList.getItems().clear();
                docList.setItems(doctors);
            } catch (Exception e) {
            }
        } else {
            updateTable();
        }
    }

    @FXML
    private void searchPatName(ActionEvent event) {
        String s = patName.getText();
        if (!s.equals("")) {
            Patient req = getPatientFromDbNAME(s);
            ObservableList<Patient> patients = FXCollections.observableArrayList();
            patients.add(req);
            patList.setItems(patients);
            patName.clear();
        }else{
            updateTable();
        }

    }

    @FXML
    private void searchPatID(ActionEvent event) {
        try {
            int d = Integer.parseInt(patID.getText());
            Patient req = getPatientFromDbID(d);
            ObservableList<Patient> patients = FXCollections.observableArrayList();
            patients.add(req);
            patList.setItems(patients);
            patID.clear();
        } catch (Exception e) {
            updateTable();
        }

    }
}
