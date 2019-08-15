package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Admin;
import models.User;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;

public class Driver {
    private static Connection c = null;

    private Driver() {

    }

    public static Connection getCon() {
        try {
            if (c == null) {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersystem", "root", "root");
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return c;
    }

    public static void disConnect() {
        try {
            c.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void insertIntoUserTable(String username, String password) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("insert into user (userID,password) values (?,?)");
            p.setString(1,username);
            p.setString(2,password);
            p.executeUpdate();
            System.out.println("Inserting");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void insertIntoUserInfoTable(String username, String firstName,String lastName) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("insert into userInfo (userID,firstname,lastname) values (?,?,?)");
            p.setString(1,username);
            p.setString(2,firstName);
            p.setString(3,lastName);
            p.executeUpdate();
            System.out.println("Inserting");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static String getPassword(String username) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("SELECT * FROM user where userID = ?");
            p.setString(1,username);

            ResultSet r = p.executeQuery();
            String s = "";
            if (r.next())
                s = r.getString("password");

            return s;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }

    public static boolean containsUserID(String username) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("SELECT userID FROM user where userID = ?");
            p.setString(1,username);

            ResultSet r = p.executeQuery();
            String s = "";
            if (r.next())
                s = r.getString("userID");

            if (s.equals(username))
                return true;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean checkIfAdmin(String username) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("SELECT admin FROM user where userID = ?");
            p.setString(1,username);

            ResultSet r = p.executeQuery();
             if (r.next()) {
                 if (r.getInt("admin") == 0)
                     return true;
             }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return false;
    }

    public static Admin getAdmin(String username) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("SELECT * FROM userInfo where userID = ?");
            p.setString(1,username);

            ResultSet r = p.executeQuery();
            Admin admin = new Admin();
            if (r.next()) {
                admin.setUserID(r.getString("userID"));
                admin.setFirstName(r.getString("firstname"));
                admin.setLastName(r.getString("lastname"));
                admin.setAddress(r.getString("address"));
                admin.setDescription(r.getString("description"));
            }

            return admin;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static User getUser(String username) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("SELECT * FROM userInfo where userID = ?");
            p.setString(1,username);

            ResultSet r = p.executeQuery();
            User user = new User();
            if (r.next()) {
                user.setUserID(r.getString("userID"));
                user.setFirstName(r.getString("firstname"));
                user.setLastName(r.getString("lastname"));
                user.setAddress(r.getString("address"));
                user.setDescription(r.getString("description"));
            }


            return user;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static boolean updateUserInfo(String userID, String firstName, String lastName, String address, String description) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("UPDATE userInfo SET " +
                    "firstName = ?," +
                    "lastName = ?," +
                    "address = ?," +
                    "description = ? " +
                    "where userID = ?");
            p.setString(1,firstName);
            p.setString(2,lastName);
            p.setString(3,address);
            p.setString(4,description);
            p.setString(5,userID);
            p.executeUpdate();

            System.out.println("Updating");
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static void setLoggedIn(String userID, String loggedIn) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("UPDATE user SET loggedin = ? where userID = ?");
            p.setString(1,loggedIn);
            p.setString(2, userID);
            p.executeUpdate();

            System.out.println("Updating");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static ObservableList<User> getAllUsers() {
        try {
            Statement s = Driver.getCon().createStatement();
            ResultSet r = s.executeQuery("select * from userInfo");

            ObservableList<User> users = FXCollections.observableArrayList();

            while (r.next()) {
                User user = new User(r.getString("userID"),
                        r.getString("firstname"),
                        r.getString("lastname"),
                        r.getString("address"),
                        r.getString("description"));
                users.add(user);
            }
            System.out.println("Getting users");
            return users;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void deleteUser(User user) {
        try {
            PreparedStatement p1 = Driver.getCon().prepareStatement("DELETE FROM userInfo where userID = ?");
            PreparedStatement p2 = Driver.getCon().prepareStatement("DELETE FROM user where userID = ?");
            p1.setString(1,user.getUserID());
            p1.executeUpdate();

            p2.setString(1,user.getUserID());
            p2.executeUpdate();

            System.out.println("Deleting");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }



}
