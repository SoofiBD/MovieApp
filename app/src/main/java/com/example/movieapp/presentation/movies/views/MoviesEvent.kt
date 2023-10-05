package com.example.movieapp.presentation.movies.views

sealed class MoviesEvent {
    data class Search(val searchString: String) : MoviesEvent()
}