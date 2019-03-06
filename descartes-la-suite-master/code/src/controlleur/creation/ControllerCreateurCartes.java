package controlleur.creation;

import bd.H2Cartes;
import controlleur.ControllerAccueil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import modele.Carte;
import modele.CarteEntree;
import modele.CarteQCM;
import modele.CarteVF;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerCreateurCartes implements Initializable{

    private Stage stage;

    public ControllerCreateurCartes(Stage s){
        stage = s;
    }

    String q ;
    String r ;
    String t ;
    String th ;
    String vide = "";

    @FXML
    TextArea question;
    @FXML
    TextArea reponse;
    @FXML
    Label type;
    @FXML
    Label theme;
    @FXML
    ToggleButton classique;
    @FXML
    ToggleButton vf;
    @FXML
    RadioButton bphy;
    @FXML
    RadioButton bhis;
    @FXML
    RadioButton bgeo;
    @FXML
    RadioButton baut;
    @FXML
    RadioButton bcul;
    @FXML
    RadioButton bmat;
    @FXML
    RadioButton bfra;
    @FXML
    RadioButton bang;
    @FXML
    ImageView msg_error;
    @FXML
    ImageView rose;
    @FXML
    ToggleButton tb1;
    @FXML
    ToggleButton tborange;
    @FXML
    ToggleButton VRAI;
    @FXML
    ToggleButton FAUX;
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    ImageView VRAI_im;
    @FXML
    ImageView FAUX_im;
    @FXML
    Label Home;
    @FXML
    Label Retour;
    @FXML
    Label Edition;

    public void clicVrai(){
        VRAI_im.setOpacity(1);
        FAUX_im.setOpacity(0.25);
    }
    public void clicFaux(){
       VRAI_im.setOpacity(0.35);
       FAUX_im.setOpacity(1);
    }

    public void isSelect() throws IOException {
        tb1.setStyle("-fx-background-color: #ef8a26");
        tborange.setStyle("-fx-background-color: #565656");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueEditeurCartes.fxml"));
        loader.setControllerFactory(iC->new ControllerEditeurCartes(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void selectClassique() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurCartesClassique.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurCartes(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void selectQCM() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurCartesQCM.fxml"));
        loader.setControllerFactory(iC->new ControlleurCreationCartesQCM(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void selectVF() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurCartesVF.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurCartes(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void labelHome() {
        Home.setVisible(true);
    }

    public void labelHome2(){
        Home.setVisible(false);
    }

    public void labelRetour() {
        Retour.setVisible(true);
    }

    public void labelRetour2(){
        Retour.setVisible(false);
    }

    public void labelEdition() {
        Edition.setVisible(true);
    }

    public void labelEdition2(){
        Edition.setVisible(false);
    }


    public void clicvaliderVF(){

        //on enlève tous les espaces à la fin de la question pour éviter tout problème
        String texteARaccourcir = question.getText();
        texteARaccourcir = texteARaccourcir + "~#~";
        int longueur2 = texteARaccourcir.length();
        for(int k=longueur2 ; k>=0; k--){
            String res="";
            for(int l=0; l<k; l++){
                res=res+" ";
            }
            res=res+"~#~";
            if (texteARaccourcir.contains(res)) {
                texteARaccourcir = texteARaccourcir.replaceFirst(res, "");
            }
        }
        q=texteARaccourcir;
        H2Cartes carte = new H2Cartes();
        if (!carte.isInCarte(q)){
            if(VRAI.isSelected())
            {
                r = "Vrai";
            }
            else if (FAUX.isSelected()){
                r = "Faux";
            }else {
                msg_error.setVisible(true);
                rose.setVisible(false);
                VRAI_im.setOpacity(1);
                FAUX_im.setOpacity(1);
                return;
            }
            t = type.getText();
            th = theme.getText();
            if (th.equals("Culture G."))
                th = "Culture générale";
            if(q.equals(vide) || th.equals(vide)) {
                msg_error.setVisible(true);
                rose.setVisible(false);
            }
            else {
                msg_error.setVisible(false);
                rose.setVisible(true);
                question.setText("");
                CarteVF vf = new CarteVF(q,r,th);
                vf.ajouterCarteVFBD();
            }
            VRAI_im.setOpacity(1);
            FAUX_im.setOpacity(1);
        } else{
            msg_error.setVisible(true);
            rose.setVisible(false);
            VRAI_im.setOpacity(1);
            FAUX_im.setOpacity(1);
        }
    }

    public void clicvalider(){

        //on enlève tous les espaces à la fin de la question pour éviter tout problème
        String texteARaccourcir = question.getText();
        texteARaccourcir = texteARaccourcir + "~#~";
        int longueur2 = texteARaccourcir.length();
        for(int k=longueur2 ; k>=0; k--){
            String res="";
            for(int l=0; l<k; l++){
                res=res+" ";
            }
            res=res+"~#~";
            if (texteARaccourcir.contains(res)) {
                texteARaccourcir = texteARaccourcir.replaceFirst(res, "");
            }
        }
        q=texteARaccourcir;
        H2Cartes cartes = new H2Cartes();
        r = reponse.getText();
        t = type.getText();
        th = theme.getText();
        if (th.equals("Culture G."))
            th = "Culture générale";
        if(q.equals(vide) || r.equals(vide) || t.equals(vide) || th.equals(vide) || cartes.isInCarte(q)) {
            msg_error.setVisible(true);
            rose.setVisible(false);
        }
        else {
            msg_error.setVisible(false);
            rose.setVisible(true);


            switch (t) {
                case "Classique":
                    CarteEntree ce = new CarteEntree(q, r, th);
                    ce.ajouterCarteClassiqueBD();
                    break;
                case "Vrai/Faux":
                    CarteVF vf = new CarteVF(q, r, th);
                    vf.ajouterCarteVFBD();
                    break;
            }
            question.setText("");
            reponse.setText("");
        }
        //question.setText("");
        //reponse.setText("");
    }

    public void clicBACK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreation.fxml"));
        loader.setControllerFactory(iC->new ControllerCreation(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void clicHOME() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    // Commande pour valider le type de la carte
    public void typeactive(){

    }

    // Commande pour valider le theme de la carte
    public void themeactive() {
        if (bphy.isSelected()) {
            theme.setText("Physique");
        } else if (bhis.isSelected()) {
            theme.setText("Histoire");
        } else if (bgeo.isSelected()) {
            theme.setText("Géographie");
        } else if (bang.isSelected()) {
            theme.setText("Anglais");
        } else if (bfra.isSelected()) {
            theme.setText("Français");
        } else if (bcul.isSelected()) {
            theme.setText("Culture G.");
        } else if (baut.isSelected()) {
            theme.setText("Autres");
        } else if (bmat.isSelected()) {
            theme.setText("Maths");
        } else {
            theme.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (classique.isDisable()){
            type.setText("Classique");
        }
        else{
            type.setText("Vrai/Faux");
        }
    }

    public void changeb1(){
        //TODO changer la couleur du bouton sur lequel on est
        b1.setStyle("-fx-background-color: #e2e2e2");
    }

    public void changeb2(){
        //TODO changer la couleur du bouton sur lequel on est
        b2.setStyle("-fx-background-color: #e2e2e2");
    }

    public void x1(){
        b1.setStyle("-fx-background-color: #aeaeae");

    }

    public void x2(){
        b2.setStyle("-fx-background-color: #aeaeae");

    }
}
