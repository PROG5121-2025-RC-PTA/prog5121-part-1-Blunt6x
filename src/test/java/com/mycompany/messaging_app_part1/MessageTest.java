/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.messaging_app_part1;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message msg1, msg2, msg3, msg4, msg5;

    @BeforeEach
    public void setUp() {
        // Clear arrays before each test
        Message.sentMessages.clear();
        Message.storedMessages.clear();
        Message.discardedMessages.clear();
        Message.messageIDs.clear();
        Message.messageHashes.clear();

        msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg3 = new Message(3, "+27834484567", "Yahoooo, I am at your gate.");
        msg4 = new Message(4, "0838884567", "It is dinner time !");
        msg5 = new Message(5, "+27838684567", "Ok, I am leaving without you.");

        msg1.sendMessageOption("1"); // Sent
        msg2.sendMessageOption("3"); // Stored
        msg3.sendMessageOption("2"); // Disregard
        msg4.sendMessageOption("1"); // Sent
        msg5.sendMessageOption("3"); // Stored
    }

    @Test
    public void testSentMessagesArray() {
        assertTrue(Message.sentMessages.contains(msg1));
        assertTrue(Message.sentMessages.contains(msg4));
        assertEquals(2, Message.sentMessages.size());
    }

    @Test
    public void testStoredMessagesArray() {
        assertTrue(Message.storedMessages.contains(msg2));
        assertTrue(Message.storedMessages.contains(msg5));
        assertEquals(2, Message.storedMessages.size());
    }

    @Test
    public void testDiscardedMessagesArray() {
        assertTrue(Message.discardedMessages.contains(msg3));
        assertEquals(1, Message.discardedMessages.size());
    }

    @Test
    public void testLongestMessage() {
        Message longest = Message.storedMessages.get(0);
        for (Message m : Message.storedMessages) {
            if (m.getMessage().length() > longest.getMessage().length()) {
                longest = m;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessage());
    }

    @Test
    public void testSearchMessageByID() {
        String idToFind = msg4.getMessageID();
        Message found = null;
        for (Message m : Message.sentMessages) {
            if (m.getMessageID().equals(idToFind)) {
                found = m;
                break;
            }
        }
        assertNotNull(found);
        assertEquals("It is dinner time !", found.getMessage());
    }

    @Test
    public void testSearchByRecipient() {
        String recipient = "+27838884567";
        boolean foundAny = false;
        for (Message m : Message.storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                foundAny = true;
                assertTrue(m.getMessage().contains("Where are you") || m.getMessage().contains("Ok"));
            }
        }
        assertTrue(foundAny);
    }

    @Test
    public void testDeleteByMessageHash() {
        String hashToDelete = msg2.createMessageHash();
        boolean removed = Message.storedMessages.removeIf(m -> m.createMessageHash().equals(hashToDelete));
        assertTrue(removed);
        assertFalse(Message.storedMessages.contains(msg2));
    }

    @Test
    public void testMessageHashCreated() {
        String hash = msg1.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(msg1.getMessageID().substring(0, 2)));
    }

    @Test
    public void testMessageIDLength() {
        assertEquals(10, msg1.getMessageID().length());
    }
}
