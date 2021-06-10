package com.programmer2704.movapp.data.repositories

import com.programmer2704.movapp.domain.Movie
import com.programmer2704.movapp.data.local.MovieEntity
import com.programmer2704.movapp.data.local.MovieEntityResponse
import com.programmer2704.movapp.data.remote.MovieApi
import javax.inject.Inject

class MovieRemoteRepo @Inject constructor(private val api: MovieApi) : BaseRepo() {

    private var popularEntityResponse: MovieEntityResponse? = null
    private var upcomingEntityResponse: MovieEntityResponse? = null
    private var movieResponse: Movie? = null

    suspend fun getPopularMovies(page: Int): MutableList<MovieEntity>? {
        popularEntityResponse = safeApiCall(
            call = { api.getPopularMovieAsync(page).await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        return popularEntityResponse?.results?.toMutableList();
    }

    suspend fun getUpcomingMovies(page: Int): MutableList<MovieEntity>? {
        upcomingEntityResponse = safeApiCall(
            call = { api.getUpcomingMovieAsync(page).await() },
            errorMessage = "Error Fetching Upcoming Movies"
        )
        return upcomingEntityResponse?.results?.toMutableList();
    }

    suspend fun getMovieDetails(id: Int): Movie? {
        movieResponse = safeApiCall(
            call = { api.getMovieDetailsAsync(id).await() },
            errorMessage = "Error Fetching Movie Details"
        )
        return movieResponse;
    }
}