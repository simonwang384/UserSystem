package UI;


import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Admin;
import models.User;



public class AdminTab {
    private Tab adminTab;
    private Admin admin;
    private BorderPane pane;
    private VBox container;
    private EventHandler AdminUpdateTabBtnEvent;
    private EventHandler DeleteUserFromAdminEvent;
    private EventHandler DoubleClickGoToAdminUpdateTab;
    private EventHandler GoToAdminUpdateTab;
    private EventHandler DeleteUserFromContextMenu;
    private Hyperlink linkToUsersTableTab;


    public AdminTab(Admin admin, EventHandler DoubleClickGoToAdminUpdateTab, EventHandler AdminUpdateTabBtnEvent,
                    EventHandler DeleteUserFromAdminEvent,
                    EventHandler goToUsersTableTabEvent,
                    EventHandler GoToAdminUpdateTab,
                    EventHandler DeleteUserFromContextMenu) {

        this.admin = admin;
        this.pane = new BorderPane();
        this.GoToAdminUpdateTab = GoToAdminUpdateTab;
        this.DeleteUserFromContextMenu = DeleteUserFromContextMenu;

        this.adminTab = new Tab("Admin");
        adminTab.setClosable(false);

        this.container = new VBox();

        this.DoubleClickGoToAdminUpdateTab = DoubleClickGoToAdminUpdateTab;
        this.AdminUpdateTabBtnEvent = AdminUpdateTabBtnEvent;
        this.DeleteUserFromAdminEvent = DeleteUserFromAdminEvent;

        this.linkToUsersTableTab = new Hyperlink("Click here to check users.");
        linkToUsersTableTab.setOnAction(goToUsersTableTabEvent);
        container.getChildren().add(linkToUsersTableTab);

        pane.setCenter(container);

        adminTab.setContent(pane);

    }

    public Tab getAdminTab() {
        return adminTab;
    }



    public UsersTableTab getUserTablesTab() {
        return new UsersTableTab(admin, DoubleClickGoToAdminUpdateTab, DeleteUserFromAdminEvent, AdminUpdateTabBtnEvent, GoToAdminUpdateTab, DeleteUserFromContextMenu);
    }



}
