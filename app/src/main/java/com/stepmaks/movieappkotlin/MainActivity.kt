package com.stepmaks.movieappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stepmaks.movieappkotlin.model.KEY_LIST_DETAIL
import com.stepmaks.movieappkotlin.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieAdapter.Listener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.movieData.observe(this, Observer {
            val adapter = MovieAdapter(this, it, this)
            recycler_view.adapter = adapter
        })
    }

    override fun onItemClick(list: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(KEY_LIST_DETAIL, list)
        startActivity(intent)
    }
}
