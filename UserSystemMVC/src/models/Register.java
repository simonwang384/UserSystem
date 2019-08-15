package models;

import database.Driver;

public class Register {
    Password password;

    public Register() {
        password = new Password();
    }
    public boolean checkPassword(String passwordEntry, String username) {
        if (password.validPassword(passwordEntry) && !Driver.containsUserID(username)) {
            return true;
        }
        return false;
    }

    public void register(String username,String password, String firstName, String lastName) {
        Driver.insertIntoUserTable(username, password);
        Driver.insertIntoUserInfoTable(username,firstName,lastName);
    }
}
