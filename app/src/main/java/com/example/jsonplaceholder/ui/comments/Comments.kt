package com.example.jsonplaceholder.ui.comments

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.jsonplaceholder.data.model.comments.CommentsModelItemModel
import com.example.jsonplaceholder.ui.components.SearchBar
import kotlin.random.Random


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Comments(navController: NavHostController, viewModel: CommentsViewModel = hiltViewModel()) {
    val comments by viewModel.comments.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val filteredCommentsList = if (searchQuery.value.isBlank()) {
        comments
    } else {
        comments.filter { comments ->
            comments.body!!.contains(searchQuery.value, ignoreCase = true) ||comments.name!!.contains(searchQuery.value, ignoreCase = true)
        }
    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By Comment name or Body")
            ListComments(model = filteredCommentsList)

        }
    }
}

@Composable
fun ListComments(model: List<CommentsModelItemModel>)
{
    LazyColumn(
        modifier = Modifier
            .padding(top = 10.dp)

    ) {
        itemsIndexed(model) { _, comment ->
           // AddComments(comments)
            CommentItem(comment = comment)
            Spacer(modifier = Modifier.height(5.dp))
           // Divider(color = Color.LightGray, thickness = 2.dp)

        }

    }
}



@Composable
fun CommentItem(comment: CommentsModelItemModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://picsum.photos/id/${comment.id}/200"),
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = comment.email.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                val randomNumber = Random.Default.nextInt(24) + 1
                Text(
                    text = "$randomNumber h ago",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = comment.body.toString(),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }}