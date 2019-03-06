package controlleur.Examen;

import controlleur.ControllerAccueil;
import controlleur.apprentissage.ControlleurSuccess;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Apprentissage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerExamenJeu implements Initializable{

    private Stage stage;
    private Apprentissage apprentissage;
    private Timeline avancement;
    private Timeline timeline;
    private boolean difficulty;

    @FXML
    ProgressIndicator pi;
    @FXML
    Label LabelType;
    @FXML
    Label LabelTheme;
    @FXML
    Label question;
    @FXML
    Label Home;
    @FXML
    Button QCM1;
    @FXML
    Button QCM2;
    @FXML
    Button QCM3;
    @FXML
    Button QCM4;
    @FXML
    Button VRAI;
    @FXML
    Button FAUX;
    @FXML
    TextField reponseClassique;
    @FXML
    ImageView imV;
    @FXML
    ImageView imbar;
    @FXML
    Group gVF;
    @FXML
    Group gQCM;
    @FXML
    Group gArea;
    @FXML
    ProgressBar pb;
    @FXML
    Button btn;



    private int tempsDifficile = 8000;
    private double tempsMax = 15000;
    private double i = 0;
    private double cycle = 50;
    private boolean update = false;
    private int CarteFaite = 0;
    private int nbCarteTotal;
    private Random r = new Random();


    public ControllerExamenJeu(Stage s, Apprentissage app, boolean b){
        stage = s;
        apprentissage = app;
        timeline = new Timeline();
        avancement = new Timeline();
        difficulty = b;
    }



    public void chargereponse(){
        int alea = r.nextInt(3);
        switch (alea){
            case 0:
                imV.setImage(new Image("/Ressources/im_interface/rond1.png"));
                imbar.setImage(new Image("Ressources/im_interface/rect1.png"));
                break;
            case 1:
                imV.setImage(new Image("/Ressources/im_interface/rond2.png"));
                imbar.setImage(new Image("Ressources/im_interface/rect2.png"));
                break;
            case 2:
                imV.setImage(new Image("/Ressources/im_interface/rond3.png"));
                imbar.setImage(new Image("Ressources/im_interface/rect3.png"));
                break;
        }

        gQCM.setVisible(false);

        gVF.setVisible(false);

        gArea.setVisible(false);
        btn.setVisible(false);

        String q = apprentissage.getCarteQuestion();

        question.setText(q);

        String th = apprentissage.getCarteTheme();

        LabelTheme.setText(th);

        String t = apprentissage.getCarteType();

        LabelType.setText(t);

        switch (LabelType.getText()){
            case "QCM" :
                //TODO on change l'imageView de fond avec celle qui correspond à QCM

                int x = r.nextInt(4);
                switch (x){
                    case 0 :
                        QCM1.setText(apprentissage.getCarteReponse());
                        QCM2.setText(apprentissage.getFausseReponse1());
                        QCM3.setText(apprentissage.getFausseReponse2());
                        QCM4.setText(apprentissage.getFausseReponse3());
                        break;
                    case 1:
                        QCM1.setText(apprentissage.getFausseReponse1());
                        QCM2.setText(apprentissage.getCarteReponse());
                        QCM3.setText(apprentissage.getFausseReponse2());
                        QCM4.setText(apprentissage.getFausseReponse3());
                        break;
                    case 2 :
                        QCM1.setText(apprentissage.getFausseReponse2());
                        QCM2.setText(apprentissage.getFausseReponse1());
                        QCM3.setText(apprentissage.getCarteReponse());
                        QCM4.setText(apprentissage.getFausseReponse3());
                        break;
                    case 3 :
                        QCM1.setText(apprentissage.getFausseReponse3());
                        QCM2.setText(apprentissage.getFausseReponse1());
                        QCM3.setText(apprentissage.getFausseReponse2());
                        QCM4.setText(apprentissage.getCarteReponse());
                        break;
                }
                gQCM.setVisible(true);
                break;
            case "Classique" :
                //tempsMax = tempsMax*2;
                gArea.setVisible(true);
                btn.setVisible(true);
                break;
            case "VF" :
                gVF.setVisible(true);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (difficulty){
            tempsMax = tempsDifficile;
        }
        nbCarteTotal = apprentissage.getPileDeCartes().size();

        avancement.getKeyFrames().addAll(
                new KeyFrame(new Duration(10), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (update){
                            update = false;
                            pb.setProgress((double)(CarteFaite)/(double)nbCarteTotal);
                        }
                    }
                })
        );

        avancement.setCycleCount(Timeline.INDEFINITE);
        avancement.play();

        this.chargereponse();

        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(cycle), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (pi.getProgress() != 1){
                            pi.setProgress((double)(cycle*i)/tempsMax);
                            i++;
                        }
                        else {
                            timeline.stop();
                            update = true;
                            CarteFaite++;
                            reponseClassique.setText("");
                            apprentissage.AvancerUneCarte(false);

                            if (!apprentissage.getFini()) {

                                int alea = r.nextInt(3);
                                switch (alea){
                                    case 0:
                                        imV.setImage(new Image("/Ressources/im_interface/rond1.png"));
                                        imbar.setImage(new Image("Ressources/im_interface/rect1.png"));
                                        break;
                                    case 1:
                                        imV.setImage(new Image("/Ressources/im_interface/rond2.png"));
                                        imbar.setImage(new Image("Ressources/im_interface/rect2.png"));
                                        break;
                                    case 2:
                                        imV.setImage(new Image("/Ressources/im_interface/rond3.png"));
                                        imbar.setImage(new Image("Ressources/im_interface/rect3.png"));
                                        break;
                                }

                                gQCM.setVisible(false);

                                gVF.setVisible(false);

                                gArea.setVisible(false);
                                btn.setVisible(false);

                                String q = apprentissage.getCarteQuestion();

                                question.setText(q);

                                String th = apprentissage.getCarteTheme();

                                LabelTheme.setText(th);

                                String t = apprentissage.getCarteType();

                                LabelType.setText(t);

                                switch (LabelType.getText()){
                                    case "QCM" :
                                        //TODO on change l'imageView de fond avec celle qui correspond à QCM

                                        int x = r.nextInt(4);
                                        switch (x){
                                            case 0 :
                                                QCM1.setText(apprentissage.getCarteReponse());
                                                QCM2.setText(apprentissage.getFausseReponse1());
                                                QCM3.setText(apprentissage.getFausseReponse2());
                                                QCM4.setText(apprentissage.getFausseReponse3());
                                                break;
                                            case 1:
                                                QCM1.setText(apprentissage.getFausseReponse1());
                                                QCM2.setText(apprentissage.getCarteReponse());
                                                QCM3.setText(apprentissage.getFausseReponse2());
                                                QCM4.setText(apprentissage.getFausseReponse3());
                                                break;
                                            case 2 :
                                                QCM1.setText(apprentissage.getFausseReponse2());
                                                QCM2.setText(apprentissage.getFausseReponse1());
                                                QCM3.setText(apprentissage.getCarteReponse());
                                                QCM4.setText(apprentissage.getFausseReponse3());
                                                break;
                                            case 3 :
                                                QCM1.setText(apprentissage.getFausseReponse3());
                                                QCM2.setText(apprentissage.getFausseReponse1());
                                                QCM3.setText(apprentissage.getFausseReponse2());
                                                QCM4.setText(apprentissage.getCarteReponse());
                                                break;
                                        }
                                        gQCM.setVisible(true);
                                        break;
                                    case "Classique" :
                                        gArea.setVisible(true);
                                        btn.setVisible(true);
                                        break;
                                    case "VF" :
                                        gVF.setVisible(true);
                                        break;
                                }

                                i = 0;
                                pi.setProgress(0);
                                timeline.playFrom(Duration.ZERO);
                            }
                            else {
                                update = true;
                                CarteFaite++;
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueSuccess.fxml"));
                                loader.setControllerFactory(iC->new ControlleurSuccess(stage, apprentissage,true,difficulty));
                                Parent root = null;
                                try {
                                    root = loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage.setScene(new Scene(root, 1080, 720));
                                stage.show();
                            }
                        }
                    }
                }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void termine() throws IOException {
        //tempsMax = tempsMax/2;
        if (reponseClassique.getText().equals(apprentissage.getCarteReponse())){
            reponseClassique.setText("");
            this.nextJ();
        }
        else {
            reponseClassique.setText("");
            this.nextF();
        }
    }

    public void isTrue() throws IOException {
        if (apprentissage.getCarteReponse().equals("Vrai")){
            this.nextJ();
        }else{
            this.nextF();
        }
    }
    public void isFalse() throws IOException {
        if (apprentissage.getCarteReponse().equals("Faux")){
            this.nextJ();
        }else{
            this.nextF();
        }
    }

    public void clicHome() throws IOException {

        //timeline.pause(); //En commentaire avec le .play + bas, sinon il y a un risque de triche !
        Stage stage2 = new Stage();
        //stage2.initModality(Modality.APPLICATION_MODAL);

        stage2.setTitle("Retour au menu");
        stage2.setMaxWidth(330);
        stage2.setMinWidth(327);
        stage2.setMaxHeight(250);
        stage2.setMinHeight(100);

        Label modalityLabel = new Label("           Voulez-vous vraiment quitter ?\n (Votre avancement ne sera pas sauvegardé)");
        modalityLabel.setTranslateX(12);
        modalityLabel.setTranslateY(10);
        modalityLabel.setMinHeight(30);
        modalityLabel.setMaxHeight(40);
        Button validate = new Button("Oui");
        validate.setTranslateX(87);
        validate.setTranslateY(30);
        validate.setOnAction(e -> {
            stage2.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
            loader.setControllerFactory(iC->new ControllerAccueil(stage));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        });
        Button closeButton = new Button("Non");
        closeButton.setTranslateX(197);
        closeButton.setTranslateY(4);
        closeButton.setOnAction(e -> {
            stage2.close();
            //timeline.play();
        });
        VBox root2 = new VBox();
        root2.getChildren().addAll(modalityLabel, validate, closeButton);
        Scene scene = new Scene(root2, 200, 100);
        stage2.setScene(scene);
        stage2.show();

        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(stage));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1080, 720));
        stage.show();
        */
    }

    public void selected1() throws IOException {
        if (QCM1.getText().equals(apprentissage.getCarteReponse())){
            this.nextJ();
        }
        else {
            this.nextF();
        }
    }
    public void selected2() throws IOException {
        if (QCM2.getText().equals(apprentissage.getCarteReponse())){
            this.nextJ();
        }
        else {
            this.nextF();
        }
    }
    public void selected3() throws IOException {
        if (QCM3.getText().equals(apprentissage.getCarteReponse())){
            this.nextJ();
        }
        else {
            this.nextF();
        }
    }
    public void selected4() throws IOException {
        if (QCM4.getText().equals(apprentissage.getCarteReponse())){
            this.nextJ();
        }
        else {
            this.nextF();
        }
    }

    public void nextJ() throws IOException {
        update = true;
        CarteFaite++;

        apprentissage.AvancerUneCarte(true);

        if (!apprentissage.getFini()) {

            chargereponse();

            i = 0;
            pi.setProgress(0);
            timeline.playFrom(Duration.ZERO);
        }
        else {
            timeline.stop();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueSuccess.fxml"));
            loader.setControllerFactory(iC->new ControlleurSuccess(stage, apprentissage,true,difficulty));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
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

    public void nextF() throws IOException {
        update = true;
        CarteFaite++;
        apprentissage.AvancerUneCarte(false);

        if (!apprentissage.getFini()) {

            this.chargereponse();

            i = 0;
            pi.setProgress(0);
            timeline.playFrom(Duration.ZERO);
        }
        else {
            timeline.stop();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueSuccess.fxml"));
            loader.setControllerFactory(iC->new ControlleurSuccess(stage, apprentissage,true,difficulty));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        }

    }

    public void labelHome(){
        Home.setVisible(true);
    }

    public void labelHome2(){
        Home.setVisible(false);
    }


}
