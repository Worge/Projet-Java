package controlleur;

import controlleur.Examen.ControllerExamen;
import controlleur.apprentissage.ControllerApprentissageChoix;
import controlleur.apprentissage.ControllerApprentissageStats;
import controlleur.creation.ControllerCreation;
import controlleur.stats.ControllerStats;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerAccueil implements Initializable {

    private Stage stage;
    Timeline t = new Timeline();
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    ImageView easterEgg;

    private double cycle = 50;
    private double temps = 2000;
    private int alea = 0;

    public ControllerAccueil(Stage s){
        stage = s;
    }

    public void clicEasterEgg(){
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public void clickcreation() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreation.fxml"));
        loader.setControllerFactory(iC->new ControllerCreation(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clickjouer() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueApprentissageChoix.fxml"));
        loader.setControllerFactory(iC->new ControllerApprentissageChoix(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clicExamen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuExamen/VueExamen.fxml"));
        loader.setControllerFactory(iC->new ControllerExamen(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }


    public void changeb1(){
        //TODO changer la couleur du bouton sur lequel on est
        b1.setStyle("-fx-background-color: #e2e2e2");
    }
    public void changeb2(){
        //TODO changer la couleur du bouton sur lequel on est
        b2.setStyle("-fx-background-color: #e2e2e2");
    }
    public void changeb3(){
        //TODO changer la couleur du bouton sur lequel on est
        b3.setStyle("-fx-background-color: #e2e2e2");
    }

    public void changeb4(){
        b4.setStyle("-fx-background-color: #e2e2e2");
    }
    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");
        b2.setStyle("-fx-background-color: #aeaeae");
        b3.setStyle("-fx-background-color: #aeaeae");
        b4.setStyle("-fx-background-color: #aeaeae");
    }

    public void statistiques() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuStats/VueStats.fxml"));
        loader.setControllerFactory(iC->new ControllerStats(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
        t.getKeyFrames().addAll(
                new KeyFrame(new Duration(cycle), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    	if(easterEgg.getRotate() < 270) {
                    		easterEgg.setRotate(easterEgg.getRotate()+(360*cycle)/temps);
                    	}else if (alea <= 3){
                    		easterEgg.setRotate(90);
	                    	switch(alea) {
	                    	case 0:
	                    		easterEgg.setImage(new Image("/Ressources/1.png"));
	                    		break;
	                    	case 1:
	                    		easterEgg.setImage(new Image("/Ressources/2.png"));
	                    		break;
	                    	case 2:
	                    		easterEgg.setImage(new Image("/Ressources/3.png"));
	                    		break;
	                    	case 3:
	                            easterEgg.setImage(new Image("/Ressources/4.png"));
	                            break;
	                    	}
	                    	alea++;
                    	}else {
                    		alea = 0;
                    	}
                    }
                }));
    }
}
