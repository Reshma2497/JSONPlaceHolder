package com.example.jsonplaceholder.ui.users.userTodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTodosViewModel  @Inject constructor(
    val repository: Repository
) : ViewModel() {

    //Mutable State Flow to store the data
    private val _userTodos = MutableStateFlow(TodosModel())
    val userTodos: StateFlow<TodosModel> = _userTodos

    fun getUserTodos(userId: String) {
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getUserTodos(userId)
            _userTodos.value = result
        }
    }
}