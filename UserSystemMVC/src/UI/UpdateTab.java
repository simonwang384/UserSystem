package UI;

import database.Driver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.User;

public class UpdateTab {
    private Tab updateTab;
    private User user;
    private BorderPane pane;
    private HBox container;
    private VBox labelEntryVBox;
    private Label firstName;
    private Label lastName;
    private Label address;
    private Label description;
    private TextField firstNameEntry;
    private TextField lastNameEntry;
    private TextField addressEntry;
    private TextArea descriptionEntry;
    private Label status;

    private HBox firstNameHBox;
    private HBox lastNameHBox;
    private HBox addressHBox;
    private HBox descriptionHBox;
    private Button updateBtn;

    public UpdateTab(User user, EventHandler updateBtnEvent) {
        this.user = user;
        this.updateTab = new Tab("Update");
        updateTab.setClosable(false);


        this.container = new HBox();
        container.setAlignment(Pos.CENTER);
        this.labelEntryVBox = new VBox();

        this.firstName = new Label("First Name: ");
        this.lastName = new Label("Last Name: ");
        this.address = new Label("Address: ");
        this.description = new Label("Description: ");

        this.firstNameEntry = new TextField(this.user.getFirstName());
        this.lastNameEntry = new TextField(this.user.getLastName());
        this.addressEntry = new TextField(this.user.getAddress());
        this.descriptionEntry = new TextArea(this.user.getDescription());

        this.firstNameHBox = new HBox();
        firstNameHBox.getChildren().addAll(firstName,firstNameEntry);
        firstNameHBox.setPadding(new Insets(10,0,0,0));
        firstNameHBox.setSpacing(10);

        this.lastNameHBox = new HBox();
        lastNameHBox.getChildren().addAll(lastName,lastNameEntry);
        lastNameHBox.setSpacing(12);

        this.addressHBox = new HBox();
        addressHBox.getChildren().addAll(address,addressEntry);
        addressHBox.setSpacing(25);

        this.descriptionHBox = new HBox();
        descriptionHBox.getChildren().addAll(description,descriptionEntry);
        descriptionHBox.setSpacing(8);

        this.updateBtn = new Button("Update");
        updateBtn.setOnAction(updateBtnEvent);

        this.status = new Label();

        labelEntryVBox.getChildren().addAll(firstNameHBox,lastNameHBox,addressHBox,descriptionHBox, updateBtn, status);
        labelEntryVBox.setSpacing(10);
        container.getChildren().addAll(labelEntryVBox);

        this.pane = new BorderPane();
        pane.setCenter(container);
        updateTab.setContent(pane);
    }

    public Tab getUpdateTab() {
        return updateTab;
    }

    public void updateInfoFromUser() {
        if (user.updateUserInfo(user.getUserID(),firstNameEntry.getText(),lastNameEntry.getText(),addressEntry.getText(),descriptionEntry.getText())) {
            status.setText("Update Successful.");
        } else {
            status.setText("Update not successful.");
        }
    }




    public void resetStatusLabel() {
        status.setText("");
    }


}
