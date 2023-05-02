package com.example.jsonplaceholder.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    //Mutable State Flow to store the data
    private val _todos= MutableStateFlow(TodosModel())
    val todos: StateFlow<TodosModel> =_todos

    //Initialising the API call request to fetch the data
    init {
        getTodos()
    }

    fun getTodos(){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getTodos()
            _todos.value = result
        }
    }
}