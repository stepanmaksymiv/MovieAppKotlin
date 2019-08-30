package com.stepmaks.movieappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.stepmaks.movieappkotlin.model.IMAGE_URL
import com.stepmaks.movieappkotlin.model.KEY_LIST_DETAIL
import com.stepmaks.movieappkotlin.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(KEY_LIST_DETAIL)
        title_text.text = movie?.title
        description.text = movie?.overview
        Glide.with(this).load(IMAGE_URL + movie?.poster_path).into(monsterImage)
        ratingBar.rating = movie?.vote_average!!.toFloat()
        title = movie.title
    }

}
