/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class QuickChat {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Login login = new Login();
            
            System.out.println("Welcome to QuickChat.");
            
            // Checks if the user can log in
            if (!login.loginUser(scanner)) {
                System.out.println("Login failed. Exiting.");
                scanner.close();
                return;
            }
            
            boolean running = true;
            
            while (running) {
                
                System.out.println("\nMenu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                
                String option = scanner.nextLine();
                
                switch (option) {
                    
                    case "1" -> {
                        System.out.print("How many messages do you wish to enter? ");
                        int numMessages = Integer.parseInt(scanner.nextLine());
                        
                        for (int i = 0; i < numMessages; i++) {
                            
                            System.out.println("\n--- Message " + (i + 1) + " ---");
                            
                            System.out.print("Enter recipient with international code: ");
                            String recipient = scanner.nextLine();
                            
                            System.out.print("Enter your message (max 250 characters): ");
                            String content = scanner.nextLine();
                            
                            Message msg = new Message(i, recipient, content);
                            
                            // Checks the message ID
                            if (!msg.checkMessageID()) {
                                System.out.println("Message ID exceeds 10 characters.");
                                i--;
                                continue;
                            }
                            
                            // Checks the recipient's phone number
                            String recipientCheck = msg.checkRecipientCell();
                            System.out.println(recipientCheck);
                            
                            if (!recipientCheck.contains("successfully captured")) {
                                i--;
                                continue;
                            }
                            
                            // Checks the message length
                            String lengthCheck = msg.checkMessageLength();
                            System.out.println(lengthCheck);
                            
                            if (!lengthCheck.equals("Message ready to send.")) {
                                i--;
                                continue;
                            }
                            
                            // Displays the message ID and hash
                            System.out.println("Message Hash: " + msg.getMessageHash());
                            System.out.println("Message ID: " + msg.getMessageID());
                            
                            // Allows the user to choose what to do with the message
                            System.out.println("Choose:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            
                            int choice = Integer.parseInt(scanner.nextLine());
                            
                            String result = msg.SentMessage(choice);
                            System.out.println(result);
                            
                            // Displays the full details if the message is sent
                            if (choice == 1) {
                                System.out.println("\nFull Message Details:");
                                System.out.println("Message ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageContent());
                            }
                        }
                        
                        // Displays the total number of sent messages
                        System.out.println("\nTotal messages sent: "
                                + Message.returnTotalMessagess());
                    }
                        
                    case "2" -> System.out.println("Coming Soon.");
                        
                    case "3" -> {
                        running = false;
                        System.out.println("Quitting...");
                    }
                        
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
}


