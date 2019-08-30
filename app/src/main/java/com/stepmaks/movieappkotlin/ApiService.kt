package com.stepmaks.movieappkotlin

import com.stepmaks.movieappkotlin.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMoviesList(@Query("api_key") apiKey: String): Response<MovieResponse>
}
