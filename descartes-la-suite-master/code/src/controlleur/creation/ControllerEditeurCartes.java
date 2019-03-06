package controlleur.creation;


import bd.H2Cartes;
import bd.H2Contient;
import controlleur.ControllerAccueil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerEditeurCartes implements Initializable {


    private Stage stage;
    private boolean block = false;
    private ToggleGroup tg = new ToggleGroup();

    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    TextField question;
    @FXML
    TextField reponse;
    @FXML
    Label labelQuestion;
    @FXML
    Label labelReponse;
    @FXML
    Label labelType;
    @FXML
    Label labelTheme;
    @FXML
    RadioButton bC;
    @FXML
    RadioButton bQCM;
    @FXML
    RadioButton bVF;
    @FXML
    RadioButton bPhy;
    @FXML
    RadioButton bHis;
    @FXML
    RadioButton bGeo;
    @FXML
    RadioButton bAut;
    @FXML
    RadioButton bCul;
    @FXML
    RadioButton bMat;
    @FXML
    RadioButton bFra;
    @FXML
    RadioButton bAng;
    @FXML
    FlowPane fpane;
    @FXML
    SplitMenuButton filtre;
    @FXML
    ToggleButton tb1;
    @FXML
    ToggleButton tborange;
    @FXML
    Label poubelle;
    @FXML
    Label Creation;
    @FXML
    Label Home;
    @FXML
    Label Retour;
    @FXML
    Group gvf;
    @FXML
    Group gvf1;
    @FXML
    Group gqcm;
    @FXML
    ImageView VRAI_im;
    @FXML
    ImageView VRAI_im1;
    @FXML
    ImageView FAUX_im;
    @FXML
    ImageView FAUX_im1;
    @FXML
    ToggleButton VRAI1;
    @FXML
    ToggleButton FAUX1;
    @FXML
    TextField rep1;
    @FXML
    TextField rep2;
    @FXML
    TextField rep3;
    @FXML
    TextField rep4;
    @FXML
    Group glqcm;
    @FXML
    Label lrep1;
    @FXML
    Label lrep2;
    @FXML
    Label lrep3;
    @FXML
    Label lrep4;


    public ControllerEditeurCartes(Stage s) {
        stage = s;
    }

    public void chargement(){
        if (!block){
            //TODO regarder toute les pile de la base de donner et mettre leur nom dans le flowpane
            H2Cartes carte = new H2Cartes();
            ArrayList<String> c = carte.visualiserCartes();

            for (int i =0;i<c.size();i++){
                RadioButton rb = new RadioButton();
                rb.setToggleGroup(tg);
                String texteAAllonger = c.get(i);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                rb.setText(texteAAllonger);
                rb.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        if (rb.isSelected()){
                                            String texteARaccourcir = rb.getText();
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
                                            labelQuestion.setText(texteARaccourcir);
                                            H2Cartes c = new H2Cartes();
                                            String rep = c.getRepCarteByQuest(texteARaccourcir);
                                            String them = c.getThemCarteByQuest(texteARaccourcir);
                                            String type = c.getTypeCarteByQuest(texteARaccourcir);
                                            switch (type){
                                                case "Classique":
                                                    gqcm.setVisible(false);
                                                    glqcm.setVisible(false);
                                                    gvf.setVisible(false);
                                                    gvf1.setVisible(false);
                                                    labelReponse.setVisible(true);
                                                    reponse.setVisible(true);
                                                    labelReponse.setText(rep);
                                                    question.setText(labelQuestion.getText());
                                                    reponse.setText(labelReponse.getText());
                                                    break;
                                                case "V/F":
                                                    gqcm.setVisible(false);
                                                    glqcm.setVisible(false);
                                                    labelReponse.setVisible(false);
                                                    reponse.setVisible(false);
                                                    gvf.setVisible(true);
                                                    gvf1.setVisible(true);
                                                    if (rep.equals("Vrai")){
                                                        VRAI1.setSelected(true);
                                                        VRAI_im.setOpacity(1);
                                                        FAUX_im.setOpacity(0.25);
                                                        VRAI_im1.setOpacity(1);
                                                        FAUX_im1.setOpacity(0.25);
                                                    }
                                                    else{
                                                        FAUX1.setSelected(true);
                                                        VRAI_im.setOpacity(0.25);
                                                        FAUX_im.setOpacity(1);
                                                        VRAI_im1.setOpacity(0.25);
                                                        FAUX_im1.setOpacity(1);
                                                    }
                                                    break;
                                                case "QCM":
                                                    gvf.setVisible(false);
                                                    gvf1.setVisible(false);
                                                    gqcm.setVisible(false);
                                                    glqcm.setVisible(true);
                                                    labelReponse.setVisible(false);
                                                    reponse.setVisible(false);
                                                    gqcm.setVisible(true);
                                                    rep1.setText(rep);
                                                    rep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                                    rep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                                    rep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                                    lrep1.setText(rep);
                                                    lrep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                                    lrep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                                    lrep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                                    break;
                                            }
                                            labelTheme.setText(them);
                                            labelType.setText(type);
                                            question.setText(labelQuestion.getText());
                                            //On va aussi les mettre sélectionner avec bouton.setSelected()
                                            if(labelType.getText().equals("Classique")){
                                                bC.setSelected(true);
                                            } else if(labelType.getText().equals("QCM")){
                                                bQCM.setSelected(true);
                                            } else if(labelType.getText().equals("V/F")){
                                                bVF.setSelected(true);
                                            } else {
                                                System.out.println("erreur dans editeur carte");
                                            }
                                            if (labelTheme.getText().equals("Physique")) {
                                                bPhy.setSelected(true);
                                            } else if (labelTheme.getText().equals("Histoire")) {
                                                bHis.setSelected(true);
                                            } else if (labelTheme.getText().equals("Géographie")) {
                                                bGeo.setSelected(true);
                                            } else if (labelTheme.getText().equals("Anglais")) {
                                                bAng.setSelected(true);
                                            } else if (labelTheme.getText().equals("Français")) {
                                                bFra.setSelected(true);
                                            } else if (labelTheme.getText().equals("Culture G.")) {
                                                bCul.setSelected(true);
                                            } else if (labelTheme.getText().equals("Autres")) {
                                                bAut.setSelected(true);
                                            } else if (labelTheme.getText().equals("Maths")) {
                                                bMat.setSelected(true);
                                            } else {
                                                System.out.println("erreur dans editeur carte");
                                            }
                                        }
                                    }
                                });
                fpane.getChildren().add(rb);
            }
            block = true;
        }
    }

    //A modifier
    String q = "";
    String r = "";





    //Pour modifier la question
    public void enterq(){

        //on enlève tous les espaces à la fin de la question pour éviter tout problème
        String texteARaccourcir1 = question.getText();
        texteARaccourcir1 = texteARaccourcir1 + "~#~";
        int longueur11 = texteARaccourcir1.length();
        for(int k=longueur11 ; k>=0; k--){
            String res="";
            for(int l=0; l<k; l++){
                res=res+" ";
            }
            res=res+"~#~";
            if (texteARaccourcir1.contains(res)) {
                texteARaccourcir1 = texteARaccourcir1.replaceFirst(res, "");
            }
        }
        q=texteARaccourcir1;
        H2Cartes carte = new H2Cartes();
        H2Contient contient = new H2Contient();
        if (!carte.isInCarte(q)){
            String qu = labelQuestion.getText();
            labelQuestion.setText(q);
            carte.editCarteQuestion(qu,q); //on change dans la liste de cartes
            contient.editCartePile(qu,q); //on change dans la liste contient
            for (int i=0; i<fpane.getChildren().size(); i++) {
                String texteAAllonger = qu;
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                if (((RadioButton)fpane.getChildren().get(i)).getText().equals(texteAAllonger)){
                    String texteAAllonger2 = q;
                    int longueur2= texteAAllonger2.length();
                    for (int k=longueur2; k<70; k++) {
                        texteAAllonger2 = texteAAllonger2 + " ";
                    }
                    ((RadioButton)(fpane.getChildren().get(i))).setText(texteAAllonger2);
                }
            }
        }else
        {
            //TODO METTRE VICTOR HUGO
        }

    }

    //Pour modifier la réponse
    public void enterr(){
        r = reponse.getText();
        labelReponse.setText(r);
        H2Cartes carte = new H2Cartes();
        carte.editCarteReponse(labelQuestion.getText(),r);
    }
    public void  enter1(){
        r=rep1.getText();
        lrep1.setText(r);
        H2Cartes carte = new H2Cartes();
        carte.editCarteReponse(labelQuestion.getText(),r);
        lrep1.setText(rep1.getText());
    }
    public void  enter2(){
        r=rep2.getText();
        lrep2.setText(r);
        H2Cartes carte = new H2Cartes();
        carte.editRep1(labelQuestion.getText(),rep2.getText());
        lrep2.setText(rep2.getText());
    }
    public void  enter3(){
        r=rep3.getText();
        lrep3.setText(r);
        H2Cartes carte = new H2Cartes();
        carte.editRep2(labelQuestion.getText(),rep3.getText());
        lrep3.setText(rep3.getText());
    }
    public void  enter4(){
        r=rep4.getText();
        lrep4.setText(r);
        H2Cartes carte = new H2Cartes();
        carte.editRep3(labelQuestion.getText(),rep4.getText());
        lrep4.setText(rep4.getText());
    }

    public void clicVrai(){
        VRAI_im.setOpacity(1);
        FAUX_im.setOpacity(0.25);
        VRAI_im1.setOpacity(1);
        FAUX_im1.setOpacity(0.25);
        H2Cartes carte = new H2Cartes();
        carte.editCarteReponse(labelQuestion.getText(),"Vrai");
    }
    public void clicFaux(){
        VRAI_im.setOpacity(0.25);
        FAUX_im.setOpacity(1);
        VRAI_im1.setOpacity(0.25);
        FAUX_im1.setOpacity(1);
        H2Cartes carte = new H2Cartes();
        carte.editCarteReponse(labelQuestion.getText(),"Faux");
    }


    // Commande pour changer le type de la carte (Je l'ai supprimé car on ne change pas le type d'une carte !)
    //Après comme y a des boutons soit on les enlève soit ils servent à quelque chose sinon c'est bizarre
    public void typeactive() {
        if(labelType.getText().equals("Classique")){
            bC.setSelected(true);
        } else if(labelType.getText().equals("QCM")){
            bQCM.setSelected(true);
        } else if(labelType.getText().equals("V/F")){
            bVF.setSelected(true);
        } else {
            System.out.println("erreur dans editeur carte");
        }
    }
    /*public void typeactive(){
        if (bC.isSelected()){
            reponse.setVisible(true);
            labelReponse.setVisible(true);
            labelType.setText("Classique");
            gvf.setVisible(false);
            gvf1.setVisible(false);
            glqcm.setVisible(false);
            gqcm.setVisible(false);
            question.setText(labelQuestion.getText());
            reponse.setText(labelReponse.getText());
        }
        else if (bVF.isSelected()) {
            glqcm.setVisible(false);
            gqcm.setVisible(false);
            labelType.setText("Vrai/Faux");
            labelReponse.setVisible(false);
            reponse.setVisible(false);
            gvf.setVisible(true);
            gvf1.setVisible(true);
            if (labelReponse.equals("Vrai")){
                VRAI1.setSelected(true);
                VRAI_im.setOpacity(1);
                FAUX_im.setOpacity(0.5);
                VRAI_im1.setOpacity(1);
                FAUX_im1.setOpacity(0.5);
            }
            else{
                FAUX1.setSelected(true);
                VRAI_im.setOpacity(0.5);
                FAUX_im.setOpacity(1);
                VRAI_im1.setOpacity(0.5);
                FAUX_im1.setOpacity(1);
            }
        }
        else if (bQCM.isSelected()) {
            labelType.setText("QCM");
            labelReponse.setVisible(false);
            reponse.setVisible(false);
            gvf.setVisible(false);
            gvf1.setVisible(false);
            gvf1.setVisible(true);
            glqcm.setVisible(true);
        }
        else {
            labelType.setText("");
        }
        H2Cartes carte = new H2Cartes();
        carte.editCarteType(labelQuestion.getText(),labelType.getText());
    }
    */

    // Commande pour valider le theme de la carte
    public void themeactive() {
        if (bPhy.isSelected()) {
            labelTheme.setText("Physique");
        } else if (bHis.isSelected()) {
            labelTheme.setText("Histoire");
        } else if (bGeo.isSelected()) {
            labelTheme.setText("Géographie");
        } else if (bAng.isSelected()) {
            labelTheme.setText("Anglais");
        } else if (bFra.isSelected()) {
            labelTheme.setText("Français");
        } else if (bCul.isSelected()) {
            labelTheme.setText("Culture G.");
        } else if (bAut.isSelected()) {
            labelTheme.setText("Autres");
        } else if (bMat.isSelected()) {
            labelTheme.setText("Maths");
        } else {
            labelTheme.setText("");
        }
        if (labelTheme.getText().equals("Culture G.")){
            String s = "Culture générale";
            H2Cartes cartes = new H2Cartes();
            cartes.editCarteTheme(labelQuestion.getText(),s);
        }
        else {
            H2Cartes cartes = new H2Cartes();
            cartes.editCarteTheme(labelQuestion.getText(), labelTheme.getText());
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
    //vider les champs de textes
    public void clicSupprimer() throws IOException {

        Stage stage2 = new Stage();
        //stage2.initModality(Modality.APPLICATION_MODAL);

        stage2.setTitle("Suppression de carte");
        stage2.setMaxWidth(320);
        stage2.setMinWidth(317);
        stage2.setMaxHeight(120);
        stage2.setMinHeight(100);

        Label modalityLabel = new Label("Voulez-vous vraiment supprimer cette carte ?");
        modalityLabel.setTranslateX(10);
        modalityLabel.setTranslateY(10);
        Button validate = new Button("Oui");
        validate.setTranslateX(80);
        validate.setTranslateY(30);
        validate.setOnAction(e -> {
            stage2.close();
            H2Cartes c = new H2Cartes();
            c.supprimerCarte(labelQuestion.getText());

            for(int i=0 ; i<fpane.getChildren().size(); i++){
                String texteARaccourcir = ((RadioButton)fpane.getChildren().get(i)).getText();
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
                if(labelQuestion.getText().equals(texteARaccourcir)) {
                    fpane.getChildren().remove(i);
                }
            }

            labelQuestion.setText("");
            labelReponse.setText("");
            labelType.setText("");
            labelTheme.setText("");
            question.setText("");
            reponse.setText("");
            gvf.setVisible(false);
            gvf1.setVisible(false);
            gqcm.setVisible(false);
            glqcm.setVisible(false);
        });
        Button closeButton = new Button("Non");
        closeButton.setTranslateX(183);
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

    public void clicFiltre(){

        //On efface se qui est déjà la

        boolean afficherTout = true; //On le met à false dès qu'on trouve quelquechose de coché
        boolean aCocheUnType = false;
        boolean aCocheUnTheme = false;

        ArrayList<String> res = new ArrayList<String>();

        int n = fpane.getChildren().size();

        while (n > 0){
            fpane.getChildren().remove(0);
            n--;
        }
        // On traite le filtrage par type
        for (int i = 0; i < 3 ; i++) {
            if (((RadioMenuItem) ((Menu)filtre.getItems().get(0)).getItems().get(i)).isSelected()){
                afficherTout=false;
                aCocheUnType=true;
                H2Cartes cartes = new H2Cartes();
                ArrayList<String> c = cartes.getQuestionByType((((Menu)filtre.getItems().get(0)).getItems().get(i)).getText());
                for (int j=0;j<c.size();j++){
                    res.add(c.get(j));
                    //RadioButton rb = new RadioButton((c.get(j)));
                    //rb.setToggleGroup(tg);
                    //fpane.getChildren().addAll(rb);
                }
            }
        }
        // On traite le filtrage par theme
        for (int i = 0 ; i < 8 ; i++){
            if (((RadioMenuItem) ((Menu)filtre.getItems().get(1)).getItems().get(i)).isSelected()){
                afficherTout=false;
                aCocheUnTheme=true;

                H2Cartes cartes = new H2Cartes();
                ArrayList<String> c = cartes.getQuestionByTheme((((Menu)filtre.getItems().get(1)).getItems().get(i)).getText());
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
                            RadioButton rb = new RadioButton();
                            rb.setToggleGroup(tg);
                            String texteAAllonger = questionAAfficher;
                            int longueur = texteAAllonger.length();
                            for (int k=longueur; k<70; k++) {
                                texteAAllonger = texteAAllonger + " ";
                            }
                            rb.setText(texteAAllonger);
                            rb.setOnAction( new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if (rb.isSelected()){
                                        String texteARaccourcir = rb.getText();
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
                                        labelQuestion.setText(texteARaccourcir);
                                        H2Cartes c = new H2Cartes();
                                        String rep = c.getRepCarteByQuest(texteARaccourcir);
                                        String them = c.getThemCarteByQuest(texteARaccourcir);
                                        String type = c.getTypeCarteByQuest(texteARaccourcir);
                                        switch (type){
                                            case "Classique":
                                                gqcm.setVisible(false);
                                                glqcm.setVisible(false);
                                                reponse.setVisible(true);
                                                gvf.setVisible(false);
                                                gvf1.setVisible(false);
                                                labelReponse.setText(rep);
                                                question.setText(labelQuestion.getText());
                                                reponse.setText(labelReponse.getText());
                                                break;
                                            case "V/F":
                                                gqcm.setVisible(false);
                                                glqcm.setVisible(false);
                                                labelReponse.setText(rep);
                                                labelReponse.setVisible(false);
                                                reponse.setVisible(false);
                                                gvf.setVisible(true);
                                                gvf1.setVisible(true);
                                                if (rep.equals("Vrai")){
                                                    VRAI_im.setOpacity(1);
                                                    FAUX_im.setOpacity(0.25);
                                                    VRAI_im1.setOpacity(1);
                                                    FAUX_im1.setOpacity(0.25);
                                                }
                                                else{
                                                    VRAI_im.setOpacity(0.25);
                                                    FAUX_im.setOpacity(1);
                                                    VRAI_im1.setOpacity(0.25);
                                                    FAUX_im1.setOpacity(1);
                                                }
                                                break;
                                            case "QCM":
                                                gvf.setVisible(false);
                                                gvf1.setVisible(false);
                                                gqcm.setVisible(false);
                                                glqcm.setVisible(true);
                                                labelReponse.setVisible(false);
                                                reponse.setVisible(false);
                                                gqcm.setVisible(true);
                                                rep1.setText(rep);
                                                rep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                                rep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                                rep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                                lrep1.setText(rep);
                                                lrep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                                lrep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                                lrep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                                break;
                                        }
                                        labelTheme.setText(them);
                                        labelType.setText(type);
                                        question.setText(labelQuestion.getText());
                                        //On va aussi les mettre sélectionner avec bouton.setSelected()
                                        if(labelType.getText().equals("Classique")){
                                            bC.setSelected(true);
                                        } else if(labelType.getText().equals("QCM")){
                                            bQCM.setSelected(true);
                                        } else if(labelType.getText().equals("V/F")){
                                            bVF.setSelected(true);
                                        } else {
                                            System.out.println("erreur dans editeur carte");
                                        }
                                        if (labelTheme.getText().equals("Physique")) {
                                            bPhy.setSelected(true);
                                        } else if (labelTheme.getText().equals("Histoire")) {
                                            bHis.setSelected(true);
                                        } else if (labelTheme.getText().equals("Géographie")) {
                                            bGeo.setSelected(true);
                                        } else if (labelTheme.getText().equals("Anglais")) {
                                            bAng.setSelected(true);
                                        } else if (labelTheme.getText().equals("Français")) {
                                            bFra.setSelected(true);
                                        } else if (labelTheme.getText().equals("Culture G.")) {
                                            bCul.setSelected(true);
                                        } else if (labelTheme.getText().equals("Autres")) {
                                            bAut.setSelected(true);
                                        } else if (labelTheme.getText().equals("Maths")) {
                                            bMat.setSelected(true);
                                        } else {
                                            System.out.println("erreur dans editeur carte");
                                        }
                                    }
                                }
                            });
                            fpane.getChildren().addAll(rb);
                        }
                    }
                } else { //Si l'utilisateur n'a pas coché de type
                    for (int j = 0; j < c.size(); j++) {
                        RadioButton rb = new RadioButton();
                        rb.setToggleGroup(tg);
                        String texteAAllonger = c.get(j);
                        int longueur = texteAAllonger.length();
                        for (int k=longueur; k<70; k++) {
                            texteAAllonger = texteAAllonger + " ";
                        }
                        rb.setText(texteAAllonger);
                        rb.setOnAction( new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (rb.isSelected()){
                                    String texteARaccourcir = rb.getText();
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
                                    labelQuestion.setText(texteARaccourcir);
                                    H2Cartes c = new H2Cartes();
                                    String rep = c.getRepCarteByQuest(texteARaccourcir);
                                    String them = c.getThemCarteByQuest(texteARaccourcir);
                                    String type = c.getTypeCarteByQuest(texteARaccourcir);
                                    switch (type){
                                        case "Classique":
                                            gqcm.setVisible(false);
                                            glqcm.setVisible(false);
                                            reponse.setVisible(true);
                                            gvf.setVisible(false);
                                            gvf1.setVisible(false);
                                            labelReponse.setText(rep);
                                            question.setText(labelQuestion.getText());
                                            reponse.setText(labelReponse.getText());
                                            break;
                                        case "V/F":
                                            gqcm.setVisible(false);
                                            glqcm.setVisible(false);
                                            labelReponse.setText(rep);
                                            labelReponse.setVisible(false);
                                            reponse.setVisible(false);
                                            gvf.setVisible(true);
                                            gvf1.setVisible(true);
                                            if (rep.equals("Vrai")){
                                                VRAI_im.setOpacity(1);
                                                FAUX_im.setOpacity(0.25);
                                                VRAI_im1.setOpacity(1);
                                                FAUX_im1.setOpacity(0.25);
                                            }
                                            else{
                                                VRAI_im.setOpacity(0.25);
                                                FAUX_im.setOpacity(1);
                                                VRAI_im1.setOpacity(0.25);
                                                FAUX_im1.setOpacity(1);
                                            }
                                            break;
                                        case "QCM":
                                            gvf.setVisible(false);
                                            gvf1.setVisible(false);
                                            gqcm.setVisible(false);
                                            glqcm.setVisible(true);
                                            labelReponse.setVisible(false);
                                            reponse.setVisible(false);
                                            gqcm.setVisible(true);
                                            rep1.setText(rep);
                                            rep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                            rep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                            rep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                            lrep1.setText(rep);
                                            lrep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                            lrep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                            lrep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                            break;
                                    }
                                    labelTheme.setText(them);
                                    labelType.setText(type);
                                    question.setText(labelQuestion.getText());
                                    //On va aussi les mettre sélectionner avec bouton.setSelected()
                                    if(labelType.getText().equals("Classique")){
                                        bC.setSelected(true);
                                    } else if(labelType.getText().equals("QCM")){
                                        bQCM.setSelected(true);
                                    } else if(labelType.getText().equals("V/F")){
                                        bVF.setSelected(true);
                                    } else {
                                        System.out.println("erreur dans editeur carte");
                                    }
                                    if (labelTheme.getText().equals("Physique")) {
                                        bPhy.setSelected(true);
                                    } else if (labelTheme.getText().equals("Histoire")) {
                                        bHis.setSelected(true);
                                    } else if (labelTheme.getText().equals("Géographie")) {
                                        bGeo.setSelected(true);
                                    } else if (labelTheme.getText().equals("Anglais")) {
                                        bAng.setSelected(true);
                                    } else if (labelTheme.getText().equals("Français")) {
                                        bFra.setSelected(true);
                                    } else if (labelTheme.getText().equals("Culture G.")) {
                                        bCul.setSelected(true);
                                    } else if (labelTheme.getText().equals("Autres")) {
                                        bAut.setSelected(true);
                                    } else if (labelTheme.getText().equals("Maths")) {
                                        bMat.setSelected(true);
                                    } else {
                                        System.out.println("erreur dans editeur carte");
                                    }
                                }
                            }
                        });
                        fpane.getChildren().addAll(rb);
                    }
                }
            }
        }

        if (!aCocheUnTheme){
            for (int j = 0; j < res.size(); j++) {
                RadioButton rb = new RadioButton();
                rb.setToggleGroup(tg);
                String texteAAllonger = res.get(j);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                rb.setText(texteAAllonger);
                rb.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (rb.isSelected()){
                            String texteARaccourcir = rb.getText();
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
                            labelQuestion.setText(texteARaccourcir);
                            H2Cartes c = new H2Cartes();
                            String rep = c.getRepCarteByQuest(texteARaccourcir);
                            String them = c.getThemCarteByQuest(texteARaccourcir);
                            String type = c.getTypeCarteByQuest(texteARaccourcir);
                            switch (type){
                                case "Classique":
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(false);
                                    reponse.setVisible(true);
                                    gvf.setVisible(false);
                                    gvf1.setVisible(false);
                                    labelReponse.setText(rep);
                                    question.setText(labelQuestion.getText());
                                    reponse.setText(labelReponse.getText());
                                    break;
                                case "V/F":
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(false);
                                    labelReponse.setText(rep);
                                    labelReponse.setVisible(false);
                                    reponse.setVisible(false);
                                    gvf.setVisible(true);
                                    gvf1.setVisible(true);
                                    if (rep.equals("Vrai")){
                                        VRAI_im.setOpacity(1);
                                        FAUX_im.setOpacity(0.25);
                                        VRAI_im1.setOpacity(1);
                                        FAUX_im1.setOpacity(0.25);
                                    }
                                    else{
                                        VRAI_im.setOpacity(0.25);
                                        FAUX_im.setOpacity(1);
                                        VRAI_im1.setOpacity(0.25);
                                        FAUX_im1.setOpacity(1);
                                    }
                                    break;
                                case "QCM":
                                    gvf.setVisible(false);
                                    gvf1.setVisible(false);
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(true);
                                    labelReponse.setVisible(false);
                                    reponse.setVisible(false);
                                    gqcm.setVisible(true);
                                    rep1.setText(rep);
                                    rep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                    rep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                    rep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                    lrep1.setText(rep);
                                    lrep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                    lrep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                    lrep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                    break;
                            }
                            labelTheme.setText(them);
                            labelType.setText(type);
                            question.setText(labelQuestion.getText());
                            //On va aussi les mettre sélectionner avec bouton.setSelected()
                            if(labelType.getText().equals("Classique")){
                                bC.setSelected(true);
                            } else if(labelType.getText().equals("QCM")){
                                bQCM.setSelected(true);
                            } else if(labelType.getText().equals("V/F")){
                                bVF.setSelected(true);
                            } else {
                                System.out.println("erreur dans editeur carte");
                            }
                            if (labelTheme.getText().equals("Physique")) {
                                bPhy.setSelected(true);
                            } else if (labelTheme.getText().equals("Histoire")) {
                                bHis.setSelected(true);
                            } else if (labelTheme.getText().equals("Géographie")) {
                                bGeo.setSelected(true);
                            } else if (labelTheme.getText().equals("Anglais")) {
                                bAng.setSelected(true);
                            } else if (labelTheme.getText().equals("Français")) {
                                bFra.setSelected(true);
                            } else if (labelTheme.getText().equals("Culture G.")) {
                                bCul.setSelected(true);
                            } else if (labelTheme.getText().equals("Autres")) {
                                bAut.setSelected(true);
                            } else if (labelTheme.getText().equals("Maths")) {
                                bMat.setSelected(true);
                            } else {
                                System.out.println("erreur dans editeur carte");
                            }
                        }
                    }
                });
                fpane.getChildren().addAll(rb);
            }
        }


        if (afficherTout){
            H2Cartes carte = new H2Cartes();
            ArrayList<String> c = carte.visualiserCartes();

            for (int i =0;i<c.size();i++){
                RadioButton rb = new RadioButton();
                rb.setToggleGroup(tg);
                String texteAAllonger = c.get(i);
                int longueur = texteAAllonger.length();
                for (int k=longueur; k<70; k++) {
                    texteAAllonger = texteAAllonger + " ";
                }
                rb.setText(texteAAllonger);
                rb.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (rb.isSelected()){
                            String texteARaccourcir = rb.getText();
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
                            labelQuestion.setText(texteARaccourcir);
                            H2Cartes c = new H2Cartes();
                            String rep = c.getRepCarteByQuest(texteARaccourcir);
                            String them = c.getThemCarteByQuest(texteARaccourcir);
                            String type = c.getTypeCarteByQuest(texteARaccourcir);

                            switch (type){
                                case "Classique":
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(false);
                                    reponse.setVisible(true);
                                    gvf.setVisible(false);
                                    gvf1.setVisible(false);
                                    labelReponse.setText(rep);
                                    question.setText(labelQuestion.getText());
                                    reponse.setText(labelReponse.getText());
                                    break;
                                case "V/F":
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(false);
                                    labelReponse.setText(rep);
                                    labelReponse.setVisible(false);
                                    reponse.setVisible(false);
                                    gvf.setVisible(true);
                                    gvf1.setVisible(true);
                                    if (rep.equals("Vrai")){
                                        VRAI_im.setOpacity(1);
                                        FAUX_im.setOpacity(0.25);
                                        VRAI_im1.setOpacity(1);
                                        FAUX_im1.setOpacity(0.25);
                                    }
                                    else{
                                        VRAI_im.setOpacity(0.25);
                                        FAUX_im.setOpacity(1);
                                        VRAI_im1.setOpacity(0.25);
                                        FAUX_im1.setOpacity(1);
                                    }
                                    break;
                                case "QCM":
                                    gvf.setVisible(false);
                                    gvf1.setVisible(false);
                                    gqcm.setVisible(false);
                                    glqcm.setVisible(true);
                                    labelReponse.setVisible(false);
                                    reponse.setVisible(false);
                                    gqcm.setVisible(true);
                                    rep1.setText(rep);
                                    rep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                    rep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                    rep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                    lrep1.setText(rep);
                                    lrep2.setText(c.getRep1ByQuest(labelQuestion.getText()));
                                    lrep3.setText(c.getRep2ByQuest(labelQuestion.getText()));
                                    lrep4.setText(c.getRep3ByQuest(labelQuestion.getText()));
                                    break;
                            }

                            labelType.setText(type);
                            labelTheme.setText(them);
                            question.setText(labelQuestion.getText());
                            //On va aussi les mettre sélectionner avec bouton.setSelected()
                            if(labelType.getText().equals("Classique")){
                                bC.setSelected(true);
                            } else if(labelType.getText().equals("QCM")){
                                bQCM.setSelected(true);
                            } else if(labelType.getText().equals("V/F")){
                                bVF.setSelected(true);
                            } else {
                                System.out.println("erreur dans editeur carte");
                            }
                            if (labelTheme.getText().equals("Physique")) {
                                bPhy.setSelected(true);
                            } else if (labelTheme.getText().equals("Histoire")) {
                                bHis.setSelected(true);
                            } else if (labelTheme.getText().equals("Géographie")) {
                                bGeo.setSelected(true);
                            } else if (labelTheme.getText().equals("Anglais")) {
                                bAng.setSelected(true);
                            } else if (labelTheme.getText().equals("Français")) {
                                bFra.setSelected(true);
                            } else if (labelTheme.getText().equals("Culture G.")) {
                                bCul.setSelected(true);
                            } else if (labelTheme.getText().equals("Autres")) {
                                bAut.setSelected(true);
                            } else if (labelTheme.getText().equals("Maths")) {
                                bMat.setSelected(true);
                            } else {
                                System.out.println("erreur dans editeur carte");
                            }
                        }
                    }
                });
                fpane.getChildren().add(rb);
            }
        }
    }


    /*public void clicEditer() {
        int n = fpane.getChildren().size();
        int i = 0;
        String texte;
        while (i < n) {
            if (((RadioButton) fpane.getChildren().get(i)).isSelected()) {
                texte = ((RadioButton) fpane.getChildren().get(i)).getText();
                labelQuestion.setText(texte);
                H2Cartes c = new H2Cartes();
                String rep = c.getRepCarteByQuest(texte);
                String them = c.getThemCarteByQuest(texte);
                String type = c.getTypeCarteByQuest(texte);
                System.out.println(type);
                labelReponse.setText(rep);
                labelTheme.setText(them);
                labelType.setText(type);
                question.setText(labelQuestion.getText());
                reponse.setText(labelReponse.getText());
            }
            i++;
        }
    }*/

    public void isSelect() throws IOException {
        tb1.setStyle("-fx-background-color: #ef8a26");
        tborange.setStyle("-fx-background-color: #565656");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/menuCreation/VueCreateurCartesClassique.fxml"));
        loader.setControllerFactory(iC->new ControllerCreateurCartes(stage));
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
        poubelle.setVisible(true);
    }

    public void x(){
        b1.setStyle("-fx-background-color: #aeaeae");
        b2.setStyle("-fx-background-color: #aeaeae");
        poubelle.setVisible(false);
    }

    public void labelCreation(){
        Creation.setVisible(true);
    }

    public void labelCreation2() {
        Creation.setVisible(false);
    }

    public void labelHome(){
        Home.setVisible(true);
    }

    public void labelHome2() {
        Home.setVisible(false);
    }

    public void labelRetour(){
        Retour.setVisible(true);
    }

    public void labelRetour2() {
        Retour.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chargement();
    }
}
