package controlleur.apprentissage;

import bd.H2Cartes;
import bd.H2Contient;
import bd.H2Piles;
import controlleur.ControllerAccueil;
import controlleur.creation.ControllerCreation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import modele.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerApprentissageChoix implements Initializable{
    private Stage stage;
    private boolean block = false;

    @FXML
    FlowPane fpane;
    @FXML
    FlowPane fpaneListe;
    @FXML
    Label nomPile;
    @FXML
    Label descrPile;
    @FXML
    ImageView msg_Detail;
    @FXML
    ImageView msg_error3;
    @FXML
    Button b1;
    @FXML
    Label nbCarte;
    @FXML
    Label Retour;
    @FXML
    Label Home;

    public ControllerApprentissageChoix(Stage s) {
        stage =s;
    }


    public void go() throws IOException {
        ArrayList<String> nomPilesSelectionnees = new ArrayList<String>();
        ArrayList<Integer> nombreElementsParPile = new ArrayList<Integer>();

        boolean ok = false;
        int n = fpane.getChildren().size();
        for (int i = 0; i < n; i++) {
            if (((CheckBox) fpane.getChildren().get(i)).isSelected()){
                H2Contient hc = new H2Contient();
                ArrayList<String> x = hc.getCarteDePile(((CheckBox) fpane.getChildren().get(i)).getText());
                if (x.size() != 0){
                    ok = true;
                }
            }
        }

        if (!ok){

            msg_error3.setVisible(true);
        }
        else {

            //TODO récupérer les piles selectionnés
            H2Contient cont = new H2Contient();
            H2Cartes c = new H2Cartes();
            ArrayList<CheckBox> ar = new ArrayList<CheckBox>();

            for (int i = 0; i < n; i++) {
                if (((CheckBox) fpane.getChildren().get(i)).isSelected()) {
                    ar.add((CheckBox) fpane.getChildren().get(i));
                    nomPilesSelectionnees.add(((CheckBox) fpane.getChildren().get(i)).getText());
                }
            }
            //TODO récupérer les cartes dans chaque piles et les mettres dans l'ArrayList
            ArrayList<Carte> listCarte = new ArrayList<Carte>();

            for (CheckBox rb : ar) {
                //on stocke toutes les questions

                ArrayList<String> questions = cont.getCarteDePile(rb.getText());

                for (int i = 0; i < questions.size(); i++) {
                    String rep = c.getRepCarteByQuest(questions.get(i));
                    String them = c.getThemCarteByQuest(questions.get(i));
                    String type = c.getTypeCarteByQuest(questions.get(i));

                    if (type.equals("QCM")) {
                        String rp1 = c.getRep1ByQuest(questions.get(i));
                        String rp2 = c.getRep2ByQuest(questions.get(i));
                        String rp3 = c.getRep3ByQuest(questions.get(i));
                        CarteQCM carteQCM = new CarteQCM(questions.get(i), rep, rp1, rp2, rp3, them);
                        listCarte.add(carteQCM);
                    }

                    else if (type.equals("V/F")) {
                        CarteVF vf = new CarteVF(questions.get(i), rep, them);
                        listCarte.add(vf);

                    } else {
                        CarteEntree classique = new CarteEntree(questions.get(i), rep, them);
                        listCarte.add(classique);
                    }
                }
                nombreElementsParPile.add(questions.size());

            }

            Apprentissage app = new Apprentissage(listCarte,true);
            app.setNomPilesSelectionnees(nomPilesSelectionnees);
            app.setNombreElementsParPile(nombreElementsParPile);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueApprentissageJeu.fxml"));
            loader.setControllerFactory(iC -> new ControllerApprentissageJeu(stage, app));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        }
    }
    /*
    public void clicBACK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }*/

    public void clicHOME() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void removeCarte(){

        int n = fpaneListe.getChildren().size();
        int i = 0;
        while (i < n){
            if (((RadioButton)fpaneListe.getChildren().get(i)).isSelected()){
                ((RadioButton)fpaneListe.getChildren().get(i)).setSelected(false);
                fpane.getChildren().addAll((fpaneListe.getChildren().get(i)));
                n--;
                i--;
            }
            i++;
        }
    }

    public void addPile(){
        //TODO récupérer toute les checkBox cocher et les mettre dans la selection
/*
        int n = fpane.getChildren().size();
        int i = 0;
        while (i<n){
            if (((RadioButton)fpane.getChildren().get(i)).isSelected()){

                fpaneListe.getChildren().addAll(fpane.getChildren().get(i));
                ((RadioButton) fpaneListe.getChildren().get(fpaneListe.getChildren().size()-1)).fire();

                n--;
                i--;
            }
            i++;
        }
        msg_Detail.setVisible(false);
        nomPile.setText("");
        descrPile.setText("");
        */
    }
    public void clicDetail() {
        /*
        int n = fpane.getChildren().size();
        int i = 0;
        String texte;
        H2Piles p = new H2Piles();
        while (i < n) {
            if (((RadioButton) fpane.getChildren().get(i)).isSelected()) {
                texte = ((RadioButton) fpane.getChildren().get(i)).getText();
                nomPile.setText(texte);
                String descr = p.getDescriptionByNom(((RadioButton) fpane.getChildren().get(i)).getText());
                descrPile.setText(descr);
                msg_Detail.setVisible(true);
            }
            i++;
        }
        */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        H2Piles p = new H2Piles();
        if (!block){
            ArrayList<String> res = p.visualiserPile();
            //TODO regarder toute les pile de la base de donner et mettre leur nom dans le flowpane
            for (int i=0;i<res.size();i++) {
                CheckBox rb = new CheckBox();
                rb.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (rb.isSelected()){
                            H2Piles p = new H2Piles();
                            String x = p.getDescriptionByNom(rb.getText());
                            nomPile.setText(rb.getText());
                            descrPile.setText(x);
                            msg_Detail.setVisible(true);
                            msg_error3.setVisible(false);
                            H2Contient hc = new H2Contient();
                            ArrayList<String> cartepile = hc.getCarteDePile(rb.getText());
                            String a = ""+cartepile.size();
                            nbCarte.setText(a);
                        }
                    }
                }
                );
                rb.setText(res.get(i));
                fpane.getChildren().add(rb);
            }
            block = true;
        }
    }

    public void changeb1(){
        //TODO changer la couleur du bouton sur lequel on est
        b1.setStyle("-fx-background-color: #e2e2e2");
    }

    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");
    }

    public void labelRetour(){
        Retour.setVisible(true);
    }

    public void labelRetour2(){
        Retour.setVisible(false);
    }

    public void labelHome() {
        Home.setVisible(true);
    }

    public void labelHome2(){
        Home.setVisible(false);
    }
}
