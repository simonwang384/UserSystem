package UI;

import BCrypt.BCrypt;
import controller.LoginController;
import database.Driver;
import javafx.event.ActionEvent;
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
import models.Password;
import models.Register;

public class RegisterUI {

    private final BorderPane pane;
    private Text title;
    private Stage primaryStage;
    private Text userLabel;
    private Text passwordLabel;
    private Text registerLabel;
    private Text firstNameLabel;
    private Text lastNameLabel;
    private TextField userEntry;
    private PasswordField passwordEntry;
    private TextField firstNameEntry;
    private TextField lastNameEntry;
    private Button registerBtn;
    private Button loginBtn;
    private HBox userHBox;
    private HBox passHBox;
    private HBox firstNameHBox;
    private HBox lastNameHBox;
    private VBox vBox;
    private Text status;
    private Register register;
    private Text passwordSpecifications;

    public RegisterUI(Text title, Stage primaryStage, EventHandler registerBtnEvent, EventHandler goToLoginPageEvent) {
        this.register = new Register();
        this.title = title;
        this.primaryStage = primaryStage;
        this.pane = new BorderPane();

        this.registerLabel = new Text("Register");
        this.registerLabel.setFont(Font.font(20));
        this.status = new Text();

        String s = "Password must be: \n" +
                "\u2022 8 or more Characters \n" +
                "\u2022 Contain 1 uppercase and lowercase letter \n" +
                "\u2022 Contain 1 special character";
        this.passwordSpecifications = new Text(s);


        this.userLabel = new Text("Username: ");
        this.passwordLabel = new Text("Password: ");
        this.firstNameLabel = new Text("First Name: ");
        this.lastNameLabel = new Text("Last Name: ");

        this.userEntry = new TextField();
        this.passwordEntry = new PasswordField();
        this.firstNameEntry = new TextField();
        this.lastNameEntry = new TextField();


        this.loginBtn = new Button("Have an account? Login!");
        this.registerBtn = new Button("Register");
        loginBtn.setOnAction(goToLoginPageEvent);
        registerBtn.setOnAction(registerBtnEvent);



        this.userHBox = new HBox();
        userHBox.getChildren().addAll(userLabel,userEntry);
        userHBox.setAlignment(Pos.CENTER);

        this.passHBox = new HBox();
        passHBox.getChildren().addAll(passwordLabel,passwordEntry);
        passHBox.setAlignment(Pos.CENTER);

        this.firstNameHBox = new HBox();
        firstNameHBox.getChildren().addAll(firstNameLabel,firstNameEntry);
        firstNameHBox.setAlignment(Pos.CENTER);

        this.lastNameHBox = new HBox();
        lastNameHBox.getChildren().addAll(lastNameLabel,lastNameEntry);
        lastNameHBox.setAlignment(Pos.CENTER);

        this.vBox = new VBox();
        vBox.getChildren().addAll(title,registerLabel,passwordSpecifications,userHBox,passHBox,firstNameHBox,lastNameHBox,registerBtn,loginBtn,status);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);


        pane.setCenter(vBox);

        Scene scene = new Scene(getRegisterVBox(),500,500);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(null);

    }

    public BorderPane getRegisterVBox() {
        return pane;
    }

    public void goToLoginPage() {
        LoginController loginController = new LoginController(title,primaryStage);
    }

    public void register() {
        System.out.println(passwordEntry.getText());
        String username = userEntry.getText();

        if(Driver.containsUserID(username)) {
            status.setText("Username already in use.");
        } else if (register.checkPassword(passwordEntry.getText(),username)) {
            String password = BCrypt.hashpw(passwordEntry.getText(), BCrypt.gensalt());
            register.register(username,password,firstNameEntry.getText(),lastNameEntry.getText());
            resetAlLEntries();
            status.setText("Registered");
        } else {
            status.setText("Password must follow specifications.");
        }
    }

    public void resetAlLEntries() {
        userEntry.setText("");
        passwordEntry.setText("");
        firstNameEntry.setText("");
        lastNameEntry.setText("");
    }





}
