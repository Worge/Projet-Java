package controlleur.creation;

import bd.H2Cartes;
import controlleur.ControllerAccueil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import modele.CarteEntree;
import modele.CarteQCM;
import modele.CarteVF;

import java.io.FileWriter;
import java.io.IOException;


public class ControlleurCreationCartesQCM {

    private Stage stage;

    public ControlleurCreationCartesQCM(Stage s){
        stage = s;
    }

    String q ;
    String r1 ;
    String r2 ;
    String r3 ;
    String r4 ;
    String t ;
    String th ;
    String vide = "";

    @FXML
    TextArea question;
    @FXML
    TextArea reponse1;
    @FXML
    TextArea reponse2;
    @FXML
    TextArea reponse3;
    @FXML
    TextArea reponse4;
    @FXML
    Label theme;
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
    Button b1;
    @FXML
    Label Home;
    @FXML
    Label Retour;
    @FXML
    Label Edition;



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


    public void clicvaliderQCM(){

        H2Cartes hc = new H2Cartes();

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
        r1 = reponse1.getText();
        r2 = reponse2.getText();
        r3 = reponse3.getText();
        r4 = reponse4.getText();
        t = "QCM";
        th = theme.getText();
        if (th.equals("Culture G."))
            th = "Culture générale";
        if(q.equals(vide) || r1.equals(vide) || r2.equals(vide) || r3.equals(vide) || r4.equals(vide) || t.equals(vide) || th.equals(vide) || hc.isInCarte(q)) {
            msg_error.setVisible(true);
            rose.setVisible(false);
        }
        else {
            msg_error.setVisible(false);
            rose.setVisible(true);
            CarteQCM ce = new CarteQCM(q,r1,r2,r3,r4,th);
            ce.ajouterCarteQCMBD();
            question.setText("");
            reponse1.setText("");
            reponse2.setText("");
            reponse3.setText("");
            reponse4.setText("");
        }

        //TODO faire le message d'erreur

        //question.setText("");
        //reponse1.setText("");
        //reponse2.setText("");
        //reponse3.setText("");
        //reponse4.setText("");
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
            theme.setText("Geographie");
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

    public void changeb1(){
        //TODO changer la couleur du bouton sur lequel on est
        b1.setStyle("-fx-background-color: #e2e2e2");
    }

    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");

    }

}
