/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Random;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class Message {

    private String messageID;
    private final int numMessagesSent;
    private final String recipient;
    private final String messageContent;
    private String messageHash;

    private static int totalMessagesSent = 0;
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static final List<Message> disregardedMessages = new ArrayList<>();

    // Sets the message details
    public Message(int numSent, String recipient, String messageContent) {
        this.messageID = generateMessageID();
        this.numMessagesSent = numSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
    }

    // Creates a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Checks that the message ID has no more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Checks the recipient's phone number
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+")
                && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Checks that the message is not longer than 250 characters
    public String checkMessageLength() {
        if (messageContent.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = messageContent.length() - 250;
        return "Message exceeds 250 characters by " + excess
                + ", please reduce the size.";
    }

    // Creates the message hash
    public String createMessageHash() {
        String[] words = messageContent.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z]", "");

        String firstTwo = messageID.substring(0, 2);

        return (firstTwo + ":" + numMessagesSent + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Sends, disregards or stores the message
    public String SentMessage(int choice) {
        switch (choice) {
            case 1 -> {
                sentMessages.add(this);
                totalMessagesSent++;
                return "Message successfully sent.";
            }

            case 2 -> {
                disregardedMessages.add(this);
                return "Press 0 to delete the message.";
            }

            case 3 -> {
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            }

            default -> {
                return "Invalid choice.";
            }
        }
    }

    // Displays all sent messages
    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }

        StringBuilder result = new StringBuilder();

        for (Message m : sentMessages) {
            result.append("MessageID: ").append(m.messageID).append("\n");
            result.append("MessageHash: ").append(m.messageHash).append("\n");
            result.append("Recipient: ").append(m.recipient).append("\n");
            result.append("Message: ").append(m.messageContent).append("\n\n");
        }

        return result.toString();
    }

    // Returns the total number of sent messages
    public static int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Saves the message in a JSON file
    public void storeMessage() {
        try {
            Path path = Paths.get("storedMessages.json");

            String newEntry = String.format(
                "{\"messageID\":\"%s\",\"messageHash\":\"%s\","
                + "\"recipient\":\"%s\",\"message\":\"%s\","
                + "\"numSent\":%d}",
                messageID,
                messageHash,
                recipient,
                messageContent.replace("\"", "\\\"").replace("\n", " "),
                numMessagesSent
            );

            String content = "[]";

            if (Files.exists(path)) {
                content = new String(Files.readAllBytes(path)).trim();
            }

            if (content.equals("[]") || content.isEmpty()) {
                content = "[" + newEntry + "]";
            } else {
                content = content.substring(0, content.lastIndexOf("]"))
                        + "," + newEntry + "]";
            }

            Files.write(path, content.getBytes());

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Returns message details for testing
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getNumMessagesSent() {
        return numMessagesSent;
    }

    public static List<Message> getSentMessagesList() {
        return sentMessages;
    }

    // Allows the message ID to be changed during testing
    public void setMessageID(String id) {
        this.messageID = id;
        this.messageHash = createMessageHash();
    }
}

