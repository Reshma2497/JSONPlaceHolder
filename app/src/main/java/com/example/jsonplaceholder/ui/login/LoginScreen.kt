package com.example.jsonplaceholder.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.ui.navigation.Screen
import com.example.jsonplaceholder.ui.theme.JSONPlaceHolderTheme
import com.example.jsonplaceholder.ui.theme.SkyBlue
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    navController: NavController, viewModel: LoginViewModel = viewModel()

) {
    JSONPlaceHolderTheme {

        //get current context
        val context = LocalContext.current

        val navigateToSignUp: () -> Unit = {
            navController.navigate(Screen.SignUpScreen.route)
        }

        //Google signin
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        val googleSignInLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                try {
                    // Handle successful sign-in
                    val account = task.getResult(ApiException::class.java)
                    navController.navigate(Screen.Users.route)
                    // ...
                } catch (e: ApiException) {
                    // Handle failed sign-in
                    // ...
                }
            }
        }

        val googleSignInClient = remember {
            GoogleSignIn.getClient(context, googleSignInOptions)
        }

        val signInIntent = googleSignInClient.signInIntent

        //facebook initialize

        val callbackManager = remember { CallbackManager.Factory.create() }
        //val context = LocalContext.current

        val facebookCallback = remember {
            object : FacebookCallback<LoginResult> {

                override fun onCancel() {}
                override fun onError(error: FacebookException) {
                    TODO("Not yet implemented")
                }

                override fun onSuccess(result: LoginResult) {
                    //onLoginSuccess()
                    navController.navigate(Screen.Users.route)
                }


            }
        }

        LaunchedEffect(callbackManager) {
            LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
        }





        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in to your account",
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    viewModel.email.value = newValue
                    viewModel.validateEmail()
                },
                isError = viewModel.isEmailValid.value,
                label = { Text(text = "Email address") },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.emailErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))

            var passwordVisibility by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = viewModel.password.value,
                onValueChange = { newValue ->
                    viewModel.password.value = newValue
                    viewModel.validatePassword()
                },
                isError = viewModel.isPasswordValid.value,
                label = { Text(text = "Password") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val visibilityIcon =
                        if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.passwordErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {

                    viewModel.login()
                    if (viewModel.isLoginSuccess.value) {
                        Toast.makeText(
                            context,
                            "${viewModel.email.value} : Login Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Screen.Users.route)
                    }
                },

                modifier = Modifier.fillMaxWidth(), enabled = viewModel.isEnabledLoginButton.value
            ) {
                Text(text = "Sign in")
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewModel.loginErrorMessage.value,
                fontSize = 14.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = {
                    navigateToSignUp()
                },
                modifier = Modifier.fillMaxWidth(),

                ) {
                Text(
                    text = "Don't have an account? Sign up",
                    color = SkyBlue
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Or sign in with",
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        LoginManager.getInstance().logInWithReadPermissions(
                            context as Activity,
                            listOf("public_profile", "email")
                        )
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Sign in with Facebook"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = { googleSignInLauncher.launch(signInIntent) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Sign in with Google"
                    )
                }
            }
        }
    }
}



