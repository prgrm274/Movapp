package com.programmer2704.movapp.data.remote

import com.programmer2704.movapp.domain.Movie
import com.programmer2704.movapp.data.local.MovieEntityResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovieAsync(@Query("page") page: Int): Deferred<Response<MovieEntityResponse>>

    @GET("movie/upcoming")
    fun getUpcomingMovieAsync(@Query("page") page: Int): Deferred<Response<MovieEntityResponse>>

    @GET("movie/{movie_id}")
    fun getMovieDetailsAsync(@Path("movie_id") id: Int): Deferred<Response<Movie>>
}