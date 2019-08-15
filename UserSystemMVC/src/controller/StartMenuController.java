package controller;

import UI.StartMenuUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class StartMenuController {
    private StartMenuUI startMenuUI;

    public StartMenuController(Stage primaryStage) {
        startMenuUI = new StartMenuUI(primaryStage, new GoToLoginPageBtnEvent(), new GoToRegisterPageBtnEvent());
    }

    class GoToLoginPageBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            startMenuUI.goToLoginPage();
        }
    }

    class GoToRegisterPageBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            startMenuUI.goToRegisterPage();
        }
    }
}
