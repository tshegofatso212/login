/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

//Login class handles user registration and login.

import java.util.Scanner;
// part2
public class Login {

    private String storedUsername;
    private String storedPassword;

    // Check username
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }

        return username.contains("_") && username.length() <= 5;
    }

    // Check password
    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }

        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");

        return length && capital && number && special;
    }

    // Check cell phone number
    //Checks the cellphone number follows the required South African format.
    public boolean checkCellPhoneNumber(String cell) {
        if (cell == null) {
            return false;
        }

        return cell.matches("^\\+27\\d{9}$");
    }
    //Status message and login logic.
    // Username message
    public String getUsernameStatus(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        }

        return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    // Password message
    public String getPasswordStatus(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        }

        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    // Cell number message
    public String getCellStatus(String cell) {
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        }

        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    // Alternative cell status method
    public String getCellStatusAlt(String cell) {
        return getCellStatus(cell);
    }

    // Store login details
    public void setStoredCredentials(String username, String password,
                                     String cell, String fName, String lName) {
        storedUsername = username;
        storedPassword = password;
    }

    // Check login details
    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) {
            return false;
        }

        return storedUsername.equals(username)
                && storedPassword.equals(password);
    }

    // Return login message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome";
        }

        return "Username or password incorrect, please try again.";
    }
     boolean registerUser(String username, String password, String cell) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean loginUser(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}




