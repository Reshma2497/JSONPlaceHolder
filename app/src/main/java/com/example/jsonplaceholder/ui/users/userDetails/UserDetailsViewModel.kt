package com.example.jsonplaceholder.ui.users.userDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel@Inject constructor(
    val repository: Repository): ViewModel(){
    private val _user= MutableStateFlow(UsersModelItemModel())
    val user: StateFlow<UsersModelItemModel> =_user


    fun getUserById(userId:String){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getUserById(userId)
            _user.value = result
        }
    }
}