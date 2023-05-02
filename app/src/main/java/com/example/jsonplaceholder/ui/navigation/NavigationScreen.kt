package com.example.jsonplaceholder.ui.navigation

import com.example.jsonplaceholder.R


enum class Screen(var route: String, var icon: Int, var title: String, var mainTab:Boolean) {

    LoginScreen("login", R.drawable.ic_login, "Login",false),
    SignUpScreen("signUp", R.drawable.ic_login, "SignUp", false),
    Posts("posts", R.drawable.ic_post, "Posts",true),
    Photos("photos", R.drawable.ic_image, "Photos",true),
    Users("users", R.drawable.ic_users, "Users",true),
    UserDetails("userDetail",R.drawable.ic_album,"User Details",false),
    Comments("comments", R.drawable.ic_comment, "Comments",true),
    Albums("albums", R.drawable.ic_album, "Albums",true),
    Todos("todos", R.drawable.ic_todos, "Todos",true),
    PostComments("postComments",R.drawable.ic_comment,"Post Comments",false),
    AlbumPhotos("albumPhotos",R.drawable.ic_image,"Album Photos",false),
    UserAlbums("userAlbums",R.drawable.ic_album,"User Albums",false),
    UserTodos("userTodos",R.drawable.ic_todos,"User Todos",false),
    UserPosts("userPosts",R.drawable.ic_post,"User Posts",false),
    LogOut("logOut",R.drawable.ic_signout,"LogOut",true)

}
