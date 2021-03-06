/*
    Author: Simon Wang
    Version: 1.0
*/
package Main;

import controller.StartMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new StartMenuController(primaryStage);
    }
}
