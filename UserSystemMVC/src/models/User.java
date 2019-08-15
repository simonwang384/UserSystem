package models;

import BCrypt.BCrypt;
import database.Driver;

public class User {
    private String userID;
    private String firstName;
    private String lastName;
    private String address;
    private String description;

    public User() {

    }


    public User(String userID, String firstName, String lastName, String address, String description) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.description = description;
    }

    public void setUserInfo(String firstName, String lastName, String address, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.description = description;
    }

    public boolean isFirstNameEmpty() {
        if (firstName.equals(""))
            return true;
        else
            return false;
    }

    public boolean isLastNameEmpty() {
        if (lastName.equals(""))
            return true;
        else
            return false;
    }

    public boolean isAddressEmpty() {
        if (address == null || address.equals(""))
            return true;
        else
            return false;
    }

    public boolean isDescriptionEmpty() {
        if (description == null || description.equals(""))
            return true;
        else
            return false;
    }

    public boolean updateUserInfo(String userID,String firstName, String lastName, String address, String description) {
        if(Driver.updateUserInfo(userID,firstName,lastName,address,description)) {
            setUserInfo(firstName,lastName,address,description);
            return true;
        } else
            return false;
    }

    public boolean login(String username, String password, String dbPassword) {
        System.out.println(username);
        if ((!dbPassword.equals("") && !dbPassword.equals(null)) && BCrypt.checkpw(password,dbPassword)) {
            Driver.setLoggedIn(username, "true");
            return true;
        }
        return false;
    }

    public void logout() {
        Driver.setLoggedIn(userID,"false");
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
