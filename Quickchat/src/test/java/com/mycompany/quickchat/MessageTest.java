/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;
// part2

/**
 *
 * @author Student
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Creates test messages
    Message message1 = new Message(0, "+27718693002",
            "Hi Mike, can you join us for dinner tonight?");
    Message message2 = new Message(1, "08575975889",
            "Hi Keegan, did you receive the payment?");

    // Tests a message with the correct length
    @Test
    public void testMessageLength_Success() {
        Message valid = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message ready to send.",
                valid.checkMessageLength());
    }

    // Tests a message that is too long
    @Test
    public void testMessageLength_Failure() {
        String longMsg = "a".repeat(260);
        Message invalid = new Message(0, "+27718693002", longMsg);

        String result = invalid.checkMessageLength();

        assertTrue(result.contains(
                "Message exceeds 250 characters by 10"));
    }

    // Tests a correct phone number
    @Test
    public void testRecipientCell_Success() {
        assertEquals("Cell phone number successfully captured.",
                message1.checkRecipientCell());
    }

    // Tests an incorrect phone number
    @Test
    public void testRecipientCell_Failure() {
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message2.checkRecipientCell());
    }

    // Tests the message hash
    @Test
    public void testMessageHash_Correct() {
        message1.setMessageID("0011223344");

        String hash = message1.createMessageHash();

        assertEquals("00:0:HITONIGHT", hash);
    }

    // Tests message hashes in a loop
    @Test
    public void testMessageHashesInLoop() {
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };

        for (int i = 0; i < messages.length; i++) {
            Message m = new Message(i, "+27718693002", messages[i]);
            m.setMessageID("00" + i + "0000000");

            assertNotNull(m.createMessageHash());
            assertTrue(m.createMessageHash().contains(":"));
        }
    }

    // Tests if a message ID is created
    @Test
    public void testMessageID_Created() {
        String id = message1.getMessageID();

        assertNotNull(id);
        assertTrue(id.length() <= 10);

        System.out.println("Message ID generated: " + id);
    }

    // Tests the message ID
    @Test
    public void testCheckMessageID() {
        assertTrue(message1.checkMessageID());
    }

    // Tests sending a message
    @Test
    public void testSentMessage_Send() {
        Message m = new Message(0, "+27718693002", "Test");

        assertEquals("Message successfully sent.",
                m.SentMessage(1));
    }

    // Tests disregarding a message
    @Test
    public void testSentMessage_Disregard() {
        Message m = new Message(0, "+27718693002", "Test");

        assertEquals("Press 0 to delete the message.",
                m.SentMessage(2));
    }

    // Tests storing a message
    @Test
    public void testSentMessage_Store() {
        Message m = new Message(0, "+27718693002", "Test");

        assertEquals("Message successfully stored.",
                m.SentMessage(3));
    }

    // Tests the total number of sent messages
    @Test
    public void testReturnTotalMessages() {
        int initial = Message.returnTotalMessagess();

        Message m = new Message(99, "+27718693002", "Total test");
        m.SentMessage(1);

        assertEquals(initial + 1, Message.returnTotalMessagess());
    }

    // Tests displaying sent messages
    @Test
    public void testPrintMessages() {
        Message m = new Message(0, "+27718693002", "Print test");
        m.SentMessage(1);

        String printed = Message.printMessages();

        assertTrue(printed.contains("Print test")
                || printed.contains("MessageID"));
    }
}

