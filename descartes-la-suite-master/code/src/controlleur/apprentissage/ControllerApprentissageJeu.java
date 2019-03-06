package controlleur.apprentissage;

import controlleur.ControllerAccueil;
import controlleur.creation.ControllerCreation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Apprentissage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerApprentissageJeu implements Initializable{

    private Stage stage;
    private Apprentissage apprentissage;
    private Timeline timeline;
    private Timeline avancement;
    private int nbCarteTotal;
    private boolean update = false;
    @FXML
    ProgressIndicator pi;
    @FXML
    Button juste;
    @FXML
    Button faux;
    @FXML
    Button btn_termine;
    @FXML
    Label LabelType;
    @FXML
    Label LabelTheme;
    @FXML
    Label reponse;
    @FXML
    Label home;
    @FXML
    Label question;
    @FXML
    ImageView cacheR;
    @FXML
    ProgressBar pb;

    private double tempsMax = 10000;
    private double i = 0;
    private double cycle = 10;





    public ControllerApprentissageJeu(Stage s, Apprentissage app){
        stage = s;
        apprentissage = app;
        timeline = new Timeline();
        avancement = new Timeline();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nbCarteTotal = apprentissage.getPileDeCartes().size();

        String q = apprentissage.getCarteQuestion();

        question.setText(q);

        reponse.setText(apprentissage.getCarteReponse());

        String th = apprentissage.getCarteTheme();

        LabelTheme.setText(th);

        String t = apprentissage.getCarteType();

        LabelType.setText(t);

        avancement.getKeyFrames().addAll(
                new KeyFrame(new Duration(10), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (update){
                            update = false;
                            pb.setProgress((double)(nbCarteTotal-apprentissage.getNbrCartesDifferentesDeZero())/(double)nbCarteTotal);
                        }
                    }
                })
        );

        avancement.setCycleCount(Timeline.INDEFINITE);
        avancement.play();

        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(cycle), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (pi.getProgress() != 1){
                            pi.setProgress((double)(cycle*i)/tempsMax);
                            i++;
                        }
                        else {
                            i = 0;
                            pi.setProgress(0);
                            timeline.stop();
                            cacheR.setVisible(false);
                            juste.setVisible(true);
                            faux.setVisible(true);
                            btn_termine.setVisible(false);
                            pi.setVisible(false);
                            juste.setStyle("-fx-background-color: #e2e2e2");
                            faux.setStyle("-fx-background-color: #e2e2e2");
                        }
                    }
                }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void termine(){
        timeline.stop();
        juste.setVisible(true);
        faux.setVisible(true);
        btn_termine.setVisible(false);
        pi.setVisible(false);
        cacheR.setVisible(false);
        juste.setStyle("-fx-background-color: #e2e2e2");
        faux.setStyle("-fx-background-color: #e2e2e2");
    }

    public void nextJ() throws IOException {
        i = 0;
        update = true;

        apprentissage.AvancerUneCarte(true);

        if (!apprentissage.getFini()) {


            String q = apprentissage.getCarteQuestion();

            question.setText(q);

            reponse.setText(apprentissage.getCarteReponse());

            String th = apprentissage.getCarteTheme();

            LabelTheme.setText(th);

            String t = apprentissage.getCarteType();

            LabelType.setText(t);

            timeline.playFrom(Duration.ZERO);


            juste.setVisible(false);
            faux.setVisible(false);
            btn_termine.setVisible(true);
            pi.setVisible(true);
            cacheR.setVisible(true);
            juste.setStyle("-fx-background-color: #e2e2e2");
            faux.setStyle("-fx-background-color: #e2e2e2");
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vue/menuApprentissage/VueSuccess.fxml"));
            loader.setControllerFactory(iC->new ControlleurSuccess(stage, apprentissage,false,true));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1080, 720));
            stage.show();
        }
    }

    public void labelHome() {
        home.setVisible(true);
    }
    public void labelHome2() {
        home.setVisible(false);
    }

    public void clicHOME() throws IOException {

        timeline.pause(); //En commentaire avec le .play + bas, sinon il y a un risque de triche !
        Stage stage2 = new Stage();
        //stage2.initModality(Modality.APPLICATION_MODAL);

        stage2.setTitle("Retour au menu");
        stage2.setMaxWidth(330);
        stage2.setMinWidth(327);
        stage2.setMaxHeight(120);
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
            timeline.play();
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

    public void nextF(){
        i = 0;
        apprentissage.AvancerUneCarte(false);

            String q = apprentissage.getCarteQuestion();

            question.setText(q);

            reponse.setText(apprentissage.getCarteReponse());

            String th = apprentissage.getCarteTheme();

            LabelTheme.setText(th);

            String t = apprentissage.getCarteType();

            LabelType.setText(t);


            timeline.playFrom(Duration.ZERO);

            juste.setVisible(false);
            faux.setVisible(false);
            btn_termine.setVisible(true);
            pi.setVisible(true);
            cacheR.setVisible(true);
            juste.setStyle("-fx-background-color: #e2e2e2");
            faux.setStyle("-fx-background-color: #e2e2e2");
    }
}
