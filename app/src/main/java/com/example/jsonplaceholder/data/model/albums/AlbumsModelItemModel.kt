package com.example.jsonplaceholder.data.model.albums


import com.google.gson.annotations.SerializedName

data class AlbumsModelItemModel(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
)