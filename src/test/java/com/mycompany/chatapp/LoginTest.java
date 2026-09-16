/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Login class.
 * Test data has been taken directly from the PoE brief.
 *
 * @author Student
 */
public class LoginTest {

    // =========================================
    // ASSERT TRUE TESTS
    // =========================================

    @Test
    public void testLoginSuccessful() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        user.registerUser();

        assertTrue(
                user.loginUser("kyl_1", "Ch&sec@ke99!")
        );
    }

    @Test
    public void testUsernameCorrectlyFormatted() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    // =========================================
    // ASSERT FALSE TESTS
    // =========================================

    @Test
    public void testLoginFailed() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        user.registerUser();

        assertFalse(
                user.loginUser("wrong", "wrong")
        );
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {

        Login user = new Login(
                "kyle!!!!!!",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {

        Login user = new Login(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    // =========================================
    // ASSERT EQUALS TESTS
    // =========================================

    @Test
    public void testUsernameSuccessMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Username successfully captured.",
                user.getUsernameStatus()
        );
    }

    @Test
    public void testInvalidUsernameMessage() {

        Login user = new Login(
                "kyle!!!!!!",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.",
                user.getUsernameStatus()
        );
    }

    @Test
    public void testPasswordSuccessMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Password successfully captured.",
                user.getPasswordStatus()
        );
    }

    @Test
    public void testInvalidPasswordMessage() {

        Login user = new Login(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a "
                + "capital letter, a number, and a special character.",
                user.getPasswordStatus()
        );
    }

    @Test
    public void testCellPhoneSuccessMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Cell number successfully captured.",
                user.getCellPhoneStatus()
        );
    }

    @Test
    public void testCellPhoneFailureMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "Cell phone number incorrectly formatted or does not "
                + "contain international code.",
                user.getCellPhoneStatus()
        );
    }

    @Test
    public void testSuccessfulRegistrationMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals(
                "User registered successfully.",
                user.registerUser()
        );
    }

    @Test
    public void testSuccessfulLoginMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        user.registerUser();

        boolean result =
                user.loginUser("kyl_1", "Ch&sec@ke99!");

        assertEquals(
                "Welcome Kyle Smith it is great to see you again.",
                user.returnLoginStatus(result)
        );
    }

    @Test
    public void testFailedLoginMessage() {

        Login user = new Login(
                "kyl_1",
                "Ch&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        user.registerUser();

        boolean result =
                user.loginUser("wrong", "wrong");

        assertEquals(
                "Username or password incorrect, please try again.",
                user.returnLoginStatus(result)
        );
    }
}