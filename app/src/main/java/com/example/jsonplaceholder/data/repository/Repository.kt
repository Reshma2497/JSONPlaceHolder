package com.example.jsonplaceholder.data.repository

import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.model.users.UsersModel
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import com.example.jsonplaceholder.data.remote.ApiDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface Repository {

    suspend fun getPosts(): PostsModel

    suspend fun getAlbums(): AlbumsModel

    suspend fun getPhotos(): PhotosModel

    suspend fun getUsers(): UsersModel

    suspend fun getUserById(userId: String): UsersModelItemModel

    suspend fun getComments(): CommentsModel

    suspend fun getTodos(): TodosModel

    suspend fun getPostComments(postId: String): CommentsModel

    suspend fun getAlbumPhotos(albumId: String): PhotosModel

    suspend fun getUserAlbums(userId: String): AlbumsModel

    suspend fun getUserTodos(userId: String): TodosModel

    suspend fun getUserPosts(userId: String): PostsModel


}