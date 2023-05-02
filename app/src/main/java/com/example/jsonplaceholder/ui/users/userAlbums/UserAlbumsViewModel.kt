package com.example.jsonplaceholder.ui.users.userAlbums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAlbumsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    //Mutable State Flow to store the data
    private val _userAlbums = MutableStateFlow(AlbumsModel())
    val userAlbums: StateFlow<AlbumsModel> = _userAlbums

    fun getUserAlbums(userId: String) {
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getUserAlbums(userId)
            _userAlbums.value = result
        }
    }
}