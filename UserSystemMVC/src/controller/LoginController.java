package controller;


import UI.LoginUI;
import UI.RegisterUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
    private LoginUI loginUI;

    public LoginController(Text title, Stage primaryStage) {
        this.loginUI = new LoginUI(title,primaryStage, new LoginBtnEvent(), new GoToRegisterPageBtnEvent());
    }



    class LoginBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            loginUI.checkLogin();
        }
    }

    class GoToRegisterPageBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            loginUI.goToRegisterPage();
        }
    }


}
