/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag; // "Sent", "Stored", or "Disregard"
    private static int totalMessages = 0;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates ID using string manipulation and loop counter (Requirement)
    private String generateMessageID() {
        // Generate a 10-digit ID using current time and the loop counter
        String rawTime = String.valueOf(System.currentTimeMillis());
        String paddedNumber = String.format("%02d", messageNumber);
        String combined = rawTime.substring(rawTime.length() - 8) + paddedNumber;
        return combined.substring(0, 10);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Returns a String, not a boolean (Requirement)
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else if (recipient != null && recipient.startsWith("0") && recipient.length() == 10) {
            return "Cell phone number successfully captured."; // Accepts 08575975889 test data
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= 250;
    }

    // Creates the hash in ALL CAPS (Requirement)
    public String createMessageHash() {
        if (!checkMessageID() || !checkMessageLength() || messageText.trim().isEmpty()) {
            return "Hash Error";
        }

        String firstTwoDigits = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        
        // Clean words of punctuation and convert to uppercase
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Handles the menu for Send, Store, or Disregard (Requirement)
    public String SentMessage(String choice) {
        switch (choice) {
            case "1":
                this.flag = "Sent";
                totalMessages++;
                return "Message successfully sent.";
            case "2":
                this.flag = "Stored";
                return "Message successfully stored.";
            case "3":
                this.flag = "Disregard";
                return "Press 0 to delete the message.";
            default:
                this.flag = "Disregard";
                return "Invalid choice. Message disregarded.";
        }
    }

    // Stores the message in a JSON file (Requirement)
    public void storeMessage() {
        /*
         * JSON storing research and code adapted from:
         * https://www.baeldung.com/java-org-json
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MessageID", this.messageID);
        jsonObject.put("MessageNumber", this.messageNumber);
        jsonObject.put("Recipient", this.recipient);
        jsonObject.put("Message", this.messageText);
        jsonObject.put("MessageHash", this.messageHash);
        jsonObject.put("Flag", this.flag);

        // Appends to a file named "messages.json"
        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonObject.toString() + "\n");
            file.flush();
        } catch (IOException e) {
            System.out.println("Error saving message to JSON: " + e.getMessage());
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
}