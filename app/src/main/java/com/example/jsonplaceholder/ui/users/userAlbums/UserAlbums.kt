package com.example.jsonplaceholder.ui.users.userAlbums

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jsonplaceholder.ui.album.ListAlbums
import com.example.jsonplaceholder.ui.navigation.Screen

@Composable
fun UserAlbums(userId: String, navController: NavHostController, viewModel: UserAlbumsViewModel = hiltViewModel())
{
    viewModel.getUserAlbums(userId)
    val userAlbums by viewModel.userAlbums.collectAsState()
    //Navigation
    val navigateToAlbumPhotos: (String) -> Unit = { albumId ->
        navController.navigate("${Screen.AlbumPhotos.route}/$albumId")
    }

    ListAlbums(userAlbums, navigateToAlbumPhotos )

}