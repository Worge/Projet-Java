package controlleur.creation;

import bd.H2Cartes;
import bd.H2Contient;
import bd.H2Piles;
import controlleur.ControllerAccueil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import modele.Pile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCreateurPiles implements Initializable {
    private Stage stage;
    private String nom;
    private String desc;
    private String vide = "";

    @FXML
    FlowPane fpanePile;
    @FXML
    FlowPane fpaneCarte;
    @FXML
    TextField nompile;
    @FXML
    TextField descpile;
    @FXML
    SplitMenuButton filtre;
    @FXML
    ImageView msg_error2;
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button tout;
    @FXML
    Label Home;
    @FXML
    Label Retour;
    @FXML
    Label Supprimer;
    @FXML
    Label Ajouter;
    @FXML
    Label Edition;

    private boolean block = false;

    public ControllerCreateurPiles(Stage s){
        stage = s;
    }


    public void chargement(){
        if (!block){
            //TODO regarder toute les pile de la base de donner et mettre leur nom dans le flowpane
            H2Cartes carte = new H2Cartes();
            ArrayList<String> c = carte.visualiserCartes();

            for (int j =0;j<c.size();j++){
                String texteAAllonger = c.get(j);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                CheckBox cb = new CheckBox(texteAAllonger);

                fpaneCarte.getChildren().addAll(cb);
            }
            block = true;
        }
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

    public void addCarte(){
        //TODO récupérer toute les checkBox cocher et les mettre dans la pile

        int n = fpaneCarte.getChildren().size();
        int i = 0;
        while (i<n){
            if (((CheckBox)fpaneCarte.getChildren().get(i)).isSelected()){
                String texteARaccourcir = ((CheckBox)fpaneCarte.getChildren().get(i)).getText();
                texteARaccourcir = texteARaccourcir + "~#~";
                int longueur2 = texteARaccourcir.length();
                for (int k = longueur2; k >= 0; k--) {
                    String res = "";
                    for (int l = 0; l < k; l++) {
                        res = res + " ";
                    }
                    res = res + "~#~";
                    if (texteARaccourcir.contains(res)) {
                        texteARaccourcir = texteARaccourcir.replaceFirst(res, "");
                    }
                }
                boolean notDejaDedans = true;
                for(int j=0; j<fpanePile.getChildren().size(); j++){
                    if (texteARaccourcir.equals(((CheckBox) fpanePile.getChildren().get(j)).getText())) {
                        notDejaDedans=false;
                    }
                }
                if (notDejaDedans) {
                    ((CheckBox) fpaneCarte.getChildren().get(i)).fire();
                    fpanePile.getChildren().addAll(fpaneCarte.getChildren().get(i));
                    n--;
                    i--;
                }
            }
            i++;
        }/*
        for (int i = 0 ; i < n ; i++){
            if (((CheckBox)fpaneCarte.getChildren().get(i)).isSelected()){
                fpanePile.getChildren().addAll(fpaneCarte.getChildren().get(i));
            }
        }*/
    }

    public void clicFiltre(){

        //On efface se qui est déjà la

        boolean afficherTout = true; //On le met à false dès qu'on trouve quelquechose de coché
        boolean aCocheUnType = false;
        boolean aCocheUnTheme = false;
        tout.setText("T");

        ArrayList<String> res = new ArrayList<String>();

        int n = fpaneCarte.getChildren().size();

        while (n > 0){
            fpaneCarte.getChildren().remove(0);
            n--;
        }


        // On traite le filtrage par type
        for (int i = 0; i < 3 ; i++) {
            if (((RadioMenuItem) ((Menu)filtre.getItems().get(0)).getItems().get(i)).isSelected()){
                afficherTout = false;
                aCocheUnType = true;
                H2Cartes cartes = new H2Cartes();
                ArrayList<String> c = cartes.getQuestionByType((((Menu)filtre.getItems().get(0)).getItems().get(i)).getText());
                for (int j=0;j<c.size();j++){
                    res.add(c.get(j));
                    //CheckBox rb = new CheckBox((c.get(j)));
                    //fpaneCarte.getChildren().addAll(rb);
                }
            }
        }
        // On traite le filtrage par theme
        for (int i = 0; i < 8; i++) {
            if (((RadioMenuItem) ((Menu) filtre.getItems().get(1)).getItems().get(i)).isSelected()) {
                afficherTout = false;
                aCocheUnTheme = true;

                H2Cartes cartes = new H2Cartes();
                ArrayList<String> c = cartes.getQuestionByTheme((((Menu) filtre.getItems().get(1)).getItems().get(i)).getText());
                if(aCocheUnType) { //Si l'utilisateur a coché un type
                    for (int j = 0; j < c.size(); j++) { //On regarde dans toutes les questions trouvées avec les thèmes
                        String questionAAfficher = c.get(j);
                        boolean trouve = false;
                        for (int k = 0; k < res.size(); k++) { //On regarde toutes les questions avec les types sélectionnées
                            if (questionAAfficher.equals(res.get(k))) { //On n'ajoute la question que si elle correspond à un thème et un type sélectionné
                                trouve = true;
                            }
                        }
                        if (trouve) {
                            String texteAAllonger = questionAAfficher;
                            int longueur = texteAAllonger.length();
                            for (int k=longueur; k<70; k++) {
                                texteAAllonger = texteAAllonger + " ";
                            }
                            CheckBox rb = new CheckBox(texteAAllonger);
                            fpaneCarte.getChildren().addAll(rb);
                        }
                    }
                } else { //Si l'utilisateur n'a pas coché de type
                    for (int j = 0; j < c.size(); j++) {
                        String texteAAllonger = c.get(j);
                        int longueur = texteAAllonger.length();
                        for (int k=longueur; k<70; k++) {
                            texteAAllonger = texteAAllonger + " ";
                        }
                        CheckBox rb = new CheckBox(texteAAllonger);
                        fpaneCarte.getChildren().addAll(rb);
                    }
                }

            }
        }
        if (!aCocheUnTheme){
            for (int j = 0; j < res.size(); j++) {
                String texteAAllonger = res.get(j);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                CheckBox rb = new CheckBox(texteAAllonger);
                fpaneCarte.getChildren().addAll(rb);
            }
        }

        if(afficherTout){
            H2Cartes carte = new H2Cartes();
            ArrayList<String> c = carte.visualiserCartes();

            for (int j =0;j<c.size();j++){
                String texteAAllonger = c.get(j);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                CheckBox cb = new CheckBox(texteAAllonger);

                fpaneCarte.getChildren().addAll(cb);
            }
        }

    }

    public void removeCarte(){
        int n = fpanePile.getChildren().size();
        int i = 0;
        while (i < n){
            if (((CheckBox)fpanePile.getChildren().get(i)).isSelected()){

                fpanePile.getChildren().removeAll(fpanePile.getChildren().get(i));
                n--;
                i--;
            }
            i++;
        }
    }

    public void creerPile(){

        nom = nompile.getText();
        H2Piles hp = new H2Piles();
        desc = descpile.getText();

        if (nom.equals(vide) || desc.equals(vide) || hp.isInPile(nom)) {
            msg_error2.setVisible(true);
        }
        else {
            msg_error2.setVisible(false);
            Pile pile = new Pile(nom,desc);
            H2Contient ajout = new H2Contient();

            int n = fpanePile.getChildren().size();
            int i = 0;
            while (i < n){
                String texteARaccourcir = ((CheckBox)fpanePile.getChildren().get(i)).getText();
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
                ajout.ajouterCartePile(texteARaccourcir,pile.getNom());
                i++;
            }

            //TODO rechercher les cartes qui correspondent au nom dans la pile
            //TODO creer une pile avec c'est cartes et l'ajouter à la base de donnée

            int l = fpanePile.getChildren().size();
            while (l > 0){
                fpanePile.getChildren().remove(0);
                l--;
            }

            nompile.setText("");
            descpile.setText("");
        }
    }

    public void selectAll() {
        if (tout.getText().equals("T")) {
            int n = fpaneCarte.getChildren().size();

            for (int i = 0; i < n; i++) {
                ((CheckBox) fpaneCarte.getChildren().get(i)).setSelected(true);
            }
            tout.setText("R");
        } else {
            int n = fpaneCarte.getChildren().size();

            for (int i = 0; i < n; i++) {
                ((CheckBox) fpaneCarte.getChildren().get(i)).setSelected(false);
            }
            tout.setText("T");
        }
    }

    public void isSelect() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueEditeurPile.fxml"));
        loader.setControllerFactory(iC->new ControllerEditeurPile(stage));
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
        Supprimer.setVisible(true);
    }

    public void changeb3(){
        //TODO changer la couleur du bouton sur lequel on est
        b3.setStyle("-fx-background-color: #e2e2e2");
        Ajouter.setVisible(true);
    }

    public void changeb4(){
        b4.setStyle("-fx-background-color: #e2e2e2");
    }

    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");
        b2.setStyle("-fx-background-color: #aeaeae");
        b3.setStyle("-fx-background-color: #aeaeae");
        b4.setStyle("-fx-background-color: #aeaeae");
        Supprimer.setVisible(false);
        Ajouter.setVisible(false);
    }

    public void labelHome(){
        Home.setVisible(true);
    }

    public void labelHome2(){
        Home.setVisible(false);
    }

    public void labelRetour(){
        Retour.setVisible(true);
    }

    public void labelRetour2(){
        Retour.setVisible(false);
    }

    public void labelEdition(){
        Edition.setVisible(true);
    }

    public void labelEdition2(){
        Edition.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chargement();
    }

}
