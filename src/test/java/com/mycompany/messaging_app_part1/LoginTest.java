/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
// Assistance from ChatGPT (OpenAI, 2025). Retrieved from https://chat.openai.com/

package com.mycompany.messaging_app_part1;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    public LoginTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        // Create a valid Login instance for testing
        login = new Login("user_", "Password1!", "John", "Doe", "+27831234567");
    }

    @AfterEach
    public void tearDown() {
        login = null;
    }

    @Test
    public void testCheckUserName() {
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserValid() {
        String result = login.registerUser();
        assertTrue(result.contains("Username successfully captured"));
    }

    @Test
    public void testLoginUserCorrectCredentials() {
        assertTrue(login.loginUser("user_", "Password1!"));
    }

    @Test
    public void testLoginUserIncorrectCredentials() {
        assertFalse(login.loginUser("wrong", "Password1!"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String result = login.returnLoginStatus("user_", "Password1!");
        assertTrue(result.contains("Welcome John Doe"));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String result = login.returnLoginStatus("user_", "wrongpass");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}
