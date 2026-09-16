/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 * The ChatApp class contains the main method and handles all
 * console input and output for the registration and login feature.
 *
 * @author Student
 */
public class ChatApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       CHAT APP REGISTRATION     ");
        System.out.println("================================");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your username: ");
        String username = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your South African cell phone number: ");
        String cellPhoneNumber = input.nextLine();

        Login user = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        System.out.println();

        // Display the status of each detail entered
        System.out.println(user.getUsernameStatus());
        System.out.println(user.getPasswordStatus());
        System.out.println(user.getCellPhoneStatus());

        // Only proceed to register and login if everything is valid
        if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.println();
            System.out.println(user.registerUser());

            System.out.println();
            System.out.println("================================");
            System.out.println("             LOGIN              ");
            System.out.println("================================");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            boolean loginSuccessful =
                    user.loginUser(loginUsername, loginPassword);

            System.out.println();
            System.out.println(user.returnLoginStatus(loginSuccessful));
        }

        input.close();
    }
}