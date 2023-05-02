package com.example.jsonplaceholder.ui.users.userTodos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jsonplaceholder.ui.album.ListAlbums
import com.example.jsonplaceholder.ui.navigation.Screen
import com.example.jsonplaceholder.ui.todos.ListTodos
import com.example.jsonplaceholder.ui.users.userAlbums.UserAlbumsViewModel

@Composable
fun UserTodos(userId: String, navController: NavHostController, viewModel: UserTodosViewModel = hiltViewModel())
{
    viewModel.getUserTodos(userId)
    val userTodos by viewModel.userTodos.collectAsState()
    //Navigation
     ListTodos(filteredTodosList = userTodos)

}