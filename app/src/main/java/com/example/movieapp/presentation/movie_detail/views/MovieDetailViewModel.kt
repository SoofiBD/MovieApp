package com.example.movieapp.presentation.movie_detail.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.use_case.get_movie_details.GetMovieDetailsUseCase
import com.example.movieapp.util.Constants.IMDB_ID
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let { imdbId ->
            getMovieDetails(imdbId)
        }
    }

    private fun getMovieDetails(imdbId: String){
        getMovieDetailsUseCase.executeGetMovieDetails(imdbID = imdbId).onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = MovieDetailState(movieDetail = it.data)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}