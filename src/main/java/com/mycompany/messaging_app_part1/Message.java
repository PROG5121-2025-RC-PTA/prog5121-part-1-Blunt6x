/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messaging_app_part1;

/**
 *
 * @author User
 */


import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.swing.JOptionPane;


import java.util.Random;
import java.util.Scanner;



public class Message {
    
    public String getRecipient() {
    return recipient;
}

public String getMessage() {
    return message;
}
    public static List<Message> storedMessages = new ArrayList<>();
public static List<Message> discardedMessages = new ArrayList<>();
public static List<String> messageHashes = new ArrayList<>();
public static List<String> messageIDs = new ArrayList<>();

    private String messageID;
public static List<Message> sentMessages = new ArrayList<>();

    /**
     *
     * @return
     */
    public String getMessageID() {//idk what this does
    return messageID;
}
    
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String status; // "Sent", "Stored", "Discarded"
    


    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10)); // 0-9 digit
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public boolean checkRecipientCell() {
        // Check if recipient is max 12 chars and starts with + (international code)
       
        
    if (recipient == null || !recipient.startsWith("+")) {
        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
        return false;
    }

    String number = recipient.substring(1); // remove '+'
    

   
    if (!number.matches("\\d{12}")) {
        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
        return false;
    }

    System.out.println("Cell phone number successfully captured.");
    return true;
}


    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    public String createMessageHash() {
        // Format: first 2 digits of messageID : message number : first word + last word in caps
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.split(" ");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }
//Overload
       public String sendMessageOption(String choice) {
    switch (choice.toLowerCase()) {
        case "1":
            status = "Sent";
            sentMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            break;
        case "3":
            status = "Stored";
            storedMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            break;
        case "2":
            status = "Discarded";
            discardedMessages.add(this);
            break;
        default:
            status = "Discarded";
            discardedMessages.add(this);
            break;
    }
    return status;
}
    
   public String sendMessageOption() {


    Scanner scanner = new Scanner(System.in);
    System.out.println("Choose an option: \n1) Send Message\n2) Disregard Message\n3) Store Message");
    String choice = scanner.nextLine();

    switch (choice) {
        case "1":
            status = "Sent";
            sentMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            System.out.println("Message successfully sent.");
            showMessageDetails();
            break;

        case "2":
            status = "Discarded";
            discardedMessages.add(this);
            System.out.println("Message discarded.");
            break;

        case "3":
            status = "Stored";
            storedMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            System.out.println("Message successfully stored.");
            showMessageDetails();
            break;

        default:
            status = "Discarded";
            discardedMessages.add(this);
            System.out.println("Invalid option. Message discarded.");
    }

    return status;
}


    public String printMessage() {
        return "MessageID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + message +
                "\nStatus: " + status;
    }

    // Getters if needed
    public String getStatus() {
        return status;
    }
public static void saveMessagesToFile(String filename) {
    Gson gson = new Gson();
    try (Writer writer = new FileWriter(filename)) {
        gson.toJson(sentMessages, writer);
        System.out.println("Messages saved to " + filename);
    } catch (IOException e) {
        System.out.println("Failed to save messages: " + e.getMessage());
    }
}

public static void loadMessagesFromFile(String filename) {
    Gson gson = new Gson();
    try (Reader reader = new FileReader(filename)) {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        List<Message> loadedMessages = gson.fromJson(reader, listType);
        if (loadedMessages != null) {
            sentMessages.clear();
            sentMessages.addAll(loadedMessages);
            System.out.println("Messages loaded from " + filename);
        }
    } catch (FileNotFoundException e) {
        System.out.println("No saved messages yet.");
    } catch (IOException e) {
        System.out.println("Failed to load messages: " + e.getMessage());
    }
}

public void showMessageDetails() {//displays the message details called after case 1 or 3
       
    String details = "MessageID: " + messageID + 
                     "\nMessage Hash: " + messageHash + 
                     "\nRecipient: " + recipient + 
                     "\nMessage: " + message;
    JOptionPane.showMessageDialog(null, details, "Message Details", JOptionPane.INFORMATION_MESSAGE);
}

public static Message getLongestMessage(List<Message> messages) {
    Message longest = null;
    for (Message m : messages) {
        if ("Sent".equalsIgnoreCase(m.getStatus())) {
            if (longest == null || m.getMessage().length() > longest.getMessage().length()) {
                longest = m;
            }
        }
    }
    return longest;
}
}
