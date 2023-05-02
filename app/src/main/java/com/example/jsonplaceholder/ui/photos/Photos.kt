package com.example.jsonplaceholder.ui.photos

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.jsonplaceholder.data.model.photos.PhotosModelItemModel
import com.example.jsonplaceholder.ui.components.SearchBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Photos(navController: NavHostController, viewModel: PhotosViewModel = hiltViewModel())
{
    val photos by viewModel.photoList.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val filteredPhotosList = if (searchQuery.value.isBlank()) {
        photos
    } else {
        photos.filter { photos ->
            photos.title!!.contains(searchQuery.value, ignoreCase = true)||
                    photos.albumId.toString()!!.equals(searchQuery.value, ignoreCase = true)
        }
    }
    Scaffold (
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By Photo Title or Album Id")
            ListPhotos(model = filteredPhotosList)



        }
    }
}

@Composable
fun ListPhotos(model: List<PhotosModelItemModel>)
{

    LazyColumn(
        modifier = Modifier
            .padding(top = 10.dp)

    ) {
        itemsIndexed(model) { _, photo ->
            PhotoItem(photo)
            //Divider(color = Color.LightGray, thickness = 1.dp)
        }

    }
}


@Composable
fun PhotoItem(photo: PhotosModelItemModel) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Image(
                //painter = rememberImagePainter(data = "https://picsum.photos/id/${photo.id}/2000"),
                painter = rememberImagePainter(data = photo.url),
                contentDescription = photo.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Album: ${photo.albumId}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = photo.title.orEmpty(),

                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}

