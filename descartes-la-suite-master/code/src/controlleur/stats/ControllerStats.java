package controlleur.stats;

        import bd.H2Stat;
        import controlleur.ControllerAccueil;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.chart.*;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.stage.Stage;
        import modele.Apprentissage;
        import javafx.fxml.Initializable;


        import java.io.IOException;
        import java.lang.reflect.Array;
        import java.sql.Date;
        import java.text.DateFormat;
        import java.text.DateFormatSymbols;
        import java.text.SimpleDateFormat;
        import java.util.*;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.chart.BarChart;
        import javafx.scene.chart.CategoryAxis;
        import javafx.scene.chart.XYChart;

        import java.net.URL;

public class ControllerStats implements Initializable {

    private Stage stage;
    private Apprentissage apprentissage;


    @FXML
    BarChart<String,Integer> diagramme;

    @FXML
    Label label11;

    @FXML
    Label label12;

    @FXML
    Label label13;

    @FXML
    Label label21;

    @FXML
    Label label22;

    @FXML
    Label label23;

    @FXML
    Label indication;

    @FXML
    PieChart diagramme1;

    @FXML
    PieChart diagramme2;

    @FXML
    PieChart diagramme3;

    @FXML
    Button afficher;

    @FXML
    Button texteEnBas;

    @FXML
    Label labelPile;

    @FXML
    CategoryAxis x;

    @FXML
    NumberAxis y;

    @FXML
    Label Home;

    @FXML
    Group group1;

    @FXML
    Group group2;

    @FXML
    Group group3;

    private ArrayList<String> nomPiles = new ArrayList<String>();
    private ArrayList<Integer> valeursOccurence = new ArrayList<Integer>();

    private int nombreColonnesAAfficher;



    public ControllerStats(Stage s){
        stage=s;
    }


