package com.example.jsonplaceholder.ui.posts.postComments

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jsonplaceholder.ui.comments.ListComments

@Composable
fun PostComments(postId: String, viewModel: PostCommentsViewModel = hiltViewModel())
{
    viewModel.getPostComments(postId)
    val postComments by viewModel.postComments.collectAsState()

    ListComments(postComments)

}
