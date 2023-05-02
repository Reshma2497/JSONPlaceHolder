package com.example.jsonplaceholder.ui.album.albumPhotos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jsonplaceholder.ui.photos.ListPhotos

@Composable
fun AlbumPhotos(albumId: String, viewModel: AlbumPhotosViewModel = hiltViewModel())
{
    viewModel.getAlbumPhotos(albumId)
    val albumPhotos by viewModel.albumPhotos.collectAsState()

    ListPhotos(albumPhotos)

}