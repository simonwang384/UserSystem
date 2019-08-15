package UI;

import controller.LoginController;
import controller.RegisterController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartMenuUI{

    private String title;
    private Text titleUI;
    private Button loginBtn;
    private Button registerBtn;
    private VBox items;
    private Stage primaryStage;
    private Scene mainMenu;


    public StartMenuUI(Stage primaryStage, EventHandler loginBtnEvent, EventHandler registerBtnEvent) {
        this.primaryStage = primaryStage;

        title = "User System";
        titleUI = new Text(title);
        titleUI.setFont(Font.font(50));

        loginBtn = new Button("Login");
        loginBtn.setOnAction(loginBtnEvent);

        registerBtn = new Button("Register");
        registerBtn.setOnAction(registerBtnEvent);

        items = new VBox();
        items.getChildren().addAll(titleUI,loginBtn,registerBtn);
        items.setSpacing(20);
        items.setAlignment(Pos.CENTER);

        this.mainMenu = new Scene(items,500,500);
        primaryStage.setScene(mainMenu);

        primaryStage.show();

    }

    public void goToLoginPage() {
        LoginController loginController = new LoginController(titleUI,primaryStage);
    }

    public void goToRegisterPage() {
        RegisterController registerController = new RegisterController(titleUI,primaryStage);
    }

    public String getTitle() {
        return title;
    }
}
