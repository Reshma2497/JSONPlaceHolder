package com.example.jsonplaceholder.ui.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


class SignUpUserViewModel : ViewModel() {

    var regUser: User = User()

    var userName: MutableState<String> = mutableStateOf(regUser.name)
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf(regUser.email)
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf(regUser.password)
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf(regUser.confirmPassword)
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confPasswordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)


    var isSignUpSuccess: MutableState<Boolean> = mutableStateOf(false)
    var signUpErrorMessage: MutableState<String> = mutableStateOf("")

    init { }

    private fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton.value = userNameErrMsg.value.isEmpty()
                && emailErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()
                && confPasswordErrMsg.value.isEmpty()
                && userName.value.isNotEmpty()
                && email.value.isNotEmpty()
                && password.value.isNotEmpty()
                && confirmPassword.value.isNotEmpty()
    }
    fun validateUserName() {
        if (userName.value.length <= 5) {
            isUserNameValid.value = true
            userNameErrMsg.value = "User Name should be more than 5 chars"
        } else {
            isUserNameValid.value = false
            userNameErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validatePassword() {
        if (password.value.length<= 6) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be greater than 6"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (password.value != confirmPassword.value) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "Password did not match"
        } else {
            isConfirmPasswordValid.value = false
            confPasswordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun SignUp(){

        val auth = FirebaseAuth.getInstance()
        regUser.name = userName.value
        regUser.email = email.value
        regUser.password = password.value
        regUser.confirmPassword = confirmPassword.value


            auth.createUserWithEmailAndPassword(regUser.email, regUser.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //navController.navigate(Screen.LoginScreen.route)
                        isSignUpSuccess.value= true
                    } else {
                        val exception = task.exception as FirebaseAuthException
                        isSignUpSuccess.value= false
                        signUpErrorMessage.value = exception.message ?: "An unknown error occurred"
                    }
                }


    }
}