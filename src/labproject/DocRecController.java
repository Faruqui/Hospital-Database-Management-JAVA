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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class DocRecController implements Initializable {

    @FXML
    private TextField problem;
    private TextArea info;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;

    Doctor[] doc = new Doctor[100];
    Doctor req;
    int index = 0;

    @FXML
    private TableView<Doctor> docList;
    @FXML
    private TableColumn<Doctor, Integer> idDoc;
    @FXML
    private TableColumn<Doctor, String> nameDoc;
    @FXML
    private TableColumn<Doctor, String> specDoc;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("images\\Doctor-Holding-Clipboard-fixed-arm.png");
        Image ss = new Image(file.toURI().toString());
        image.setImage(ss);

        //Table
        idDoc.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameDoc.setCellValueFactory(new PropertyValueFactory<>("name"));
        specDoc.setCellValueFactory(new PropertyValueFactory<>("specialization"));
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
    private void recommend(ActionEvent event) {
        String test = problem.getText();
        String s = getSpecFromDisease(problem.getText());
        if (!test.equals("") && s!=null) {
            try {
                s = s.toUpperCase();
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
                docList.setItems(doctors);
                error.setText("");
            } catch (Exception e) {
            }
        }else {
            error.setText("Sorry no doctors found, try a similar word \nand make sure there is no spelling mistake");
            docList.getItems().clear();
        }
    }
    
    public String getSpecFromDisease(String prob) {
        if ("cold | fever | headache | cough | typhoid | flu".contains(prob)) {
            return "Medicine";
        }
        if ("heart failure | irregular heartbeat | high pressure | low pressure | chest pain".contains(prob)) {
            return "Cardiologist";
        }
        if ("depression | anxiety | emotional disorder | mental disorder | bipolar".contains(prob)) {
            return "Psychiatrist";
        }
	if("acne | rashes | birthmarks| eczema | hair disorder | fungal infection | sweaty palms | lentigenes | melasma | psoriasis | rosacea | skin cancer | sun burn | vitiligo | wound healing".contains(prob)){
		return "Dermatologist";
	}
	if("athletes foot | calluse | nail disorder | foot injury | foot infection".contains(prob)) {
		 return "Podiatrist";
	}
	if("kidney stones | kidney disease | lupus nephritis | proteinuria | hematuria".contains(prob)){
		return "Nephrologist";
	}
	if("abdominal pain | gastric | constipation | difficulty swallowing | malabsorption disorder | nausea | vomiting | ulcer".contains(prob)){
		return "Gastroenterologist";
	}	
	if("blood disorder | bleeding disorder | blood cancer".contains(prob)){
		return "Hematologist";
	}
	if("ear infection | nasal deformities |  facial paralysis | nasal cavity | hearing loss | snoring | sinus | speech disorder | ear disorder | nose disorder | throat disorder".contains(prob)){
		return "Otolaryngologist";
	}	
	if("brain injury | epilepsy | stroke | neuromuscular disorder | nervous system | brain abscesses | migraines".contains(prob)){
		return "Neurologist";
	}
	if("blurry vision | dry eye | glaucoma | strabismus | cataracts | amblyopia | watery eyes | itchy eyes | eye cancer | retinal detachment".contains(prob)){
		return "Ophthalmologist";
	}
	if("tooth decay | cavity | gum disease | tooth infection | bad breath | tooth sensivity | toothache | mouth sores | oral cancer | dry mouth | gum bleed | yellow teeth".contains(prob)){
		return "Dentist";
	}
	if("missed period | stds | vaginal infection | hormone | painful intercourse | pelvic pain | female infertility | menstruation  | ovarian cysts | ovarian tumors | vaginitis".contains(prob)){
		return "Gynecologist";
	}
	if("lactose intolerance | malnutrition | obesity | overweight | vitamin deficiency | rickets |  body growth | unhealthy".contains(prob)){
		return "Nutritionist";
	}
	if("joint pain | fracture | shoulder dislocation | back pain | club foot | neck pain | bow legs | bone tumor | degenerative joint | knock knees | unequal leglength".contains(prob)){
		return "Orthopedic";
	}
	if("child asthma | child bellypain | unhealthy child | child fever | child seizure | cerebral palsy | child cold".contains(prob)){
		return "Pediatrician";
	}
	if("animal health | animal vacinate | animal infection | ill animal".contains(prob)){
		return "Veterinary";
	}
	if("lung cancer | pneumonia | asthma | breathing difficulty | tubercolosis | bronchitis | unhealthy lungs | emphysema".contains(prob)){
		return "Pulmonologist";
	}
	if("pregnancy test | ultrasound test | childbirth classes | pregnancy discomforts | birth control | unborn child".contains(prob)){
		return "Obstetrician";
	}
	if("male infertility | testicular injury | erectile dysfunction | urethral Injuries | testicular Cancer | penile fracture".contains(prob)){
		return "Andrologist";
	}
	if("adrenal disorder | diabetes | parathyroid | thyroid".contains(prob)){
		return "Endocrinologist";
	}	
        return null;

    }
}      


