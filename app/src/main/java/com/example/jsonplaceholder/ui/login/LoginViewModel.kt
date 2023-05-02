package com.example.jsonplaceholder.ui.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.ui.signup.User
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {

    var loginUser: User = User()



    var email: MutableState<String> = mutableStateOf(loginUser.email)
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf(loginUser.password)
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")



    var isEnabledLoginButton: MutableState<Boolean> = mutableStateOf(false)


    var isLoginSuccess: MutableState<Boolean> = mutableStateOf(false)
    var loginErrorMessage: MutableState<String> = mutableStateOf("")
    private fun shouldEnabledLoginButton() {
        isEnabledLoginButton.value =
            emailErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()

                && email.value.isNotEmpty()
                && password.value.isNotEmpty()

    }

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnabledLoginButton()
    }

    fun validatePassword() {
        if (password.value.length<= 6) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be greater than 6"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
       shouldEnabledLoginButton()
    }
    fun login()
    {
        Firebase.auth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // TODO: Handle successful login
                   isLoginSuccess.value=true
                    loginErrorMessage.value=""
                } else {
                    // TODO: Handle login failure
                    val exception = task.exception as FirebaseAuthException
                    isLoginSuccess.value= false
                    loginErrorMessage.value = exception.message ?: "An unknown error occurred"
                }
            }

    }
}