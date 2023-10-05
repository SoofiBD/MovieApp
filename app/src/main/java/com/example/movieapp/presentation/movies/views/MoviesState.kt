package com.example.movieapp.presentation.movies.views

import com.example.movieapp.domain.model.Movie

data class MoviesState (
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "Batman"
)