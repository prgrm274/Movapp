package com.programmer2704.movapp.constant

import com.programmer2704.movapp.BuildConfig

object Constants {
    const val MOVIE_PHOTO_URL: String = "https://image.tmdb.org/t/p/w300"
    const val MOVIEDB_BASE_URL: String = "https://api.themoviedb.org/3/"
//    const val API_KEY: String = "52c619fa463c582dcb617842d6e252cc"
    const val API_KEY: String = BuildConfig.API_KEY
}