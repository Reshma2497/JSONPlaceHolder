package com.example.jsonplaceholder.errorhandling

import com.example.jsonplaceholder.data.model.errorhandling.ResultOf

object ErrorHandling {
    inline fun <reified T> ResultOf<T>.doIfFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
        if (this is ResultOf.Failure) {
            callback(message, throwable)
        }
    }

    inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
        if (this is ResultOf.Success) {
            callback(value)
        }
    }

    inline fun <reified T> ResultOf<T>.doIfLoading(callback: (isLoading: T) -> Unit) {
        if(this is ResultOf.Loading){
            callback(isLoading)
        }
    }
}