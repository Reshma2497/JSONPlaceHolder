package com.example.jsonplaceholder.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholder.data.model.errorhandling.ResultOf
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    val repository: Repository
):ViewModel(){
    val posts= MutableLiveData< ResultOf<PostsModel>>()

    fun getPosts()
    {
        viewModelScope.launch {
            try {
                val result=repository.getPosts()
                posts.postValue(ResultOf.Success(result))
            }catch (ioe: IOException)
            {
                posts.postValue(ResultOf.Failure("[IO] error please retry",ioe))
            }catch (he: HttpException)
            {
                posts.postValue(ResultOf.Failure("[HTTP] error please retry",he))
            }
        }
    }
}