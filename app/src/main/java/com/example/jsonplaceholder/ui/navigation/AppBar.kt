package com.example.jsonplaceholder.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


import androidx.navigation.NavController
import com.example.jsonplaceholder.ui.login.LoginScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.jsonplaceholder.ui.theme.Shapes
import com.example.jsonplaceholder.ui.theme.SkyBlue
import com.example.jsonplaceholder.ui.theme.White

@Composable
fun AppBar(
    scope: CoroutineScope, scaffoldState: ScaffoldState, targetScreen: Screen, navController: NavController
){
    if(targetScreen.title=="Login")
    {
        TopAppBar(
            title = {
                Text(targetScreen.title)
            },
            backgroundColor = White,
            contentColor = SkyBlue
        )
    }
    else if(targetScreen.mainTab) {
        TopAppBar(
            title = {
                Text(targetScreen.title)
            },
            backgroundColor = White,
            contentColor = SkyBlue,
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Toggle drawer"
                    )

                }
            }
        )
    }

    else
    {
        TopAppBar(
            title = {
                Text(targetScreen.title)
            },
            backgroundColor = White,
            contentColor = SkyBlue,
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigateUp()
//                        scope.launch {
//                            scaffoldState.drawerState.open()
//                        }
                    }
                ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Toggle drawer"
                        )
                    }


            }
        )
    }
}