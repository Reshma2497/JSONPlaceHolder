package com.example.jsonplaceholder.ui.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    //Mutable State Flow to store the data
    private val _comments= MutableStateFlow(CommentsModel())
    val comments: StateFlow<CommentsModel> =_comments

    //Initialising the API call request to fetch the data
    init {
        getComments()
    }

    fun getComments(){
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getComments()
            _comments.value = result
        }
    }
}