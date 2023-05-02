package com.example.jsonplaceholder.ui.logout

import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import com.example.jsonplaceholder.ui.components.Alert
import com.example.jsonplaceholder.ui.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.format.TextStyle

@Composable
fun LogOut(navController: NavController)
{
    //Google SignOut
    val context = LocalContext.current
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //.requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)


    val navigateToLogin: () -> Unit = {
        Firebase.auth.signOut()
        googleSignInClient.signOut()

        navController.navigate(Screen.LoginScreen.route)
    }
    val alertStatus=Alert(title = "LogOut", message = "Do you want to LogOut?",navigateToLogin)



    if(alertStatus) {
        Text(
            text = "Since you don't want to logout. Please select other tabs and explore app ",
            fontWeight = FontWeight.Bold,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 25.sp
            ),

            modifier = Modifier.padding(10.dp)
        )
    }



}


