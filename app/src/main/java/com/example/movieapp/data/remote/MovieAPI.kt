package com.example.movieapp.data.remote

import com.example.movieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {


    @GET(".")
    suspend fun getMovies(
        @retrofit2.http.Query("s") searchQuery: String,
        @retrofit2.http.Query("apikey") apiKey: String = API_KEY
    ): com.example.movieapp.data.remote.dto.moviesDTO

    @GET(".")
    suspend fun getMovieDetails(
        @retrofit2.http.Query("i") imdbID: String,
        @Query("apikey") apiKey: String = API_KEY
    ): com.example.movieapp.data.remote.dto.movieDetailDTO
}