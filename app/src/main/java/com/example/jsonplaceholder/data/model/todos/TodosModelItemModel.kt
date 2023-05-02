package com.example.jsonplaceholder.data.model.todos


import com.google.gson.annotations.SerializedName

data class TodosModelItemModel(
    @SerializedName("completed")
    val completed: Boolean? = false,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
)