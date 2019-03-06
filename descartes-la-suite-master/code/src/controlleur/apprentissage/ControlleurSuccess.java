package controlleur.apprentissage;

import bd.H2Stat;
import controlleur.ControllerAccueil;
import controlleur.Examen.ControllerExamenJeu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Apprentissage;
import modele.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlleurSuccess implements Initializable {

    @FXML
    PieChart diagramme;

    @FXML
    Label bonne;

    @FXML
    Label mauvaise;

    @FXML
    Label pourcentage;

    @FXML
    FlowPane fpane;

    @FXML
    ScrollPane scroll;

    @FXML
    Button btn_voir;

    @FXML
    Button btn_cacher;

    @FXML
    Label Home;

    private Stage stage;
    private Apprentissage apprentissage;
    private boolean remplissage = true;
    private boolean isExam;
    private boolean difficulty = true;

    public ControlleurSuccess(Stage s, Apprentissage app,boolean isExam,boolean d){
        stage=s;
        apprentissage = app;
        this.isExam = isExam;
        difficulty = d;
    }

    public void playAgain() throws IOException {
        ArrayList<Carte> listecartes = new ArrayList<Carte>();
        listecartes = apprentissage.getPileDeCartes();
        if (isExam) {
            ArrayList<Carte> listCarte = apprentissage.getPileDeCartes();
            Apprentissage app = new Apprentissage(listCarte,false);
            app.setNomPilesSelectionnees(apprentissage.getNomPilesSelectionnees());
            app.setNombreElementsParPile(apprentissage.getNombreElementsParPile());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuExamen/VueExamenJeu.fxml"));
            loader.setControllerFactory(iC -> new ControllerExamenJeu(stage, app,difficulty));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        } else {
            ArrayList<Carte> listCarte = apprentissage.getPileDeCartes();
            Apprentissage app = new Apprentissage(listCarte,true);
            app.setNomPilesSelectionnees(apprentissage.getNomPilesSelectionnees());
            app.setNombreElementsParPile(apprentissage.getNombreElementsParPile());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueApprentissageJeu.fxml"));
            loader.setControllerFactory(iC -> new ControllerApprentissageJeu(stage, app));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        }
    }
    /*private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fpane.setVisible(false);
        scroll.setVisible(false);
        //gestion du graphique :
        ArrayList<Integer> nombreResultat = apprentissage.getNbrErreursDesCartes();
        int nombreDeBonneReponses = 0;
        int nombreDeMauvaiseReponses = 0;
        int nombre1faute = 0;
        int nombre2fautes = 0;
        int nombre3fautesOuPlus = 0;

        for (int i=0 ; i<nombreResultat.size(); i++){
            int nombreReponsesFausses=nombreResultat.get(i);
            if (nombreReponsesFausses==0){
                nombreDeBonneReponses++;
            } else if (nombreReponsesFausses==1) {
                nombreDeMauvaiseReponses++;
                nombre1faute++;
            } else if (nombreReponsesFausses==2) {
                nombreDeMauvaiseReponses++;
                nombre2fautes++;
            } else {
                nombreDeMauvaiseReponses++;
                nombre3fautesOuPlus++;
            }
        }
        int pourcent = (int)((double)nombreDeBonneReponses/(double)(nombreDeBonneReponses+nombreDeMauvaiseReponses)*100.0);

        bonne.setText(Integer.toString(nombreDeBonneReponses));
        mauvaise.setText(Integer.toString(nombreDeMauvaiseReponses));
        pourcentage.setText("Score : " + Integer.toString(pourcent) + "%");


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses),
                        new PieChart.Data("1 faute", nombre1faute),
                        new PieChart.Data("2 fautes", nombre2fautes),
                        new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus));

        diagramme.setData(pieChartData);
        /*
        applyCustomColorSequence(
                pieChartData,
                "green",
                "yellow",
                "orange",
                "red"
        );
*/


        //envoie des données à la base de donnée
        H2Stat stat=new H2Stat();
        int k=0; //variable qui indique à quelle carte ont est
        for(int j=0; j<apprentissage.getNombreElementsParPile().size() ; j++) { //numéro de la pile sélectionnée
            //récup les trucs
            String nomPile=apprentissage.getNomPilesSelectionnees().get(j);
            int nbReussi=0;
            int nb1erreur=0;
            int nb2erreurs=0;
            int nb3erreurs=0;
            for(int i=0 ; i<apprentissage.getNombreElementsParPile().get(j);i++) {
                ArrayList<Integer> nombreResultats = apprentissage.getNbrErreursDesCartes();
                int nombreReponsesFausses=nombreResultats.get(k);
                if (nombreReponsesFausses==0){
                    nbReussi++;
                } else if (nombreReponsesFausses==1) {
                    nb1erreur++;
                } else if (nombreReponsesFausses==2) {
                    nb2erreurs++;
                } else {
                    nb3erreurs++;
                }
                k++;
            }

            /*
            System.out.println(k);
            System.out.println(nomPile);
            System.out.println(nbReussi);
            System.out.println(nb1erreur);
            System.out.println(nb2erreurs);
            System.out.println(nb3erreurs);*/

            stat.ajouterStat(nomPile, nbReussi, nb1erreur, nb2erreurs, nb3erreurs);

        }
    }

    public void clicHOME() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    public void afficherErreurs() {
        //gestion des questions males répondues
        ArrayList<Integer> nombreReponsesFausseParQuestion = apprentissage.getNbrErreursDesCartes();
        ArrayList<Carte> cartes = apprentissage.getPileDeCartes();

        //récupération des Cartes ratées, puis on prend la question, réponse, type et thème

        if (remplissage) {
            remplissage = false;
            for (int i = 0; i < nombreReponsesFausseParQuestion.size(); i++) {
                if (nombreReponsesFausseParQuestion.get(i) > 0) {
                    cartes.add(apprentissage.getPileDeCartes().get(i));
                    Label comment = new Label();
                    if (nombreReponsesFausseParQuestion.get(i) == 1) {
                        comment.setText("            " + nombreReponsesFausseParQuestion.get(i) + " erreur :");
                    } else {
                        comment.setText("           " + nombreReponsesFausseParQuestion.get(i) + " erreurs :");
                    }
                    fpane.getChildren().add(comment);
                    Label labelQ = new Label();
                    labelQ.setText(" " + cartes.get(i).getQuestion());
                    fpane.getChildren().add(labelQ);
                    Label labelR = new Label();
                    labelR.setText("     " + cartes.get(i).getReponse() + "\n    ");
                    fpane.getChildren().add(labelR);
                }
            }
        }
        scroll.setVisible(true);
        fpane.setVisible(true);
        btn_voir.setVisible(false);
        btn_cacher.setVisible(true);
    }

    public void cacherStats() {
        scroll.setVisible(false);
        fpane.setVisible(false);
        btn_cacher.setVisible(false);
        btn_voir.setVisible(true);

    }

    public void labelHome()
    {
        Home.setVisible(true);
    }

    public void labelHome2()
    {
        Home.setVisible(false);
    }


    }

