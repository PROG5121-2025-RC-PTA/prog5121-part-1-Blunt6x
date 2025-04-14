/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// Assistance from ChatGPT (OpenAI, 2025). Retrieved from https://chat.openai.com/

package com.mycompany.messaging_app_part1;

/**
 *
 * @author User
 */

import java.util.Scanner;

public class Messaging_App_Part1 {
    public static void main(String[] args) {
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

        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
    }
}

