package com.example.movieapp.presentation.movie_detail.views

import com.example.movieapp.domain.model.MovieDetail

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String = ""
)