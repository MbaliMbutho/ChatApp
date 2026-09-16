/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.regex.Pattern;

/**
 * The Login class handles user registration and authentication.
 * It stores the user's details and provides methods to validate
 * the username, password and cell phone number.
 *
 * @author Student
 */
public class Login {

    // Fields storing the details entered during registration
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Fields storing the details of a successfully registered user.
    // These are only set once registerUser() succeeds, and are used
    // by loginUser() to verify the credentials entered at login.
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellPhoneNumber;

    /**
     * Constructor - creates a Login object with the user's details.
     */
    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks whether the username contains an underscore and is
     * no more than five characters long.
     *
     * @return true if the username is correctly formatted
     */
    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks whether the password meets the complexity rules:
     * at least eight characters, one capital letter, one number
     * and one special character.
     *
     * @return true if the password is correctly formatted
     */
    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecialCharacter = password.matches(".*[^a-zA-Z0-9].*");

        return hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

    /*
     * Cell phone regular expression reference:
     * Regular-Expressions.info
     * https://www.regular-expressions.info/
     *
     * The expression checks for the South African international
     * code (+27) followed by exactly 9 digits.
     */
    /**
     * Checks whether the cell phone number contains the international
     * code followed by no more than ten characters.
     *
     * @return true if the cell phone number is correctly formatted
     */
    public boolean checkCellPhoneNumber() {

        String regex = "^\\+27[0-9]{9}$";

        return cellPhoneNumber != null
                && Pattern.matches(regex, cellPhoneNumber);
    }

    /**
     * Returns the appropriate username status message.
     *
     * @return the success or failure message for the username
     */
    public String getUsernameStatus() {

        if (checkUserName()) {
            return "Username successfully captured.";
        }

        return "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.";
    }

    /**
     * Returns the appropriate password status message.
     *
     * @return the success or failure message for the password
     */
    public String getPasswordStatus() {

        if (checkPasswordComplexity()) {
            return "Password successfully captured.";
        }

        return "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a "
                + "capital letter, a number, and a special character.";
    }

    /**
     * Returns the appropriate cell phone status message.
     * This message matches the wording required by the PoE brief.
     *
     * @return the success or failure message for the cell phone number
     */
    public String getCellPhoneStatus() {

        if (checkCellPhoneNumber()) {
            return "Cell number successfully captured.";
        }

        return "Cell phone number incorrectly formatted or does not "
                + "contain international code.";
    }

    /**
     * Registers the user if all the details are correctly formatted.
     * Stores the credentials so that the user can log in later.
     *
     * @return the registration status message
     */
    public String registerUser() {

        if (!checkUserName()) {
            return getUsernameStatus();
        }

        if (!checkPasswordComplexity()) {
            return getPasswordStatus();
        }

        if (!checkCellPhoneNumber()) {
            return getCellPhoneStatus();
        }

        registeredUsername = username;
        registeredPassword = password;
        registeredCellPhoneNumber = cellPhoneNumber;

        return "User registered successfully.";
    }

    /**
     * Verifies whether the login details match the registered details.
     *
     * @param enteredUsername the username entered at login
     * @param enteredPassword the password entered at login
     * @return true if the login details are correct
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        return registeredUsername != null
                && registeredPassword != null
                && registeredUsername.equals(enteredUsername)
                && registeredPassword.equals(enteredPassword);
    }

    /**
     * Returns the login status message.
     *
     * @param loginSuccessful whether the login was successful
     * @return the welcome message or the failed login message
     */
    public String returnLoginStatus(boolean loginSuccessful) {

        if (loginSuccessful) {
            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }

    // =========================================================
    // Getters - used by the console interface and unit tests
    // =========================================================
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}