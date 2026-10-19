/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // --- PART 1: REGISTRATION ---
        System.out.println("================================");
        System.out.println("       CHAT APP REGISTRATION     ");
        System.out.println("================================");

        String firstName, lastName, username, password, cellPhoneNumber;
        Login user;

        while (true) {
            System.out.print("Enter your first name: ");
            firstName = input.nextLine();
            System.out.print("Enter your last name: ");
            lastName = input.nextLine();
            System.out.print("Enter your username: ");
            username = input.nextLine();
            System.out.print("Enter your password: ");
            password = input.nextLine();
            System.out.print("Enter your South African cell phone number: ");
            cellPhoneNumber = input.nextLine();

            user = new Login(username, password, cellPhoneNumber, firstName, lastName);
            System.out.println();

            boolean validUser = user.checkUserName();
            boolean validPass = user.checkPasswordComplexity();
            boolean validCell = user.checkCellPhoneNumber();

            System.out.println(validUser ? "Username successfully captured." : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            System.out.println(validPass ? "Password successfully captured." : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            System.out.println(validCell ? "Cell number successfully captured." : "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");

            if (validUser && validPass && validCell) break;
            System.out.println("\nPlease re-enter your details.\n================================");
        }

        System.out.println("\n" + user.registerUser());

        // --- PART 1: LOGIN ---
        System.out.println("\n================================\n             LOGIN              \n================================");
        boolean loginSuccessful = false;
        int attempts = 0;
        
        while (!loginSuccessful && attempts < 3) {
            System.out.print("Enter your username: ");
            String loginUser = input.nextLine();
            System.out.print("Enter your password: ");
            String loginPass = input.nextLine();

            loginSuccessful = user.loginUser(loginUser, loginPass);
            System.out.println(user.returnLoginStatus(loginSuccessful));

            if (!loginSuccessful) {
                attempts++;
                if (attempts < 3) System.out.println("Attempts remaining: " + (3 - attempts) + "\n================================");
                else System.out.println("Too many failed attempts. Exiting.");
            }
        }

        // --- PART 2: MESSAGING (Only if logged in) ---
        if (loginSuccessful) {
            System.out.println("\nWelcome to QuickChat.");
            
            // 1. Ask user how many messages they want to send
            System.out.print("How many messages do you wish to enter? ");
            int numMessages = 0;
            try {
                numMessages = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Exiting.");
                System.exit(0);
            }

            boolean running = true;
            while (running) {
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                String menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        // 2. For loop for entering the set number of messages
                        for (int i = 1; i <= numMessages; i++) {
                            System.out.println("\n--- Message " + i + " of " + numMessages + " ---");
                            
                            String recipient;
                            Message msg;
                            
                            while (true) {
                                System.out.print("Enter recipient cell number: ");
                                recipient = input.nextLine();
                                msg = new Message(i, recipient, "");
                                String cellStatus = msg.checkRecipientCell();
                                System.out.println(cellStatus);
                                if (cellStatus.equals("Cell phone number successfully captured.")) break;
                            }

                            String text;
                            while (true) {
                                System.out.print("Enter message: ");
                                text = input.nextLine();
                                msg = new Message(i, recipient, text);
                                if (msg.checkMessageLength()) {
                                    System.out.println("Message ready to send.");
                                    break;
                                } else {
                                    System.out.println("Message exceeds 250 characters by " + (text.length() - 250) + "; please reduce the size.");
                                }
                            }

                            System.out.println("\n" + msg.printMessages());

                            System.out.println("\n1) Send Message\n2) Store Message\n3) Disregard Message");
                            System.out.print("Choose an option: ");
                            String sendChoice = input.nextLine();
                            
                            System.out.println(msg.SentMessage(sendChoice));
                            
                            // If they chose to store it, write to JSON
                            if (sendChoice.equals("2")) {
                                msg.storeMessage();
                            }
                        }
                        break;

                    case "2":
                        System.out.println("Coming Soon.");
                        break;

                    case "3":
                        running = false;
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
        input.close();
    }
}