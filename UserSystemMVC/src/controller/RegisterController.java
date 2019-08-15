package controller;

import UI.RegisterUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterController {
    private RegisterUI registerUI;

    public RegisterController(Text title, Stage primaryStage) {
        registerUI = new RegisterUI(title,primaryStage, new RegisterBtnEvent(), new GoToLoginPageEvent());
    }

    class GoToLoginPageEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            registerUI.goToLoginPage();
        }
    }

    class RegisterBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            registerUI.register();
        }
    }

}
