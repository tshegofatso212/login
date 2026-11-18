/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

/**
 *
 * @author Student
 */

public class Login {
    private String storedUsername;
    private String storedPassword;

    // BOOLEAN CHECKS - for the ReturnsTrue / ReturnsFalse tests
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");
        return length && capital && number && special;
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) return false;
        return cell.matches("^\\+27\\d{9}$");
    }

    // MESSAGE METHODS - for the AssertEquals tests - WORD FOR WORD from your LoginTest.java
    public String getUsernameStatus(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    public String getPasswordStatus(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    public String getCellStatus(String cell) {
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    public String getCellStatusAlt(String cell) {
        return getCellStatus(cell);
    }

    // LOGIN METHODS
    public void setStoredCredentials(String username, String password, String cell, String fName, String lName) {
        this.storedUsername = username;
        this.storedPassword = password;
    }

    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) return false;
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    Object registerUser(String username, String password, String firstName, String lastName, String cell) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object returnLoginStatus(boolean loggedIn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

