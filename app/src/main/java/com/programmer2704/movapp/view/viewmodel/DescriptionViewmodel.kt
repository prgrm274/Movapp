package com.programmer2704.movapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.programmer2704.movapp.domain.Movie
import com.programmer2704.movapp.data.remote.ApiFactory
import com.programmer2704.movapp.data.repositories.MovieRemoteRepo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel

class DescriptionViewmodel @ViewModelScoped constructor()
    : BaseViewmodel() {

    private val movieRemoteRepo : MovieRemoteRepo = MovieRemoteRepo(ApiFactory.movieApi)
    val movieDetails = MutableLiveData<Movie>()

    fun fetchDetails(id: Int){
        scope.async {
            val details = movieRemoteRepo.getMovieDetails(id)
            movieDetails.postValue(details!!)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}