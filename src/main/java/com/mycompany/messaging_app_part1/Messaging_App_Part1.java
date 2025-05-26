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
import java.util.List;

public class Messaging_App_Part1 {
    
    public static void main(String[] args) {
       
      Message.loadMessagesFromFile("messages.json");

    
        
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

                        // Message input with length check
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

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    quit = true;
                    System.out.println("Quitting application. Goodbye!");
                    Message.saveMessagesToFile("messages.json");

                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
                    
            }
        }
    }
}
    //change all messageID to getmessageID
//Change Phone number length
