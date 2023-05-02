package com.example.jsonplaceholder.ui

import  com.example.jsonplaceholder.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jsonplaceholder.MainActivity

import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }
}

@Composable
fun SplashScreen(navigateTo: () -> Unit) {

    val splashColor =  Color(0xFFFEFEFE)
    Surface(color = splashColor) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Add your splash screen content here, such as a logo or image
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }

        LaunchedEffect(Unit) {
            // Simulate a delay for the splash screen
            delay(1000)

            // Navigate to the next activity
            navigateTo()
        }
    }
}

