package com.example.jsonplaceholder.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.errorhandling.ResultOf
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.model.photos.PhotosModelItemModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    //Mutable State Flow to store the data
    private val _photoList = MutableStateFlow(PhotosModel())
    val photoList: StateFlow<PhotosModel> =_photoList

    //Initialising the API call request to fetch the data
    init {
            getPhoto()
    }

    fun getPhoto(){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getPhotos()
            _photoList.value = result
        }
    }
}