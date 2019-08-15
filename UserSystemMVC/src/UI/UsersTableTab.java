package UI;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Admin;
import models.User;

public class UsersTableTab {
    private Tab usersTableTab;
    private TableView<User> tableView;
    private Admin admin;
    private EventHandler DoubleClickGoToAdminUpdateTab;
    private EventHandler DeleteUserFromAdminEvent;
    private EventHandler AdminUpdateTabBtnEvent;
    private EventHandler GoToAdminUpdateTab;
    private EventHandler DeleteUserFromContextMenu;
    private ContextMenuUpdateTableRows contextMenuUpdateTableRows;

    public UsersTableTab(Admin admin, EventHandler DoubleClickGoToAdminUpdateTab,
                         EventHandler DeleteUserFromAdminEvent,
                         EventHandler AdminUpdateTabBtnEvent,
                         EventHandler GoToAdminUpdateTab,
                         EventHandler DeleteUserFromContextMenu) {

        this.admin = admin;
        this.usersTableTab = new Tab("Users Table");

        this.DoubleClickGoToAdminUpdateTab = DoubleClickGoToAdminUpdateTab;
        this.DeleteUserFromAdminEvent = DeleteUserFromAdminEvent;
        this.AdminUpdateTabBtnEvent = AdminUpdateTabBtnEvent;
        this.GoToAdminUpdateTab = GoToAdminUpdateTab;
        this.DeleteUserFromContextMenu = DeleteUserFromContextMenu;

        this.contextMenuUpdateTableRows = new ContextMenuUpdateTableRows(GoToAdminUpdateTab, DeleteUserFromContextMenu);

        TableColumn<User, String> userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));

        TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));

        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));

        TableColumn<User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));

        tableView = new TableView<>();
        tableView.setItems(getAllUsersFromAdmin());
        tableView.getColumns().addAll(userIDColumn,firstNameColumn,lastNameColumn,addressColumn);

        setListenerForEachRowInTable();



        usersTableTab.setContent(tableView);
    }

    public Tab getUserTableTab() {
        return usersTableTab;
    }

    public void setListenerForEachRowInTable() {
        tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(DoubleClickGoToAdminUpdateTab);
            row.setContextMenu(contextMenuUpdateTableRows.getContextMenu());
            return row ;
        });
    }

    public User getUserSelected() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void updateTable() {
        tableView.setItems(getAllUsersFromAdmin());
    }

    public boolean deleteUser() {
        if(admin.isEqual(getUserSelected())) {
            admin.deleteUser(getUserSelected());
            return true;
        }
        admin.deleteUser(getUserSelected());
        return false;
    }

    public ObservableList<User> getAllUsersFromAdmin() {
        return admin.getAllUsers();
    }



    public AdminUpdateTab getAdminUpdateTab(User user) {

        return new AdminUpdateTab(user, admin, AdminUpdateTabBtnEvent, DeleteUserFromAdminEvent);

    }

}
