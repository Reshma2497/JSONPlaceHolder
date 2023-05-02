package com.example.jsonplaceholder.data.repository

import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.model.users.UsersModel
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import com.example.jsonplaceholder.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository{
    override suspend fun getPosts(): PostsModel =apiRequest.getPosts()

    override suspend fun getAlbums(): AlbumsModel =apiRequest.getAlbums()

    override suspend fun getPhotos(): PhotosModel =apiRequest.getPhotos()

    override suspend fun getUsers(): UsersModel =apiRequest.getUsers()

    override suspend fun getUserById(userId: String)=apiRequest.getUserById(userId)

    override suspend fun getComments(): CommentsModel =apiRequest.getComments()

    override suspend fun getTodos(): TodosModel =apiRequest.getTodos()

    override suspend fun getPostComments(postId: String): CommentsModel=apiRequest.getPostsComments(postId)

    override suspend fun getAlbumPhotos(albumId: String): PhotosModel=apiRequest.getAlbumPhotos(albumId)

    override suspend fun getUserAlbums(userId: String): AlbumsModel=apiRequest.getUserAlbums(userId)

    override suspend fun getUserTodos(userId: String): TodosModel=apiRequest.getUserTodos(userId)

    override suspend fun getUserPosts(userId: String): PostsModel=apiRequest.getUserPosts(userId)


}