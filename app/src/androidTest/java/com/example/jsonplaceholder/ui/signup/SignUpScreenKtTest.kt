package com.example.jsonplaceholder.ui.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.jsonplaceholder.ui.signup.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class SignUpScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val signInViewModel = SignUpUserViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            SignupScreen(
                navController,
                signInViewModel
            )
        }
    }

    @Test
    fun signupScreenButtonNotEnabled() {
        val usernameNode = composeTestRule
            .onNodeWithTag("user name")
            .assertIsDisplayed()

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val confirmpassword = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()

        val signUpNode = composeTestRule.onNodeWithTag("Sign up")

        usernameNode.performTextInput("invalidName")
        signUpNode.assertIsNotEnabled()


        emailNode.performTextInput("invalidEmail")
        signUpNode.assertIsNotEnabled()


        passwordNode.performTextInput("12345678")
        signUpNode.assertIsNotEnabled()

        confirmpassword.performTextInput("12345678")
        signUpNode.assertIsNotEnabled()


    }

    @Test
    fun signupScreenButtonEnabled() {
        val usernameNode = composeTestRule
            .onNodeWithTag("user name")
            .assertIsDisplayed()

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val confirmpassword = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()

        val signUpNode = composeTestRule.onNodeWithTag("Sign up")

        usernameNode.performTextInput("testworld")
        signUpNode.assertIsNotEnabled()

        emailNode.performTextInput("valid@email")
        passwordNode.performTextInput("1234567")
        confirmpassword.performTextInput("1234567")
        signUpNode.assertIsNotEnabled()

        emailNode.performTextInput(".com")
        signUpNode.assertIsEnabled()
    }

    @Test
    fun validateInvalidUserName(){
        val usernameNode = composeTestRule
            .onNodeWithTag("user name")
            .assertIsDisplayed()
        usernameNode.performTextInput("123")

        val usernameErrorNode = composeTestRule
            .onNodeWithTag("username_error")
            .assertIsDisplayed()

        usernameErrorNode.assertTextEquals("User Name should be more than 5 chars")

    }

    @Test
    fun validateValidUserName(){
        val usernameNode = composeTestRule
            .onNodeWithTag("user name")
            .assertIsDisplayed()
        usernameNode.performTextInput("123234")

    }

    @Test
    fun validateInvalidPassword(){
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()
        passwordNode.performTextInput("123")

        val passwordErrorNode = composeTestRule
            .onNodeWithTag("password_error")
            .assertIsDisplayed()

        passwordErrorNode.assertTextEquals("Password should be greater than 6")

    }

    @Test
    fun validateValidPassword(){
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()
        passwordNode.performTextInput("res123")

    }

    @Test
    fun validateInvalidConfirmPassword(){
        val confirmPasswordNode = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()
        confirmPasswordNode.performTextInput("123")

        val confirmPasswordErrorNode = composeTestRule
            .onNodeWithTag("confirm password_error")
            .assertIsDisplayed()

        confirmPasswordErrorNode.assertTextEquals("Password did not match")

    }

    @Test
    fun validateValidConfirmPassword(){
        val confirmPasswordNode = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()
        confirmPasswordNode.performTextInput("res123")

    }
}

