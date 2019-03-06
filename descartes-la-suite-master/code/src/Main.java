import bd.*;
import com.sun.corba.se.impl.interceptors.PICurrent;
import controlleur.ControllerAccueil;
import controlleur.creation.ControllerCreateurCartes;
import controlleur.creation.ControllerEditeurCartes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("DesCartes");
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinWidth(1080);
        primaryStage.setMaxHeight(765);
        primaryStage.setMinHeight(720);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vue/VueAccueil.fxml"));
        loader.setControllerFactory(iC->new ControllerAccueil(primaryStage));
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 1200, 800));

        primaryStage.show();
    }

    public static void main(String[] args) throws IOException  {

        GenererBD generator = new GenererBD();

        generator.iniateBD();

        H2Stat stat = new H2Stat();

        Enregistrer e = new Enregistrer();
        //e.export("Capitales européennes");
        //e.importer();
        launch(args);
    }
}
