package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.User;

public class HomeTab {

    private Tab homeTab;
    private User user;
    private Label nameLabel;
    private BorderPane pane;
    private Hyperlink addressLink;
    private Hyperlink descriptionLink;
    private Label addressLabel;
    private Label descriptionLabel;
    private VBox userInfoVBox;

    public HomeTab(User user, EventHandler goToUpdateTabEvent) {
        this.user = user;
        this.homeTab = new Tab("Home");
        homeTab.setClosable(false);

        this.pane = new BorderPane();


        this.nameLabel = new Label(user.getFirstName() + " " + user.getLastName());
        nameLabel.setFont(Font.font(20));
        nameLabel.setPadding(new Insets(10,10,10,10));
        pane.setTop(nameLabel);


        this.userInfoVBox = new VBox();
        userInfoVBox.setPadding(new Insets(10,10,10,10));
        userInfoVBox.setSpacing(10);


        this.addressLink = new Hyperlink("No Address. Click here to update!");
        addressLink.setOnAction(goToUpdateTabEvent);

        this.descriptionLink = new Hyperlink("No description. Click here to update!");
        descriptionLink.setOnAction(goToUpdateTabEvent);


        this.addressLabel = new Label(user.getAddress());
        this.descriptionLabel = new Label(user.getDescription());

        if (checkAddressIsEmpty() && checkDescriptionIsEmpty()) {
            userInfoVBox.getChildren().addAll(addressLink,descriptionLink);
        } else if(checkAddressIsEmpty()) {
            userInfoVBox.getChildren().addAll(addressLink,descriptionLabel);
        } else if(checkDescriptionIsEmpty()) {
            userInfoVBox.getChildren().addAll(addressLabel,descriptionLink);
        } else
            userInfoVBox.getChildren().addAll(addressLabel,descriptionLabel);

        pane.setCenter(userInfoVBox);


        homeTab.setContent(pane);

    }

    public void resetHyperLinks() {
        addressLink.setVisited(false);
        descriptionLink.setVisited(false);
    }


    public boolean checkFirstNameIsEmpty() {
        return user.isFirstNameEmpty();
    }

    public boolean checkLastNameIsEmpty() {
        return user.isLastNameEmpty();
    }

    public boolean checkAddressIsEmpty() {
        return user.isAddressEmpty();
    }

    public boolean checkDescriptionIsEmpty() {
        return user.isDescriptionEmpty();
    }

    public void updateLabels() {
        this.nameLabel.setText(user.getFirstName() + " " + user.getLastName());

        userInfoVBox.getChildren().remove(0);
        if (checkAddressIsEmpty()) {
            userInfoVBox.getChildren().add(0,addressLink);
        } else {
            userInfoVBox.getChildren().add(0,addressLabel);
            this.addressLabel.setText(user.getAddress());
            System.out.println("Working");
        }

        userInfoVBox.getChildren().remove(1);
        if (checkDescriptionIsEmpty()) {
            userInfoVBox.getChildren().add(1,descriptionLink);
        } else {
            userInfoVBox.getChildren().add(1,descriptionLabel);
            this.descriptionLabel.setText(user.getDescription());
        }

    }


    public Tab getHomeTab() {
        return homeTab;
    }
}
