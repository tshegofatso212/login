/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

    @BeforeEach
    void setUp() {
        QuickChat.populateTestData();
    }

    @Test
    void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, QuickChat.sentMessages.size());
        assertEquals("Did you get the cake?", QuickChat.sentMessages.get(0).getMessage());
        assertEquals("It is dinner time !", QuickChat.sentMessages.get(1).getMessage());
    }

    @Test
    void testMessagesArrayContainsExpectedTestData() {
        assertTrue(QuickChat.allMessages.size() == 5);
        assertEquals("Did you get the cake?", QuickChat.allMessages.get(0).getMessage());
    }

    @Test
    void testDisplayLongestMessage() {
        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            QuickChat.displayLongestMessage()
        );
    }

    @Test
    void testSearchForMessageID() {
        assertEquals("It is dinner time !", QuickChat.searchByMessageID("0838884567"));
    }

    @Test
    void testSearchAllMessagesForRecipient() {
        String result = QuickChat.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    void testDeleteMessageUsingHash() {
        String hash = QuickChat.storedMessages.get(0).getMessageHash();
        Object result = null;
        assertEquals(
            "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
            result
        );
        assertEquals(1, QuickChat.storedMessages.size());
    }

    @Test
    void testDisplayReport() {
        String report = QuickChat.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time !"));
    }
}