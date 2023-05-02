package com.example.jsonplaceholder.ui.users.userPosts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jsonplaceholder.ui.navigation.Screen
import com.example.jsonplaceholder.ui.posts.ListPosts
import com.example.jsonplaceholder.ui.todos.ListTodos
import com.example.jsonplaceholder.ui.users.userTodos.UserTodosViewModel

@Composable
fun UserPosts(userId: String, navController: NavHostController, viewModel: UserPostsViewModel = hiltViewModel())
{
    viewModel.getUserPosts(userId)
    val userPosts by viewModel.userPosts.collectAsState()

    val navigateToPostComments: (String) -> Unit = { postId ->
        navController.navigate("${Screen.PostComments.route}/$postId")
    }
    //Navigation
    ListPosts(userPosts,navigateToPostComments)

}