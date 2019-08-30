package com.stepmaks.movieappkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stepmaks.movieappkotlin.model.IMAGE_URL
import com.stepmaks.movieappkotlin.model.Movie
import kotlinx.android.synthetic.main.grid_item.view.*

class MovieAdapter(val context: Context, val movies: List<Movie>, val listener: Listener): RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listMovies = movies[position]

        val image = IMAGE_URL + listMovies.poster_path
        Glide.with(context).load(image).into(holder.poster)

        holder.itemView.setOnClickListener {
            listener.onItemClick(listMovies)
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val poster = itemView.movieImage
    }

    interface Listener {
        fun onItemClick(list: Movie)
    }
}