package controlleur.apprentissage;

import bd.H2Stat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import modele.Apprentissage;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerApprentissageStats implements Initializable{

    private Stage stage;
    private Apprentissage apprentissage;


    @FXML
    BarChart<String,Integer> diagramme;

    @FXML
    PieChart diagramme1;

    @FXML
    PieChart diagramme2;

    @FXML
    PieChart diagramme3;

    @FXML
    CategoryAxis x;

    @FXML
    NumberAxis y;



    public ControllerApprentissageStats(Stage s){
        stage=s;
    }


    public void initialize(URL location, ResourceBundle resources) {


        String nomPile;
        int nombreColonnesAAfficher;
        int pourcentage;


        H2Stat stat = new H2Stat();

        ArrayList<String> nomPiles = stat.getPiles(); //Toutes les piles
        ArrayList<Integer> pourcentages = stat.getPourcentage(); //Tous les pourcentages

        ArrayList<String> pileUtilisee = new ArrayList<String>();


        nombreColonnesAAfficher = nomPiles.size();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < nombreColonnesAAfficher; i++) {

            nomPile = nomPiles.get(i);
            int valeur=0; //correspond à la combien-t-ieme fois il a fait cette pile
            for(int j=0; j<pileUtilisee.size(); j++) {
                if (nomPile.equals(pileUtilisee.get(j))){
                    valeur++;
                }
            }
            pileUtilisee.add(nomPile);
            if (valeur>0){
                nomPile = nomPile + Integer.toString(valeur+1);
            }
            pourcentage = pourcentages.get(i);
            series.getData().add(new XYChart.Data<>(nomPile, pourcentage));

        }
        diagramme.getData().addAll(series);
    }



    public void reagirAuClic() {


        String nomPile = "";

        H2Stat stat = new H2Stat();

        ArrayList<Integer> nombreDeBonneReponses = stat.getReussiByNom(nomPile);
        ArrayList<Integer> nombre1faute = stat.getRate1ByNom(nomPile);
        ArrayList<Integer> nombre2fautes = stat.getRate2ByNom(nomPile);
        ArrayList<Integer> nombre3fautesOuPlus = stat.getRate3ByNom(nomPile);



        ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                new PieChart.Data("1 faute", nombre1faute.get(0)),
                new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

        diagramme1.setData(pieChartData1);

        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                new PieChart.Data("1 faute", nombre1faute.get(1)),
                new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

        diagramme2.setData(pieChartData2);

        ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                new PieChart.Data("1 faute", nombre1faute.get(2)),
                new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

        diagramme3.setData(pieChartData3);


    }

}
