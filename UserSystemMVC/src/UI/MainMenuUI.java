package UI;

import controller.LoginController;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Admin;
import models.User;


public class MainMenuUI {
    private BorderPane pane;
    private Text title;
    private HBox header;
    private TabPane tabs;
    private HomeTab homeTab;
    private UpdateTab updateTab;
    private AdminTab adminTab;
    private User user;
    private Button logoutBtn;
    private HBox bottomButtonHBox;
    private Stage primaryStage;
    private Admin admin;
    private AdminUpdateTab adminUpdateTab;
    private UsersTableTab usersTableTab;


    public MainMenuUI(Text title, Stage primaryStage, Admin admin,
                      EventHandler onHomeTabSelectedEvent,
                      EventHandler onUpdateTabSelectedEvent,
                      EventHandler goToUpdateTabEvent,
                      EventHandler logoutButtonEvent,
                      EventHandler updateBtnEvent,
                      EventHandler onClosedEvent,
                      EventHandler DoubleClickGoToAdminUpdateTab,
                      EventHandler AdminUpdateTabBtnEvent,
                      EventHandler DeleteUserFromAdminEvent,
                      EventHandler goToUsersTableTabEvent,
                      EventHandler GoToAdminUpdateTab,
                      EventHandler DeleteUserFromContextMenu) {

        this.admin = admin;
        createStage(title,primaryStage,admin, onHomeTabSelectedEvent, onUpdateTabSelectedEvent, goToUpdateTabEvent, logoutButtonEvent, updateBtnEvent, onClosedEvent);
        adminTab = new AdminTab(admin, DoubleClickGoToAdminUpdateTab, AdminUpdateTabBtnEvent, DeleteUserFromAdminEvent, goToUsersTableTabEvent, GoToAdminUpdateTab, DeleteUserFromContextMenu);
        tabs.getTabs().add(1,adminTab.getAdminTab());
    }

    public MainMenuUI(Text title, Stage primaryStage, User user,
                      EventHandler onHomeTabSelectedEvent,
                      EventHandler onUpdateTabSelectedEvent,
                      EventHandler goToUpdateTabEvent,
                      EventHandler logoutButtonEvent,
                      EventHandler updateBtnEvent,
                      EventHandler onClosedEvent) {

        createStage(title,primaryStage,user, onHomeTabSelectedEvent, onUpdateTabSelectedEvent, goToUpdateTabEvent, logoutButtonEvent, updateBtnEvent, onClosedEvent);
    }



    public void createStage(Text title, Stage primaryStage, User user,
                            EventHandler onHomeTabSelectedEvent,
                            EventHandler onUpdateTabSelectedEvent,
                            EventHandler goToUpdateTabEvent,
                            EventHandler logoutButtonEvent,
                            EventHandler updateBtnEvent,
                            EventHandler onClosedEvent) {

        this.primaryStage = primaryStage;
        this.pane = new BorderPane();
        this.user = user;
        this.title = title;
        this.title.setFont(Font.font(35));
        this.header = new HBox();

        header.getChildren().add(title);
        header.setAlignment(Pos.CENTER);

        this.tabs = new TabPane();
        this.homeTab = new HomeTab(this.user, goToUpdateTabEvent);
        this.updateTab = new UpdateTab(this.user, updateBtnEvent);


        tabs.getTabs().addAll(homeTab.getHomeTab(),updateTab.getUpdateTab());

        homeTab.getHomeTab().setOnSelectionChanged(onHomeTabSelectedEvent);
        updateTab.getUpdateTab().setOnSelectionChanged(onUpdateTabSelectedEvent);

        this.bottomButtonHBox = new HBox();
        this.logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(logoutButtonEvent);

        bottomButtonHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomButtonHBox.getChildren().add(logoutBtn);
        bottomButtonHBox.setPadding(new Insets(10));

        pane.setTop(header);
        pane.setCenter(tabs);
        pane.setBottom(bottomButtonHBox);


        Scene scene = new Scene(getHomePane(),750,500);
        primaryStage.setOnCloseRequest(onClosedEvent);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void onHomeTabSelected() {
        if (homeTab.getHomeTab().isSelected()) {
            homeTab.updateLabels();
            homeTab.resetHyperLinks();
        }
    }

    public void onUpdateTabSelected() {
        if (updateTab.getUpdateTab().isSelected()) {
            updateTab.resetStatusLabel();

        }
    }

    public void updateUserTableAfterDelete() {
        tabs.getTabs().remove(tabs.getSelectionModel().getSelectedIndex());
        tabs.getSelectionModel().select(usersTableTab.getUserTableTab());
        usersTableTab.updateTable();
    }

    public void updateUserTableAfterUpdate() {
        usersTableTab.updateTable();
    }

    public void logoutFromUser() {
        user.logout();
    }

    public void goToLoginPage() {
        LoginController loginController = new LoginController(title,primaryStage);
    }

    public void onExitDialogClickLogout() {
        user.logout();
        primaryStage.setOnCloseRequest(null);
        goToLoginPage();
    }

    public void goToUpdateTab() {
        tabs.getSelectionModel().select(updateTab.getUpdateTab());
    }

    public User getSelectedUserFromTable() {
        return usersTableTab.getUserSelected();
    }


    public void goToAdminUpdateTabFromTable(User user) {
        adminUpdateTab = usersTableTab.getAdminUpdateTab(user);
        tabs.getTabs().add(adminUpdateTab.getUpdateTab());
        tabs.getSelectionModel().select(adminUpdateTab.getUpdateTab());
    }

    public void goToUsersTableTab() {
        usersTableTab = adminTab.getUserTablesTab();
        tabs.getTabs().add(usersTableTab.getUserTableTab());
        tabs.getSelectionModel().select(usersTableTab.getUserTableTab());
    }

    public void updateFromAdminUpdateTab() {
        adminUpdateTab.updateUserInfoFromAdmin();
    }

    public void deleteUserFromAdminUpdateTab() {
        if(adminUpdateTab.deleteUserFromAdmin()) {
            user.logout();
            primaryStage.setOnCloseRequest(null);
            goToLoginPage();
        }
    }

    public void deleteUserFromUserTableTab() {
        if(usersTableTab.deleteUser()) {
            user.logout();
            primaryStage.setOnCloseRequest(null);
            goToLoginPage();
        }
    }

    public void updateInfoFromUpdateTab() {
        updateTab.updateInfoFromUser();
    }

    public BorderPane getHomePane() {
        return pane;
    }
}
