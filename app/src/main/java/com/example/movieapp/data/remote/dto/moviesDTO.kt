package com.example.movieapp.data.remote.dto

data class moviesDTO(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun moviesDTO.toMovieList() : List<com.example.movieapp.domain.model.Movie>{
    return this.Search.map {
        com.example.movieapp.domain.model.Movie(
            imdbID = it.imdbID,
            title = it.Title,
            year = it.Year,
            poster = it.Poster
        )
    }
}