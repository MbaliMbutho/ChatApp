/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "A".repeat(260);
        Message msg = new Message(1, "+27718693002", longText);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {
        // Test data from page 15
        Message msg = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
        
        Message msg2 = new Message(2, "08575975889", "Hi Keegan");
        assertEquals("Cell phone number successfully captured.", msg2.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(1, "08966553", "Test");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        // Test Case 1 expects 00:0:HITONIGHT according to the brief structure
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // First two chars of ID + ":" + message number + ":" + FIRSTWORD + LASTWORD
        String expectedFirstTwo = msg.getMessageID().substring(0, 2);
        assertEquals(expectedFirstTwo + ":1:HITONIGHT", msg.getMessageHash());
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg1 = new Message(1, "+27718693002", "Hi Mike");
        msg1.SentMessage("1"); // Send
        Message msg2 = new Message(2, "08575975889", "Hi Keegan");
        msg2.SentMessage("1"); // Send
        
        assertEquals(2, Message.returnTotalMessages());
    }
}