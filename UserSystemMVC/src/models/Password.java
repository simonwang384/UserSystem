package models;

public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public Password() {

    }

    public boolean validPassword(String password) {
        this.password = password;
        if (hasLowerCase() && hasEnoughLength() && hasNumber() && hasSpecialCharacter() && hasUpperCase()) {
            System.out.println("Password is valid");
            return true;
        } else {
            System.out.println("Password is not valid");
            return false;
        }
    }

    public boolean hasLowerCase() {
        int count = 0;
        for(int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i)))
                count++;
        }

        if (count >= 1)
            return true;
        else
            return false;
    }

    public boolean hasEnoughLength() {
        if (password.length() >= 8)
            return true;
        else
            return false;
    }

    public boolean hasUpperCase() {
        for(int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i)))
                return true;
        }

        return false;


    }

    public boolean hasNumber() {
        for(int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                return true;
        }

        return false;
    }

    public boolean hasSpecialCharacter() {
        for(int i = 0; i < password.length(); i++) {
            if (!Character.isLetterOrDigit(password.charAt(i)))
                return true;
        }

        return false;
    }

}
