package com.programmer2704.movapp.data.remote

import com.programmer2704.movapp.constant.Constants

object ApiFactory {

    val movieApi: MovieApi = RetrofitFactory.retrofit(Constants.MOVIEDB_BASE_URL).create(MovieApi::class.java)
}