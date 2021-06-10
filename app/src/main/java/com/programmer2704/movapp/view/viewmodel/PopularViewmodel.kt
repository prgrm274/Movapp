package com.programmer2704.movapp.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.programmer2704.movapp.data.local.MovieEntity
import com.programmer2704.movapp.data.remote.ApiFactory
import com.programmer2704.movapp.data.repositories.MovieRemoteRepo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*

class PopularViewmodel @ViewModelScoped/*ViewModelInject*/
//constructor(private val movieRemoteRepo: MovieRemoteRepo)//NG V PAS ERROR INFLATE POPULAR FRAGMENT, ISENG KOMEN INI LANGSUNG BISA
constructor()
    : BaseViewmodel() {
    private val movieRemoteRepo : MovieRemoteRepo = MovieRemoteRepo(ApiFactory.movieApi)
    val popularMoviesLiveData = MutableLiveData<MutableList<MovieEntity>>()

    fun fetchMovies(page: Int){
        scope.async {
            val popularMovies = movieRemoteRepo.getPopularMovies(page)
            popularMoviesLiveData.postValue(popularMovies!!)
        }
    }
}
/*
class MainViewmodel : BaseViewmodel() {

    private val remoteRepo : MovieRemoteRepo = MovieRemoteRepo(ApiFactory.movieApi)

    val popularMoviesLiveData = MutableLiveData<MutableList<MovieEntity>>()

    fun fetchMovies(page: Int){
        scope.async {
            val popularMovies = remoteRepo.getPopularMovies(page)
            popularMoviesLiveData.postValue(popularMovies)
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text
}*/
