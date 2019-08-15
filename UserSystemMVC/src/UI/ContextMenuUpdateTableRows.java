package UI;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ContextMenuUpdateTableRows {
    private ContextMenu contextMenu;



    public ContextMenuUpdateTableRows(EventHandler GoToAdminUpdateTab , EventHandler DeleteUserFromAdminEvent) {
        MenuItem openUpdateTabItem = new MenuItem("Update");
        MenuItem deleteUserItem = new MenuItem("Delete");

        openUpdateTabItem.setOnAction(GoToAdminUpdateTab);
        deleteUserItem.setOnAction(DeleteUserFromAdminEvent);

        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(openUpdateTabItem,deleteUserItem);

    }


    public ContextMenu getContextMenu() {
        return contextMenu;
    }


}
