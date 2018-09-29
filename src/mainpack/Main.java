package mainpack;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Main implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            InputStream inputStream =       getClass().getResource("gameon").openStream();
            Pane pane = loader.load(inputStream);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            //scene.getStylesheets().add("/css/stylesheet.css");
            stage.setTitle("New Game");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(true);

            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
