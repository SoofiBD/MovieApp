package com.example.movieapp.util

import com.example.movieapp.domain.model.MovieDetail

sealed class Resource<T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: MovieDetail) : Resource<T>(data = data)
    class Error<T>(message: String, data:T? = null) : Resource<T>(data = data,message=message)
    class Loading<T>(data:T?= null) : Resource<T>(data=data)
}