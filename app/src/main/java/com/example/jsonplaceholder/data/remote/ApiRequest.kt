package com.example.jsonplaceholder.data.remote

import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.model.users.UsersModel
import com.example.jsonplaceholder.data.model.users.UsersModelItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {

    @GET(ApiDetails.POSTS)
    suspend fun getPosts(): PostsModel

    @GET(ApiDetails.ALBUMS)
    suspend fun getAlbums(): AlbumsModel

    @GET(ApiDetails.PHOTOS)
    suspend fun  getPhotos(): PhotosModel

    @GET(ApiDetails.TODOS)
    suspend fun getTodos(): TodosModel

    @GET(ApiDetails.USERS)
    suspend fun getUsers(): UsersModel

    @GET(ApiDetails.USER_DETAILS)
    suspend fun getUserById(@Path("userId") userId: String): UsersModelItemModel

    @GET(ApiDetails.COMMENTS)
    suspend fun getComments(): CommentsModel

    @GET(ApiDetails.POSTS_COMMENTS)
    suspend fun getPostsComments(@Path("postId") postId: String): CommentsModel

    @GET(ApiDetails.ALBUM_PHOTOS)
    suspend fun getAlbumPhotos(@Path("albumId") albumId: String): PhotosModel

    @GET(ApiDetails.USER_ALBUMS)
    suspend fun getUserAlbums(@Path("userId") userId: String): AlbumsModel

    @GET(ApiDetails.USER_TODOS)
    suspend fun getUserTodos(@Path("userId") userId: String): TodosModel

    @GET(ApiDetails.USER_POSTS)
    suspend fun getUserPosts(@Path("userId") userId: String): PostsModel




}