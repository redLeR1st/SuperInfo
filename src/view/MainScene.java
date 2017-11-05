package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        primaryStage.setTitle("SuperInfo");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public void start(String[] args) {
        launch(args);
    }

}
