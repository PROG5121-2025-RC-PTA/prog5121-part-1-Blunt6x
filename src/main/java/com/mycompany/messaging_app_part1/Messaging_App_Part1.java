/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// Assistance from ChatGPT (OpenAI, 2025). Retrieved from https://chat.openai.com/

package com.mycompany.messaging_app_part1;//Part two 


/**
 *
 * @author User
 */

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Messaging_App_Part1 {
     // --- TEST DATA (Required for PoE) ---
      public static void loadTestData() {
        Message test1 = new Message(1, "+27834557896", "Did you get the cake?");
        test1.sendMessageOption("Send");

        Message test2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        test2.sendMessageOption("Store");

        Message test3 = new Message(3, "+27834484567", "Yahoooo, I am at your gate.");
        test3.sendMessageOption("Discard");

        Message test4 = new Message(4, "0838884567", "It is dinner time !");
        test4.sendMessageOption("Send");

        Message test5 = new Message(5, "+27838684567", "Ok, I am leaving without you.");
        test5.sendMessageOption("Store");
    }

    public static void main(String[] args) {
        
         
        Message.loadMessagesFromFile("messages.json");

        loadTestData();
     

    
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (e.g. +27812345678): ");
        String cellPhone = scanner.nextLine();

        Login login = new Login(username, password, firstName, lastName, cellPhone);

        System.out.println(login.registerUser());

        System.out.println("\nLogin Time!");

        System.out.print("Enter Username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
        System.out.println(loginStatus);

        if (!loginStatus.startsWith("Welcome")) {
            System.out.println("Exiting application due to failed login.");
            return; // Exit program if login failed
        }

        // PART 2 
        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages would you like to enter? ");
        int numMessages = 0;
        while (true) {
            try {
                numMessages = Integer.parseInt(scanner.nextLine());
                if (numMessages <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        List<Message> messages = new ArrayList<>();
        int totalMessagesSent = 0;

        boolean quit = false;

        while (!quit) {
    String option = JOptionPane.showInputDialog(
        null,
        "Menu Options:\n" +
        "1) Send Messages\n" +
        "2) Show recently sent messages\n" +
        "3) Quit\n\n" +
        "Choose an option (1-3):",
        "QuickChat Menu",
        JOptionPane.QUESTION_MESSAGE
    );

    if (option == null) { // User pressed Cancel or closed dialog
        option = "3";    // Treat as quit
    }
            switch (option) {
                case "1":
                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("\n--- Enter details for message " + i + " ---");

                        // Recipient input with validation
                        String recipient;
                        while (true) {
                            System.out.print("Enter recipient phone number (max 10 chars with international code, e.g. +27xxxxxxxxx): ");
                            recipient = scanner.nextLine();
                            if (recipient.length() > 12 || !recipient.startsWith("+")) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            } else {
                                break;
                            }
                            
                        }

                        // Message input with some length check (anything below 2 inches should be embarassing)
                        String messageText;
                        while (true) {
                            System.out.print("Enter message (max 250 characters): ");
                            messageText = scanner.nextLine();
                            if (messageText.length() > 250) {
                                int excess = messageText.length() - 250;
                                System.out.println("Message exceeds 250 characters by " + excess + ", please reduce size.");
                            } else {
                                System.out.println("Message sent.");
                                break;
                            }
                        }

                        // Create Message object
                       
                       
                  
                        Message msg = new Message(i, recipient, messageText);

                        // Show Message ID generated
                        System.out.println("Message ID generated: " + msg.getMessageID());

                        // Ask user what to do with message
                        String status = msg.sendMessageOption();

                        if (status.equals("Sent")) {
                            totalMessagesSent++;
                        }

                        messages.add(msg);

                        // Show full details after action
                        System.out.println("\nMessage Details:");
                        System.out.println(msg.printMessage());
                    }
                    System.out.println("\nTotal number of messages sent: " + totalMessagesSent);
                    break;
                    case "3":
                    quit = true;
                    System.out.println("Quitting application. Goodbye!");
                    Message.saveMessagesToFile("messages.json");

                    break;
                    //deletemessage

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
                    
                case "2":
                      boolean backToMenu = false;
                      
    while (!backToMenu) {
        //submenu
        String subOption = JOptionPane.showInputDialog(
            "Select an option:\n" +
            "1) Show Stored Messages\n" +
             "2) Search by Message ID\n" +
                 "3) Display Message Report\n" +
                   "4) Display Longest Message\n" +
                  "5) Search Messages by Recipient\n" +
                   
                       "6) Back to Main Menu\n"+
                 "7) Delete Message by THe HASH"
        );

        if (subOption == null) break; // if user cancels

        switch (subOption) {
            case "1":
                StringBuilder stored = new StringBuilder("Stored Messages:\n");
                for (Message m : Message.sentMessages) {
                    if ("Stored".equalsIgnoreCase(m.getStatus())) {
                        stored.append(m.printMessage()).append("\n\n");
                    }
                }
                JOptionPane.showMessageDialog(null, stored.toString(), "Stored Messages", JOptionPane.INFORMATION_MESSAGE);
                break;
//done
            case "2":
                String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                boolean found = false;
                for (Message m : Message.sentMessages) {
                    if (m.getMessageID().equals(searchID)) {
                        JOptionPane.showMessageDialog(null, m.printMessage(), "Message Found", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Message ID not found.");
                }
                break;
                //done

            case "3":
                StringBuilder report = new StringBuilder("Sent Message Report:\n");
                for (Message m : Message.sentMessages) {
                    if ("Sent".equalsIgnoreCase(m.getStatus())) {
                       report.append("Hash: ").append(m.createMessageHash())
                        .append("\nRecipient: ").append(m.getRecipient())
                        .append("\nMessage: ").append(m.getMessage())

                              .append("\n\n");
                    }
                }
                JOptionPane.showMessageDialog(null, report.toString(), "Message Report", JOptionPane.INFORMATION_MESSAGE);
                break;
                //NEWNEWNEWNEWNEW_START
                case "4":
                    
               Message longest = Message.getLongestMessage(Message.sentMessages);
                   if (longest != null) {
                JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.printMessage());
              } else {
                JOptionPane.showMessageDialog(null, "No sent messages available.");
                     }
                         break;
                    case "5":
                     String searchRecipient = JOptionPane.showInputDialog("Enter recipient phone number:");
                        StringBuilder foundMsgs = new StringBuilder();
                             for (Message m : Message.sentMessages) {
                       if (m.getRecipient().equals(searchRecipient)) {
                      foundMsgs.append(m.printMessage()).append("\n\n");
        }
    }
                           if (foundMsgs.length() == 0) {
                           JOptionPane.showMessageDialog(null, "No messages found for that recipient.");
                      } else {
                        JOptionPane.showMessageDialog(null, foundMsgs.toString(), "Messages Found", JOptionPane.INFORMATION_MESSAGE);
                 }
                       break;
    //NEWNEWNEWNEWNEW_END

            case "6":
                backToMenu = true;
                break;
                //done
              case "7":
    String hashToDelete = JOptionPane.showInputDialog("Enter the message hash to delete:");

    Iterator<Message> iterator = Message.sentMessages.iterator();
    boolean hashFound = false;

    while (iterator.hasNext()) {
        Message m = iterator.next();
        if (m.createMessageHash().equals(hashToDelete)) {
            iterator.remove(); // deletes it from the list
            JOptionPane.showMessageDialog(null, "Message with hash deleted successfully.");
            hashFound = true;
            break;
        }
    }

    if (!hashFound) {
        JOptionPane.showMessageDialog(null, "No message found with the given hash.");
    }
    break;
 
                
            default:
                JOptionPane.showMessageDialog(null, "Invalid option. Choose between 1-7.");
        }
    }
    break;
                    
                 
    
    
                
            }
        }
    }
}
    //change all messageID to getmessageID
//Change Phone number length
//add the array list thingy
//remove coming soon and add sum to display the array list
