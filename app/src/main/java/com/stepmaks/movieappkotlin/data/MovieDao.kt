package com.stepmaks.movieappkotlin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stepmaks.movieappkotlin.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}