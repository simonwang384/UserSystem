package models;

import database.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin extends User{
    private ObservableList<User> users;

    public Admin() {
        users = FXCollections.observableArrayList();
    }

    public ObservableList<User> getAllUsers() {
        return users = Driver.getAllUsers();
    }

    public void deleteUser(User user) {
        Driver.deleteUser(user);
    }

    public boolean isEqual(User user) {
        if (getUserID().equals(user.getUserID())) {
            return true;
        } else
            return false;
    }




}
