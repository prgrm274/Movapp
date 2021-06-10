package com.programmer2704.movapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.programmer2704.movapp.data.local.MovieDB
import com.programmer2704.movapp.data.local.MovieEntity
import com.programmer2704.movapp.data.repositories.MovieDBRepo
import kotlinx.coroutines.launch

class FavViewmodel(application: Application) : AndroidViewModel(application) {

    private val repo: MovieDBRepo
    val favoriteMoviesList: LiveData<List<MovieEntity>>

    init {
        val moviesDao = MovieDB.getDatabase(application).movieDao()
        repo = MovieDBRepo(moviesDao)
        favoriteMoviesList = repo.favoriteMovies
    }

    fun insert(movie: MovieEntity) = viewModelScope.launch {
        repo.insert(movie)
    }

    fun delete(movie: MovieEntity) = viewModelScope.launch {
        repo.delete(movie)
    }
}