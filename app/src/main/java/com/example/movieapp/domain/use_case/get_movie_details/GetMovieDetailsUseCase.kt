package com.example.movieapp.domain.use_case.get_movie_details

import com.example.movieapp.data.remote.dto.toMovieDetail
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject


class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovieDetails(imdbID: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetails(imdbID = imdbID).toMovieDetail()
            emit(Resource.Success(movieDetail))

        }catch (e: IOError){
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        } catch (e : retrofit2.HttpException){
            emit(Resource.Error(message = "An unexpected error occurred"))
        }
    }

}