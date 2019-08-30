package com.stepmaks.movieappkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val movieRepository = MovieRepository(application)

    val movieData = movieRepository.movieListData
}