    public void initialize(URL location, ResourceBundle resources) {

        label11.setVisible(false);
        label12.setVisible(false);
        label13.setVisible(false);
        label21.setVisible(false);
        label22.setVisible(false);
        label23.setVisible(false);
        afficher.setVisible(false);
        labelPile.setVisible(false);
        indication.setVisible(false);
        group1.setVisible(false);
        group2.setVisible(false);
        group3.setVisible(false);

        for(int i=0 ; i<15; i++) {
            valeursOccurence.add(0);
        }

        String nomPile;
        int pourcentage;


        H2Stat stat = new H2Stat();

        nomPiles = stat.getPiles(); //Toutes les piles (les 15 premières)
        ArrayList<Integer> pourcentages = stat.getPourcentage(); //Tous les pourcentages

        ArrayList<String> pileUtilisee = new ArrayList<String>();


        nombreColonnesAAfficher = nomPiles.size();

        if(nombreColonnesAAfficher==0){
            labelPile.setVisible(true);
            labelPile.setText("Entrainez-vous !");
            diagramme.setOpacity(0.05);
            texteEnBas.setVisible(false);
        }

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
                valeursOccurence.set(i,valeur+1);
            }
            pourcentage = pourcentages.get(i);
            series.getData().add(new XYChart.Data<>(nomPile, pourcentage));


        }
        //On complète si le mec a moins de 15 stats

        for (int j=nombreColonnesAAfficher; j<15; j++){
            nomPile="";
            for(int i=0; i<j; i++) {
                nomPile = nomPile + " ";
            }
            pourcentage = 0;
            series.getData().add(new XYChart.Data<>(nomPile, pourcentage));
        }


        diagramme.getData().addAll(series);
    }


    public void labelHome(){
        Home.setVisible(true);
    }

    public void labelHome2(){
        Home.setVisible(false);
    }

    public void clicHOME() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }


    public void afficherStat() {
        afficher.setVisible(false);
        diagramme.setOpacity(1.0);
        label11.setVisible(false);
        label12.setVisible(false);
        label21.setVisible(false);
        label22.setVisible(false);
        label13.setVisible(false);
        label23.setVisible(false);
        group1.setVisible(false);
        group2.setVisible(false);
        group3.setVisible(false);
        labelPile.setVisible(false);
        indication.setVisible(false);
        texteEnBas.setVisible(true);
    }

    public void changeAfficher() {
        afficher.setStyle("-fx-background-color: #e2e2e2");
    }

    public void x() {
        afficher.setStyle("-fx-background-color: #aeaeaeae");
    }

    public void b1() {


        if(nombreColonnesAAfficher<1){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        afficher.setVisible(true);
        diagramme.setOpacity(0.05);
        String nomPile = nomPiles.get(0);
        int valeur = valeursOccurence.get(0);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");


        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);

        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }

    public void b2() {

        if(nombreColonnesAAfficher<2){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(1);
        int valeur = valeursOccurence.get(1);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b3() {

        if(nombreColonnesAAfficher<3){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(2);
        int valeur = valeursOccurence.get(2);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b4() {

        if(nombreColonnesAAfficher<4){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(3);
        int valeur = valeursOccurence.get(3);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b5() {

        if(nombreColonnesAAfficher<5){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(4);
        int valeur = valeursOccurence.get(4);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b6() {

        if(nombreColonnesAAfficher<6){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(5);
        int valeur = valeursOccurence.get(5);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b7() {

        if(nombreColonnesAAfficher<7){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(6);
        int valeur = valeursOccurence.get(6);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b8() {

        if(nombreColonnesAAfficher<8){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(7);
        int valeur = valeursOccurence.get(7);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b9() {

        if(nombreColonnesAAfficher<9){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(8);
        int valeur = valeursOccurence.get(8);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b10() {

        if(nombreColonnesAAfficher<10){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(9);
        int valeur = valeursOccurence.get(9);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b11() {

        if(nombreColonnesAAfficher<11){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(10);
        int valeur = valeursOccurence.get(10);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b12() {

        if(nombreColonnesAAfficher<12){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(11);
        int valeur = valeursOccurence.get(11);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b13() {

        if(nombreColonnesAAfficher<13){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(12);
        int valeur = valeursOccurence.get(12);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b14() {

        if(nombreColonnesAAfficher<14){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(13);
        int valeur = valeursOccurence.get(13);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
    public void b15() {

        if(nombreColonnesAAfficher<15){ return; };//Si l'utilisateur clique sur un diagramme inexistant
        texteEnBas.setVisible(false);
        diagramme.setOpacity(0.05);
        afficher.setVisible(true);
        String nomPile = nomPiles.get(14);
        int valeur = valeursOccurence.get(14);
        labelPile.setVisible(true);
        labelPile.setText(nomPile);
        indication.setVisible(true);
        indication.setText("Voici les 3 dernières sessions de la pile sélectionnée");
        H2Stat stat = new H2Stat();
        ArrayList<Integer> nombreDeBonneReponses = stat.get3ReussiByNom(nomPile, valeur);
        ArrayList<Integer> nombre1faute = stat.get3Rate1ByNom(nomPile, valeur);
        ArrayList<Integer> nombre2fautes = stat.get3Rate2ByNom(nomPile, valeur);
        ArrayList<Integer> nombre3fautesOuPlus = stat.get3Rate3ByNom(nomPile, valeur);
        ArrayList<Date> nombreDate = stat.get3DateByNom(nomPile, valeur);

        int nombre=nombreDeBonneReponses.size();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (nombre>0) {
            group1.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(0)/(double)(nombreDeBonneReponses.get(0)+nombre1faute.get(0)+nombre2fautes.get(0)+nombre3fautesOuPlus.get(0)));
            label11.setVisible(true);
            label11.setText("session du " + df.format(nombreDate.get(0)));
            label21.setVisible(true);
            label21.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(0)),
                    new PieChart.Data("1 faute", nombre1faute.get(0)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(0)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(0)));

            diagramme1.setData(pieChartData1);
        } else {
            group1.setVisible(false);
            label11.setVisible(false);
            label21.setVisible(false);
        }
        if(nombre>1) {
            group2.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(1)/(double)(nombreDeBonneReponses.get(1)+nombre1faute.get(1)+nombre2fautes.get(1)+nombre3fautesOuPlus.get(1)));
            label12.setVisible(true);
            label12.setText("session du " + df.format(nombreDate.get(1)));
            label22.setVisible(true);
            label22.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(1)),
                    new PieChart.Data("1 faute", nombre1faute.get(1)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(1)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(1)));

            diagramme2.setData(pieChartData2);
        } else {
            label12.setVisible(false);
            label22.setVisible(false);
            group2.setVisible(false);
        }
        if(nombre>2) {
            group3.setVisible(true);
            int pourcent = (int) (100.0 * (double)nombreDeBonneReponses.get(2)/(double)(nombreDeBonneReponses.get(2)+nombre1faute.get(2)+nombre2fautes.get(2)+nombre3fautesOuPlus.get(2)));
            label13.setVisible(true);
            label13.setText("session du " + df.format(nombreDate.get(2)));
            label23.setVisible(true);
            label23.setText( pourcent + "%");
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data("Réussi du premier coup", nombreDeBonneReponses.get(2)),
                    new PieChart.Data("1 faute", nombre1faute.get(2)),
                    new PieChart.Data("2 fautes", nombre2fautes.get(2)),
                    new PieChart.Data("3 fautes ou +", nombre3fautesOuPlus.get(2)));

            diagramme3.setData(pieChartData3);
        } else {
            label13.setVisible(false);
            label23.setVisible(false);
            group3.setVisible(false);
        }
    }
}