package com.example.jsonplaceholder.ui.album.albumPhotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumPhotosViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    //Mutable State Flow to store the data
    private val _albumPhotos = MutableStateFlow(PhotosModel())
    val albumPhotos: StateFlow<PhotosModel> = _albumPhotos

    fun getAlbumPhotos(albumId: String) {
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getAlbumPhotos(albumId)
            _albumPhotos.value = result
        }
    }
}