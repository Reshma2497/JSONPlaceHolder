package com.example.jsonplaceholder.data.model.errorhandling

import com.example.jsonplaceholder.R

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ResultOf<Nothing>()
    data class Loading<out R>(val isLoading: R) : ResultOf<R>()
}