package com.example.movieapp.domain.repository


import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.data.remote.dto.moviesDTO
import com.example.movieapp.data.remote.dto.movieDetailDTO
interface MovieRepository {

    suspend fun getMovies(search: String): moviesDTO
    suspend fun getMovieDetails(imdbID: String): movieDetailDTO

}