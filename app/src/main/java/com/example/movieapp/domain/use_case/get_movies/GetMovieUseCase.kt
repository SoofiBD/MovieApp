package com.example.movieapp.domain.use_case.get_movies

import com.example.movieapp.data.remote.dto.toMovieList
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
){
    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
       try {
           emit(Resource.Loading())
           val movieList = repository.getMovies(search)
           if (movieList.Response.equals("True")) {
               emit(Resource.Success(movieList.toMovieList()))
           } else {
               emit(Resource.Error(message = "An unexpected error occurred"))
           }

       }catch (e: IOError){
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
       } catch (e : retrofit2.HttpException){
           emit(Resource.Error(message = "An unexpected error occurred"))
       }
    }
    //suspend operator fun invoke(searchQuery: String) = repository.getMovies(searchQuery)
}