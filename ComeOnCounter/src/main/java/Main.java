package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/main_gui.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Corny Joke Counter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        controller = loader.getController();
    }

    @Override
    public void stop()
    {
        controller.saveToFile();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
