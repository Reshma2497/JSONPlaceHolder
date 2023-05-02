package com.example.jsonplaceholder.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.model.users.UsersModel
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    //Mutable State Flow to store the data
    private val _users= MutableStateFlow(UsersModel())
    val users: StateFlow<UsersModel> =_users



    //Initialising the API call request to fetch the data
    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getUsers()
            _users.value = result
        }
    }

}