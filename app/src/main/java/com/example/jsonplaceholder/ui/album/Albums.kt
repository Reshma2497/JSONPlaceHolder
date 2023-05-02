package com.example.jsonplaceholder.ui.album

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.jsonplaceholder.data.model.albums.AlbumsModelItemModel
import com.example.jsonplaceholder.ui.components.SearchBar
import com.example.jsonplaceholder.ui.navigation.Screen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Albums(navController: NavHostController, viewModel: AlbumViewModel = hiltViewModel()) {
    val albums by viewModel.albums.collectAsState()

    //Navigation
    val navigateToAlbumPhotos: (String) -> Unit = { albumId ->
        navController.navigate("${Screen.AlbumPhotos.route}/$albumId")
    }
    val searchQuery = remember { mutableStateOf("") }
    val filteredAlbumsList = if (searchQuery.value.isBlank()) {
        albums
    } else {

        albums.filter { albums ->
            albums.title!!.contains(searchQuery.value, ignoreCase = true)
                    ||albums.id.toString().equals(searchQuery.value, ignoreCase = true)

        }
    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By Album Title or Album Id")

            ListAlbums(model=filteredAlbumsList, navigateToAlbumPhotos)

        }
    }
}

@Composable
fun ListAlbums(model: List<AlbumsModelItemModel>,onNextClick: (String) -> Unit )
{


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp)

    ) {
        itemsIndexed(model) { _, album ->
            AlbumItem(album = album, onNextClick = onNextClick)
        }
    }


}




@Composable
fun AlbumItem(album: AlbumsModelItemModel, onNextClick: (String) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp).size(350.dp)
        .clickable {
            onNextClick(album.id.toString())
        }, shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(data = "https://picsum.photos/id/${album.id}/200"),

                contentDescription = null,
                Modifier.size(200.dp)

            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Album ID: ${album.id ?: 0}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "User ID: ${album.userId ?: 0}",
                style = MaterialTheme.typography.body1,
                //fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = album.title.orEmpty(),
                style = MaterialTheme.typography.subtitle1,
                //fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )


        }
    }
}

