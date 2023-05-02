package com.example.jsonplaceholder.ui.posts.postComments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.comments.CommentsModelItemModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCommentsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    //Mutable State Flow to store the data
    private val _postComments = MutableStateFlow(CommentsModel())
    val postComments: StateFlow<CommentsModel> = _postComments

    fun getPostComments(postId: String) {
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getPostComments(postId)
            _postComments.value = result
        }
    }
}