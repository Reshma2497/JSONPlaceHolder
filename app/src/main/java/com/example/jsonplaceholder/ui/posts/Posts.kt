package com.example.jsonplaceholder.ui.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.data.model.posts.PostsModelItemModel
import com.example.jsonplaceholder.errorhandling.ErrorHandling.doIfSuccess
import com.example.jsonplaceholder.ui.components.SearchBar
import com.example.jsonplaceholder.ui.navigation.Screen
import kotlin.random.Random


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Posts(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()) {

    val posts = remember { mutableStateListOf<PostsModelItemModel>() }
    //Navigation
    val navigateToPostComments: (String) -> Unit = { postId ->
        navController.navigate("${Screen.PostComments.route}/$postId")
    }

    // Filtered product list based on search query
    val searchQuery = remember { mutableStateOf("") }
    val filteredPostsList = if (searchQuery.value.isBlank()) {
        posts
    } else {
        posts.filter { posts ->
            posts.title!!.contains(searchQuery.value, ignoreCase = true )||
                    posts.userId.toString().equals(searchQuery.value, ignoreCase = true
            )
        }
    }

    //view Model get posts
    viewModel.getPosts()

    //Observer the changes for view model
    viewModel.posts.observeAsState().value?.doIfSuccess {
        posts.apply {
            clear()
            addAll(it)
        }

    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By Post Title or User Id")

          ListPosts(filteredPostsList = filteredPostsList, onNextClick =navigateToPostComments )


        }
    }

}
@Composable
fun ListPosts(filteredPostsList:List<PostsModelItemModel>, onNextClick: (String) -> Unit)
{
    LazyColumn(
        modifier = Modifier
            .padding(top = 10.dp)

    ) {
        itemsIndexed(filteredPostsList) { _, post ->
            //AddPosts(posts, onNextClick)
            PostItem(post = post, onNextClick =onNextClick )
            Divider(color = Color.LightGray, thickness = 1.dp)
        }

    }
}



@Composable
fun PostItem(post: PostsModelItemModel, onNextClick: (String) -> Unit) {
    Column(modifier = Modifier
        .padding(16.dp)
        .clickable {
            onNextClick(post.id.toString())
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = "https://picsum.photos/id/${post.userId}/200"),
                contentDescription = null,
                modifier = Modifier.size(48.dp) .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "User ${post.userId}", fontWeight = FontWeight.Bold)
                Text(text = post?.title!!, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f), fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = post.body!!)
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        val icComment = ImageVector.vectorResource(id = R.drawable.ic_comment)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            PostAction(icon = Icons.Default.ThumbUp, text ="100")
            PostAction(icon = icComment, text =  "100")
            PostAction(icon = Icons.Default.Share,  "100")
        }
    }
}



@Composable
fun PostAction(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}



