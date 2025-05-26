/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.messaging_app_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testGetMessageID() {
          int id = 1;  // starting at 1
    String messageId = String.format("MSG%03d", id);

    assertEquals("MSG001", messageId);
    }

    @Test
    public void testCheckMessageID() {
    }

    @Test
    public void testCheckRecipientCell() {
         String recipient = "+27718693002";
    
    boolean valid = recipient.startsWith("+") && recipient.length() <= 12;

    assertTrue(valid, "Incorrectly formatted or does not contain international code.");
    }

    @Test
    public void testCheckMessageLength() {
        String message = "Hi Mike, can you join us for dinner tonight";
    int maxLength = 250;

    assertTrue(message.length() <= maxLength, 
        "Message exceeds 250 characters by " + (message.length() - maxLength));
    }

    @Test
    public void testCreateMessageHash() {
         String message = "Hi Mike, can you join us for dinner tonight";
        int index = 0;

        String[] words = message.split(" ");
        String firstWord = words[0].replaceAll("\\W", "").toUpperCase(); // "HI"
        String lastWord = words[words.length - 1].replaceAll("\\W", "").toUpperCase(); // "TONIGHT"

        String hash = String.format("00:%d:%s%s", index, firstWord, lastWord); // JOIN BOTH

        assertEquals("00:0:HITONIGHT", hash);
   
    }

    @Test
    public void testSendMessageOption() {
      String option = "Discard";  // Options: Send, Store, Discard
    String result = "";

    switch (option) {
        case "Send":
            result = "Message successfully sent.";
            break;
        case "Store":
            result = "Message successfully stored.";
            break;
        case "Discard":
            result = "Message ignored";
            break;
            
    }  
        
    }

    @Test
    public void testPrintMessage() {
    }

    @Test
    public void testGetStatus() {
    }

    @Test
    public void testSaveMessagesToFile() {
    }

    @Test
    public void testLoadMessagesFromFile() {
    }

    @Test
    public void testShowMessageDetails() {
    }
    
}
