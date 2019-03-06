package controlleur.creation;

import controlleur.ControllerAccueil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class ControllerCreation {


    @FXML
    Label Retour;
    @FXML
    Label CarteC;
    @FXML
    Label carte;
    @FXML
    Label PileC;
    @FXML
    Label PileE;

    private Stage stage;

    public ControllerCreation(Stage s){
        stage = s;
    }
    public void clickimpExp(){
        System.out.println("ouvrir Import/Export");
    }

    public void clickCreap() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurPiles.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurPiles(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clickEdtp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueEditeurPile.fxml"));
        loader.setControllerFactory(iC->new ControllerEditeurPile(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }


    public void clickCreac() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurCartesClassique.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurCartes(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clickEdtc() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueEditeurCartes.fxml"));
        loader.setControllerFactory(iC->new ControllerEditeurCartes(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clicBACK() throws IOException {
        stage.setTitle("DesCartes");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();

        stage.setScene(new Scene(root, 1080, 720));
        stage.show();

    }

    public void labelRetour(){
        Retour.setVisible(true);
    }

    public void labelRetour2(){
        Retour.setVisible(false);
    }

    public void labelCarteC(){
        CarteC.setVisible(true);
    }

    public void labelCarteC2(){
        CarteC.setVisible(false);
    }
    public void labelcarte(){
        carte.setVisible(true);
    }

    public void labelcarte2(){
        carte.setVisible(false);
        carte.layout();
    }
    public void labelPileC(){
        PileC.setVisible(true);
    }

    public void labelPileC2(){
        PileC.setVisible(false);
    }
    public void labelPileE(){
        PileE.setVisible(true);
    }

    public void labelPileE2(){
        PileE.setVisible(false);
    }

}
