package controller;

import UI.MainMenuUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Admin;
import models.User;



public class MainMenuController {
    private MainMenuUI mainMenuUI;

    public MainMenuController(Text title, Stage primaryStage, Admin admin) {
        mainMenuUI = new MainMenuUI(title,primaryStage,admin,
                new onHomeTabSelectedEvent(),
                new onUpdateTabSelectedEvent(),
                new goToUpdateTabEvent(),
                new logoutBtnEvent(),
                new updateBtnEvent(),
                new onClosedEvent(),
                new DoubleClickGoToAdminUpdateTab(),
                new AdminUpdateTabBtnEvent(),
                new DeleteUserFromAdminUpdateTabEvent(),
                new GoToUsersTableTabEvent(),
                new GoToAdminUpdateTab(),
                new DeleteUserFromContextMenu());
    }

    public MainMenuController(Text title, Stage primaryStage, User user) {
        mainMenuUI = new MainMenuUI(title,primaryStage,user,
                new onHomeTabSelectedEvent(),
                new onUpdateTabSelectedEvent(),
                new goToUpdateTabEvent(),
                new logoutBtnEvent(),
                new updateBtnEvent(),
                new onClosedEvent());
    }



    class onHomeTabSelectedEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.onHomeTabSelected();
        }
    }

    class onUpdateTabSelectedEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.onUpdateTabSelected();
        }
    }

    class goToUpdateTabEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.goToUpdateTab();
        }
    }

    class updateBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.updateInfoFromUpdateTab();
        }
    }

    class logoutBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Logout?");
            alert.setContentText("Do you want to log out?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                mainMenuUI.logoutFromUser();
                mainMenuUI.goToLoginPage();
            }

        }
    }

    class onClosedEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exit or Logout?");
            alert.setContentText("Do you exit or logout?");
            alert.getButtonTypes().setAll(new ButtonType("Logout"), new ButtonType("Exit"), ButtonType.CLOSE);

            ButtonType result = alert.showAndWait().get();
            if (result == alert.getButtonTypes().get(0)) {
                event.consume();
                mainMenuUI.onExitDialogClickLogout();
            } else if (result == alert.getButtonTypes().get(1)) {
                mainMenuUI.logoutFromUser();
            } else if (result == alert.getButtonTypes().get(2)) {
                event.consume();
            } else if(result == ButtonType.CLOSE) {
                event.consume();
            }

        }
    }

    class DoubleClickGoToAdminUpdateTab implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                mainMenuUI.goToAdminUpdateTabFromTable(mainMenuUI.getSelectedUserFromTable());
            }
        }
    }


    class AdminUpdateTabBtnEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.updateFromAdminUpdateTab();
            mainMenuUI.updateUserTableAfterUpdate();
        }
    }

    class DeleteUserFromAdminUpdateTabEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete?");
            alert.setContentText("Do you want to delete this user?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                mainMenuUI.deleteUserFromAdminUpdateTab();
                mainMenuUI.updateUserTableAfterDelete();
            }

        }
    }

    class GoToUsersTableTabEvent implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.goToUsersTableTab();
        }
    }

    class GoToAdminUpdateTab implements EventHandler {

        @Override
        public void handle(Event event) {
            mainMenuUI.goToAdminUpdateTabFromTable(mainMenuUI.getSelectedUserFromTable());
        }
    }

    class DeleteUserFromContextMenu implements EventHandler {
        @Override
        public void handle(Event event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete?");
            alert.setContentText("Do you want to delete this user?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                mainMenuUI.deleteUserFromUserTableTab();
                mainMenuUI.updateUserTableAfterUpdate();
            }

        }
    }


}
