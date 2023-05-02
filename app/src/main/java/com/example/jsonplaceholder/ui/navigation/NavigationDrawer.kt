package com.example.jsonplaceholder.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.ui.album.Albums

import com.example.jsonplaceholder.ui.album.albumPhotos.AlbumPhotos
import com.example.jsonplaceholder.ui.comments.Comments
import com.example.jsonplaceholder.ui.login.LoginScreen
import com.example.jsonplaceholder.ui.logout.LogOut
import com.example.jsonplaceholder.ui.signup.SignupScreen

import com.example.jsonplaceholder.ui.photos.Photos
import com.example.jsonplaceholder.ui.posts.postComments.PostComments
import com.example.jsonplaceholder.ui.posts.Posts
import com.example.jsonplaceholder.ui.todos.Todos
import com.example.jsonplaceholder.ui.users.userAlbums.UserAlbums
import com.example.jsonplaceholder.ui.users.Users
import com.example.jsonplaceholder.ui.users.userDetails.UserDetails
import com.example.jsonplaceholder.ui.users.userPosts.UserPosts
import com.example.jsonplaceholder.ui.users.userTodos.UserTodos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavigationScreen()
{
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val targetScreen = rememberSaveable { mutableStateOf(Screen.LoginScreen) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBar(scope = scope, scaffoldState = scaffoldState, targetScreen = targetScreen.value,navController) },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
       Navigation(navController = navController, targetScreen)
    }
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){

    val items= Screen.values().filter { it.mainTab }

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )

        }


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items, selected = currentRoute == items.route, onItemClick = {

                navController.navigate(items.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }

            })
        }

    }
}


@Composable
fun DrawerItem(item: Screen, selected: Boolean, onItemClick: (Screen) -> Unit){
    val background = if(selected) R.color.sky_blue else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {

        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )

    }

}

@Composable
fun Navigation(navController: NavHostController, targetScreen: MutableState<Screen>){

    NavHost(navController, startDestination = Screen.LoginScreen.route){

        composable(Screen.LoginScreen.route){
            targetScreen.value=Screen.LoginScreen
            LoginScreen(navController)
        }

        composable(Screen.SignUpScreen.route){
            targetScreen.value=Screen.SignUpScreen
            SignupScreen(navController)
        }

        composable(Screen.Posts.route) {
            targetScreen.value= Screen.Posts
            Posts(navController)
        }

        composable("${Screen.PostComments.route}/{postId}"){
            targetScreen.value= Screen.PostComments
            PostComments(
                it.arguments?.getString("postId")!!
            )
        }

        composable(Screen.Photos.route){
            targetScreen.value= Screen.Photos
           Photos(navController)
        }

        composable(Screen.Comments.route){
            targetScreen.value= Screen.Comments
            Comments(navController)
        }

        composable(Screen.Albums.route){
            targetScreen.value= Screen.Albums
            Albums(navController)
        }

        composable("${Screen.AlbumPhotos.route}/{albumId}"){
            targetScreen.value= Screen.AlbumPhotos
            AlbumPhotos(
                it.arguments?.getString("albumId")!!
            )
        }

        composable(Screen.Users.route){
            targetScreen.value= Screen.Users
            Users(navController )
        }

        composable("${Screen.UserDetails.route}/{userId}"){
            targetScreen.value= Screen.UserDetails
            UserDetails(   it.arguments?.getString("userId")!!,navController )
        }


        composable("${Screen.UserAlbums.route}/{userId}"){
            targetScreen.value= Screen.UserAlbums
            UserAlbums(
                it.arguments?.getString("userId")!!,
                navController
            )
        }

        composable("${Screen.UserTodos.route}/{userId}"){
            targetScreen.value= Screen.UserTodos
            UserTodos(
                it.arguments?.getString("userId")!!,
                navController
            )
        }
        composable("${Screen.UserPosts.route}/{userId}"){
            targetScreen.value= Screen.UserPosts
            UserPosts(
                it.arguments?.getString("userId")!!,
                navController
            )
        }


        composable(Screen.Todos.route){
            targetScreen.value= Screen.Todos
            Todos(navController)
        }

        composable(Screen.LogOut.route){
            targetScreen.value= Screen.LogOut
            LogOut(navController)
        }

    }

}