package com.example.jsonplaceholder.ui.users.userDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.jsonplaceholder.ui.navigation.Screen
import com.example.jsonplaceholder.ui.theme.SkyBlue


@Composable
fun UserDetails(userId:String,navController: NavHostController, viewModel: UserDetailsViewModel = hiltViewModel())
{
    //navigation to User Albums
    val navigateToUserAlbums: (String) -> Unit = { userId ->
        navController.navigate("${Screen.UserAlbums.route}/$userId")
    }

    val navigateToUserTodos: (String) -> Unit = { userId ->
        navController.navigate("${Screen.UserTodos.route}/$userId")
    }

    val navigateToUserPosts: (String) -> Unit = { userId ->
        navController.navigate("${Screen.UserPosts.route}/$userId")
    }



    viewModel.getUserById(userId)
    val user by viewModel.user.collectAsState()
    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Name: ${user.name}", fontWeight = FontWeight.Bold)
                Text(text = "Username: ${user.username}")
                Text(text = "Email: ${user.email}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Address:", fontWeight = FontWeight.Bold)
                Text(text = "${user.address?.street}, ${user.address?.suite}")
                Text(text = "${user.address?.city}, ${user.address?.zipcode}")
                Text(text = "Lat: ${user.address?.geo?.lat}, Lng: ${user.address?.geo?.lng}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Phone: ${user.phone}")
                Text(text = "Website: ${user.website}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Company:", fontWeight = FontWeight.Bold)
                Text(text = "${user.company?.name}")
                Text(text = "${user.company?.catchPhrase}")
                Text(text = "${user.company?.bs}")
            }
        }

        UserButtons(userId,navigateToUserAlbums,navigateToUserTodos,navigateToUserPosts)

    }

}
@Composable
fun UserButtons(userId:String,onAlbumsClick: (String) -> Unit, onTodosClick: (String) -> Unit, onPostsClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { onAlbumsClick(userId)},
            colors = ButtonDefaults.buttonColors(backgroundColor = SkyBlue),
            modifier = Modifier.widthIn(min = 100.dp)
        ) {
            Text(
                text = "User Albums",
                color = Color.White
            )
        }
        Button(
            onClick = { onTodosClick(userId) },
            colors = ButtonDefaults.buttonColors(backgroundColor = SkyBlue),
            modifier = Modifier.widthIn(min = 100.dp)
        ) {
            Text(
                text = "User Todos",
                color = Color.White
            )
        }
        Button(
            onClick = { onPostsClick(userId) },
            colors = ButtonDefaults.buttonColors(backgroundColor = SkyBlue),
            modifier = Modifier.widthIn(min = 100.dp)
        ) {
            Text(
                text = "User Posts",
                color = Color.White
            )
        }
    }
}



@Composable
fun MapViewExample(latitude: Double, longitude: Double) {
    val context = LocalContext.current

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                // Set up the map here
                val location = LatLng(latitude, longitude)
                val cameraPosition = CameraPosition.Builder()
                    .target(location)
                    .zoom(15f)
                    .build()

            }
        },
        update = { mapView ->
            // Update the map here, if needed
        }
    )
}
