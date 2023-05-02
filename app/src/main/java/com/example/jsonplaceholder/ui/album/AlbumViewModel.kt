package com.example.jsonplaceholder.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    //Mutable State Flow to store the data
    private val _albums= MutableStateFlow(AlbumsModel())
    val albums: StateFlow<AlbumsModel> =_albums

    //Initialising the API call request to fetch the data
    init {
        getAlbums()
    }

    fun getAlbums(){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getAlbums()
            _albums.value = result
        }
    }
}