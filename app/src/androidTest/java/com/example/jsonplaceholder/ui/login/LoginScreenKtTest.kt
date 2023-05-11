package com.example.jsonplaceholder.ui.login

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    val loginViewModel = LoginViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            LoginScreen(navController,loginViewModel)
        }
    }

    @Test
    fun loginScreenButtonNotEnabled() {

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val loginNode = composeTestRule.onNodeWithTag("Login")


        emailNode.performTextInput("invalidEmail")
        loginNode.assertIsNotEnabled()


        passwordNode.performTextInput("1234")
       loginNode.assertIsNotEnabled()




    }

    @Test
    fun loginScreenButtonEnabled() {

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val loginNode = composeTestRule.onNodeWithTag("Login")

        emailNode.performTextInput("valid@email")
        passwordNode.performTextInput("1234567")
        loginNode.assertIsNotEnabled()

        emailNode.performTextInput(".com")
        loginNode.assertIsEnabled()
    }

    @Test
    fun validateInvalidEmailAddress(){
        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()
        emailNode.performTextInput("test")

        val emailErrorNode = composeTestRule
            .onNodeWithTag("email_error")
            .assertIsDisplayed()

        emailErrorNode.assertTextEquals("Input proper email id")

    }

    @Test
    fun validateValidEmailAddress(){
        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()
        emailNode.performTextInput("test@gmail.com")
    }

    @Test
    fun validateInValidPassword(){
        val passwordNode= composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()
        passwordNode.performTextInput("test")
        val passwordErrorNode = composeTestRule
            .onNodeWithTag("password_error")
            .assertIsDisplayed()

        passwordErrorNode.assertTextEquals("password should be greater than 6")
    }


}