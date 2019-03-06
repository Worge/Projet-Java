package controlleur.creation;

import bd.Enregistrer;
import bd.H2Cartes;
import bd.H2Contient;
import bd.H2Piles;
import controlleur.ControllerAccueil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEditeurPile implements Initializable {
    private Stage stage;
    private String nomPileSelected = new String();
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
    Label Creation;
    @FXML
    Label Retirer;
    @FXML
    Label Ajouter;
    @FXML
    Label SupPile;
    @FXML
    Label Home;
    @FXML
    Label Retour;
    @FXML
    Label Importer;
    @FXML
    Label Exporter;
    @FXML
    Button ip;
    @FXML
    Button ex;

    private ToggleGroup tg = new ToggleGroup();

    @FXML
    FlowPane fpane;


    public ControllerEditeurPile(Stage s){
        stage = s ;
    }

    public void chargement(){
            int n = fpane.getChildren().size();
            while (n > 0){
                fpane.getChildren().remove(0);
                n--;
            }

            H2Piles pile = new H2Piles();
            H2Cartes carte = new H2Cartes();
            ArrayList<String> p = pile.visualiserPile();
            ArrayList<String> c = carte.visualiserCartes();


            for (int i =0;i<p.size();i++){
                RadioButton rb = new RadioButton();
                rb.setToggleGroup(tg);
                rb.setText(p.get(i));
                rb.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        if (rb.isSelected()){
                                            H2Piles p = new H2Piles();
                                            H2Contient cp = new H2Contient();
                                            String x = p.getDescriptionByNom(rb.getText());
                                            nompile.setText(rb.getText());
                                            descpile.setText(x);
                                            int n = fpanePile.getChildren().size();
                                            while (n > 0){
                                                fpanePile.getChildren().remove(0);
                                                n--;
                                            }
                                            ArrayList<String> res = cp.getCarteDePile(rb.getText());
                                            for (int j=0;j<res.size();j++) {
                                                String texteAAllonger = res.get(j);
                                                int longueur = texteAAllonger.length();
                                                for (int k=longueur; k<70; k++) {
                                                    texteAAllonger = texteAAllonger + " ";
                                                }
                                                CheckBox cb = new CheckBox(texteAAllonger);
                                                fpanePile.getChildren().addAll(cb);
                                            }
                                        }
                                    }
                                }
                );
                fpane.getChildren().add(rb);
            }/* IDEE : pouvoir créer directement des cartes dans une pile
            CheckBox bout = new CheckBox("--Ajouter de nouvelles cartes---");
            bout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (bout.isSelected()){
                        System.out.println("on va ouvrir un truc pour créer une carte\n Puis on ajoutera la carte dans la pile sélectionnée");
                    }
                }
            }
            );
            fpaneCarte.getChildren().addAll(bout);*/
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


    public void select(){
        int n = fpanePile.getChildren().size();
        while (n > 0){
            fpanePile.getChildren().remove(0);
            n--;
        }
        H2Contient cp = new H2Contient();
        H2Piles p = new H2Piles();
        n = fpane.getChildren().size();
        int i = 0;
        String texte;
        while (i < n) {
            if (((RadioButton) fpane.getChildren().get(i)).isSelected()) {
                texte = ((RadioButton) fpane.getChildren().get(i)).getText();
                nompile.setText(texte);
                nomPileSelected = texte;
                String descr = p.getDescriptionByNom(((RadioButton) fpane.getChildren().get(i)).getText());
                descpile.setText(descr);
                ArrayList<String> res = cp.getCarteDePile((((RadioButton) fpane.getChildren().get(i)).getText()));
                for (int j=0;j<res.size();j++)
                    fpanePile.getChildren().addAll(new CheckBox(res.get(j)));
            }
            i++;
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
    public void addCarte(){
        H2Contient ajout = new H2Contient();
        H2Piles hp = new H2Piles();
        int n = fpaneCarte.getChildren().size();
        int i = 0;
        while (i<n){
            if (((CheckBox)fpaneCarte.getChildren().get(i)).isSelected() && hp.isInPile(nompile.getText())){
                String texteARaccourcir = ((CheckBox) fpaneCarte.getChildren().get(i)).getText();
                boolean estDedans = false;
                for(int k=0; k<fpanePile.getChildren().size(); k++) {
                    String texteAComparer = ((CheckBox)fpanePile.getChildren().get(k)).getText();
                    if(texteARaccourcir.equals(texteAComparer)) {
                        estDedans = true;
                    }
                }
                if (!estDedans) {
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
                    ajout.ajouterCartePile(texteARaccourcir, nompile.getText());
                    ((CheckBox) fpaneCarte.getChildren().get(i)).fire();
                    fpanePile.getChildren().addAll(fpaneCarte.getChildren().get(i));
                    n--;
                    i--;
                }
            }
            i++;
        }
    }

    public void enternom() {
        String texte = nompile.getText();
        H2Piles hp = new H2Piles();
        if (hp.isInPile(texte)) {
            int n = fpane.getChildren().size();
            int i = 0;
            nomPileSelected = texte;
            while (i < n) {
                if (((RadioButton) fpane.getChildren().get(i)).isSelected()) {
                    ((RadioButton) fpane.getChildren().get(i)).setText(texte);
                }
                i++;
            }
        }
        else {
            // TODO mettre message d'erreur
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
        H2Contient cont = new H2Contient();
        int n = fpanePile.getChildren().size();
        int i = 0;
        while (i < n){
            if (((CheckBox)fpanePile.getChildren().get(i)).isSelected()){
                String texteARaccourcir = (((CheckBox) fpanePile.getChildren().get(i))).getText();
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
                cont.supprimerCarteDePile(texteARaccourcir);
                fpanePile.getChildren().removeAll(fpanePile.getChildren().get(i));
                n--;
                i--;
            }
            i++;
        }
    }
    public void supprimerPile() {


        Stage stage2 = new Stage();
        //stage2.initModality(Modality.APPLICATION_MODAL);

        stage2.setTitle("Suppression de pile");
        stage2.setMaxWidth(310);
        stage2.setMinWidth(307);
        stage2.setMaxHeight(120);
        stage2.setMinHeight(100);

        Label modalityLabel = new Label("Voulez-vous vraiment supprimer cette pile ?");
        modalityLabel.setTranslateX(10);
        modalityLabel.setTranslateY(10);
        Button validate = new Button("Oui");
        validate.setTranslateX(76);
        validate.setTranslateY(30);
        validate.setOnAction(e -> {
            stage2.close();

            H2Piles p = new H2Piles();
            p.supprimerPile(nompile.getText());

            for (int i = 0; i < fpane.getChildren().size(); i++) {
                if (nompile.getText().equals(((RadioButton) fpane.getChildren().get(i)).getText())) {
                    fpane.getChildren().remove(i);
                }
            }
            nompile.setText("");
            descpile.setText("");


            int n = fpanePile.getChildren().size();
            while (n > 0) {
                fpanePile.getChildren().remove(0);
                n--;
            }
        });
        Button closeButton = new Button("Non");
        closeButton.setTranslateX(178);
        closeButton.setTranslateY(4);
        closeButton.setOnAction(e -> {
            stage2.close();
        });
        VBox root2 = new VBox();
        root2.getChildren().addAll(modalityLabel, validate, closeButton);
        Scene scene = new Scene(root2, 200, 100);
        stage2.setScene(scene);
        stage2.show();

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
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurPiles.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurPiles(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chargement();
    }

    public void changeb1(){
        b1.setStyle("-fx-background-color: #e2e2e2");
    }
    public void changeb2(){
        b2.setStyle("-fx-background-color: #e2e2e2");
        SupPile.setVisible(true);
    }
    public void changeb3(){
        b3.setStyle("-fx-background-color: #e2e2e2");
        Ajouter.setVisible(true);
    }
    public void changeb4(){
        b4.setStyle("-fx-background-color: #e2e2e2");
        Retirer.setVisible(true);
    }

    public void changeb5(){
        ip.setStyle("-fx-background-color: #e2e2e2");
        Importer.setVisible(true);
    }
    public void changeb6(){
        ex.setStyle("-fx-background-color: #e2e2e2");
        Exporter.setVisible(true);
    }


    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");
        b2.setStyle("-fx-background-color: #aeaeae");
        b3.setStyle("-fx-background-color: #aeaeae");
        b4.setStyle("-fx-background-color: #aeaeae");
        ip.setStyle("-fx-background-color: #aeaeae");
        ex.setStyle("-fx-background-color: #aeaeae");
        Retirer.setVisible(false);
        Ajouter.setVisible(false);
        SupPile.setVisible(false);
        Importer.setVisible(false);
        Exporter.setVisible(false);

    }

    public void labelCreation(){
        Creation.setVisible(true);
    }

    public void labelCreation2(){
        Creation.setVisible(false);
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

    public void imp() throws IOException{
        Enregistrer e = new Enregistrer();
        e.importer();
        this.chargement();

    }

    public void exp() throws IOException {
        Enregistrer e = new Enregistrer();
        e.export(nompile.getText());
    }
}
