/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuickChat {

    public static ArrayList<Message> sentMessages;
   public static ArrayList<Message> allMessages;
   public static ArrayList<Message> storedMessages;
    static String deletedByhash;

    public static void main(String[] args) {

        // PART 1 - Register and login
        Login login = new Login();

        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        String cell = JOptionPane.showInputDialog("Enter Cell Number (+27...):");

        JOptionPane.showMessageDialog(null,
                login.registerUser(username, password, firstName, lastName, cell));

        // Login details
        String loginUser = JOptionPane.showInputDialog("Enter Username to Login:");
        String loginPass = JOptionPane.showInputDialog("Enter Password to Login:");

        boolean loggedIn = login.loginUser(loginUser, loginPass);

        JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Main menu
        while (true) {

            String menu = "QuickChat Menu:\n"
                    + "1. Send Messages\n"
                    + "2. Show Recently Sent Messages\n"
                    + "3. Quit\n"
                    + "4. Stored Messages";

            String input = JOptionPane.showInputDialog(menu);

            if (input == null) {
                break;
            }

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            if (choice == 1) {
                sendMessages();
            } 
            else if (choice == 2) {
                JOptionPane.showMessageDialog(null, Message.displayReport());
            } 
            else if (choice == 3) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                break;
            } 
            else if (choice == 4) {
                storedMessagesMenu();
            }
        }
    }

    // Sends messages
    private static void sendMessages() {

        String numStr = JOptionPane.showInputDialog(
                "How many messages do you want to send?");

        if (numStr == null) {
            return;
        }

        int num;

        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }

        if (num <= 0) {
            JOptionPane.showMessageDialog(null, "Enter at least one message.");
            return;
        }

        for (int i = 0; i < num; i++) {

            String recipient = JOptionPane.showInputDialog(
                    "Enter Recipient (+27... or 0...):");

            if (recipient == null) {
                return;
            }

            String text = JOptionPane.showInputDialog(
                    "Enter Message (max 250 characters):");

            if (text == null) {
                return;
            }

            if (text.length() > 250) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a message of less than 250 characters.");
                i--;
                continue;
            }

            // Choose what to do with the message
            String[] options = {"Send", "Disregard", "Store"};

            int flagChoice = JOptionPane.showOptionDialog(
                    null,
                    "What do you want to do with this message?",
                    "Send or Store Message",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (flagChoice == -1) {
                return;
            }

            String flag = "Sent";

            if (flagChoice == 1) {
                flag = "Disregard";
            } 
            else if (flagChoice == 2) {
                flag = "Stored";
            }

            Message m = new Message(recipient, text, i, flag);

            // Check the recipient number
            if (m.checkRecipientCell()) {
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cell phone number is incorrectly formatted.");
                i--;
                continue;
            }

            Message.addMessage(m);

            if (flag.equals("Stored")) {
                m.storeMessage();
            }

            JOptionPane.showMessageDialog(null,
                    m.printMessages() + "\n" + m.sentMessage());
        }
    }

    // Menu for stored messages
    private static void storedMessagesMenu() {

        while (true) {

            String menu = "Stored Messages:\n"
                    + "a. Display sender and recipient of all stored messages\n"
                    + "b. Display longest stored message\n"
                    + "c. Search for a message ID\n"
                    + "d. Search for all messages for a recipient\n"
                    + "e. Delete a message using message hash\n"
                    + "f. Display report\n"
                    + "g. Back to main menu";

            String choice = JOptionPane.showInputDialog(menu);

            if (choice == null || choice.equalsIgnoreCase("g")) {
                break;
            }

            if (choice.equalsIgnoreCase("a")) {

                JOptionPane.showMessageDialog(null,
                        Message.displaySenderAndRecipient());

            } 
            else if (choice.equalsIgnoreCase("b")) {

                JOptionPane.showMessageDialog(null,
                        "Longest Message:\n" + Message.displayLongestMessage());

            } 
            else if (choice.equalsIgnoreCase("c")) {

                String id = JOptionPane.showInputDialog(
                        "Enter Message ID to search:");

                if (id != null) {
                    JOptionPane.showMessageDialog(null,
                            Message.searchMessageByID(id));
                }

            } 
            else if (choice.equalsIgnoreCase("d")) {

                String recipient = JOptionPane.showInputDialog(
                        "Enter Recipient to search:");

                if (recipient != null) {
                    JOptionPane.showMessageDialog(null,
                            Message.searchMessagesByRecipient(recipient).toString());
                }

            } 
            else if (choice.equalsIgnoreCase("e")) {

                String hash = JOptionPane.showInputDialog(
                        "Enter Message Hash to delete:");

                if (hash != null) {
                    JOptionPane.showMessageDialog(null,
                            Message.deleteMessageByHash(hash));
                }

            } 
            else if (choice.equalsIgnoreCase("f")) {

                JOptionPane.showMessageDialog(null,
                        Message.displayReport());
            }
        }
    }

    public static void populateTestData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    static Object displayLongestMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String displayReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Object searchByMessageID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String searchByRecipient(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}

