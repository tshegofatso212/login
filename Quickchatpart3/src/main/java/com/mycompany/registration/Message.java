/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */
public class Message {

    static Object displayReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void addMessage(Message m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Object displaySenderAndRecipient() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String displayLongestMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Object searchMessageByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Object searchMessagesByRecipient(String recipient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Object deleteMessageByHash(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String flag; // Sent, Stored, Disregard
    private String sender;

    public Message(String messageID, int messageNumber, String recipient,
                   String message, String flag, String sender) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.flag = flag;
        this.sender = sender;
        this.messageHash = createMessageHash();
    }

    Message(String recipient, String text, int i, String flag) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }
    public String getSender() { return sender; }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    public boolean checkRecipientCell() {
        if (recipient == null) return false;
        if (recipient.startsWith("+27") && recipient.length() == 12) return true;
        if (recipient.startsWith("0") && recipient.length() == 10) return true;
        return false;
    }

    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || message == null) return "";
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String last = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last;
    }

    void storeMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String printMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String sentMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
