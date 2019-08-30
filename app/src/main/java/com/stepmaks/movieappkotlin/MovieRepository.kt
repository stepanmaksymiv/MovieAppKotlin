package com.stepmaks.movieappkotlin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.stepmaks.movieappkotlin.data.MovieDatabase
import com.stepmaks.movieappkotlin.model.API_KEY
import com.stepmaks.movieappkotlin.model.BASE_URL
import com.stepmaks.movieappkotlin.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieRepository(val application: Application) {

    private val movieDao = MovieDatabase.getDatabaseMovie(application).movieDao()

    val movieListData = MutableLiveData<List<Movie>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            if (networkAvailable()){
                callDataApi()
            } else{
                val data = movieDao.getAll()
                movieListData.postValue(data)
            }
        }
    }

    @WorkerThread
    suspend fun callDataApi() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getMoviesList(API_KEY).body()?.results ?: emptyList()
            movieListData.postValue(serviceData)
            movieDao.deleteAll()
            movieDao.insertMovies(serviceData)
        }
    }


    @Suppress("DEPRECATION")
    fun networkAvailable(): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}