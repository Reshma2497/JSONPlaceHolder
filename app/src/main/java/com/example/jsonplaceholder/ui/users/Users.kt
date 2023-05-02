package com.example.jsonplaceholder.ui.users

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import com.example.jsonplaceholder.ui.components.SearchBar
import com.example.jsonplaceholder.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Users(navController: NavHostController, viewModel: UsersViewModel = hiltViewModel()) {
    val users by viewModel.users.collectAsState()


    //   navigation to User Details
    val navigateToUserDetails: (String) -> Unit = { userId ->
        navController.navigate("${Screen.UserDetails.route}/$userId")
    }


    val searchQuery = remember { mutableStateOf("") }
    val filteredUsersList = if (searchQuery.value.isBlank()) {
        users
    } else {

        users.filter { users ->
            users.name!!.contains(searchQuery.value, ignoreCase = true)||
                    users.address?.city!!.contains(searchQuery.value, ignoreCase = true)
        }
    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By User Name or City")

            LazyColumn(
                modifier = Modifier

            ) {
                itemsIndexed(filteredUsersList) { _, user ->
                    UserItem(user = user, onNextClick =navigateToUserDetails )

                }

            }


        }
    }
}


@Composable
fun UserItem(user: UsersModelItemModel, onNextClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                onNextClick(user.id.toString())
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = "https://picsum.photos/id/${user.id}/200"),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = user.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.email ?: "",
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user.address?.city ?: "",
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}