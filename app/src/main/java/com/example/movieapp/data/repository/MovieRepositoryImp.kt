package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dto.movieDetailDTO
import com.example.movieapp.data.remote.dto.moviesDTO
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val api: MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): moviesDTO {
        return api.getMovies(searchQuery = search)
    }

    override suspend fun getMovieDetails(imdbID: String): movieDetailDTO {
        return api.getMovieDetails(imdbID = imdbID)
    }
}