package com.example.translationapp.utilities

sealed class Resource<T>(val data: T? = null, val message: UiText? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(message: UiText, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Empty<Unit>: Resource<Unit>()
}
