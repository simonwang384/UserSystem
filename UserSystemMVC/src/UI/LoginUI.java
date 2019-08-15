package UI;

import controller.MainMenuController;
import controller.RegisterController;
import database.Driver;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Admin;
import models.User;

public class LoginUI {
    private final BorderPane pane;
    private Text title;
    private Stage primaryStage;
    private Text userLabel;
    private Text passwordLabel;
    private Text loginLabel;
    private TextField userEntry;
    private PasswordField passwordEntry;
    private Button registerBtn;
    private Button loginBtn;
    private HBox userHBox;
    private HBox passHBox;
    private VBox vBox;
    private Text status;
    private User user;
    private Admin admin;

    public LoginUI(Text title, Stage primaryStage, EventHandler loginBtnEvent, EventHandler goToRegisterPage) {
        this.title = title;
        this.primaryStage = primaryStage;
        this.pane = new BorderPane();

        this.userLabel = new Text("Username: ");
        this.passwordLabel = new Text("Password: ");
        this.status = new Text();

        this.loginLabel = new Text("Login");
        this.loginLabel.setFont(Font.font(20));

        this.userEntry = new TextField();
        this.passwordEntry = new PasswordField();

        this.loginBtn = new Button("Login");
        loginBtn.setOnAction(loginBtnEvent);
        loginBtn.setDefaultButton(true);

        this.registerBtn = new Button("No Account? Register!");
        registerBtn.setOnAction(goToRegisterPage);


        this.userHBox = new HBox();
        userHBox.getChildren().addAll(userLabel,userEntry);
        userHBox.setAlignment(Pos.CENTER);

        this.passHBox = new HBox();
        passHBox.getChildren().addAll(passwordLabel,passwordEntry);
        passHBox.setAlignment(Pos.CENTER);


        this.vBox = new VBox();
        vBox.getChildren().addAll(title,loginLabel,userHBox,passHBox,loginBtn,registerBtn, status);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        pane.setCenter(vBox);

        Scene loginScene = new Scene(getLoginVBox(),500,500);
        primaryStage.setScene(loginScene);
        primaryStage.setOnCloseRequest(null);

    }

    public BorderPane getLoginVBox() {
        return pane;
    }

    public void goToMainMenuPage(Admin admin) {
        MainMenuController mainMenuController = new MainMenuController(title,primaryStage,(Admin) admin);
        status.setText("Logged in");
    }

    public void goToMainMenuPage(User user) {
        MainMenuController mainMenuController = new MainMenuController(title,primaryStage,user);
        status.setText("Logged in");
    }

    public boolean checkLogin() {
        String username = userEntry.getText();
        String password = passwordEntry.getText();
        String dbPassword = Driver.getPassword(username);

        if (Driver.checkIfAdmin(username)) {
            admin = Driver.getAdmin(username);
            if (admin.login(username,password,dbPassword)) {
                goToMainMenuPage(admin);
                return true;
            }
        } else {
            user = Driver.getUser(username);
            if (user.login(username,password,dbPassword)) {
                goToMainMenuPage(user);
                return true;
            }
        }

        status.setText("Incorrect Username/Password");
        return false;
    }

    public void goToRegisterPage() {
        RegisterController registerController = new RegisterController(title,primaryStage);
    }





}
