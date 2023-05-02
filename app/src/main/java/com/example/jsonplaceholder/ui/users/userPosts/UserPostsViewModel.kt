package com.example.jsonplaceholder.ui.users.userPosts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    //Mutable State Flow to store the data
    private val _userPosts = MutableStateFlow(PostsModel())
    val userPosts: StateFlow<PostsModel> = _userPosts

    fun getUserPosts(userId: String) {
        viewModelScope.launch {
            // call the repository to fetch the list of photos
            val result = repository.getUserPosts(userId)
            _userPosts.value = result
        }
    }
}