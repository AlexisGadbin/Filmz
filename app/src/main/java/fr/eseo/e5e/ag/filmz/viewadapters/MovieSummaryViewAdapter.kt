package fr.eseo.e5e.ag.filmz.viewadapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.eseo.e5e.ag.filmz.databinding.CardMovieSummaryBinding
import fr.eseo.e5e.ag.filmz.model.entities.Genre
import fr.eseo.e5e.ag.filmz.model.entities.Movie
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres
import fr.eseo.e5e.ag.filmz.views.fragments.MovieSummaryFragment

class MovieSummaryViewAdapter(val frag: MovieSummaryFragment) :
    ListAdapter<MovieWithGenres, MovieSummaryViewAdapter.ViewHolder>(MovieComparator()) {
  class ViewHolder(val binding: CardMovieSummaryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, genre: Genre) {
      Log.d("MOVIE", movie.movie_title)
      Log.d("MOVIE", movie.synopsis)
      Log.d("MOVIE", movie.year.toString())

      binding.summaryMovieTitle.text = movie.movie_title
      binding.summaryMovieGenre.text = genre.title
      binding.summaryMovieYear.text = movie.year.toString()
    }

    companion object {
      fun create(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            CardMovieSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      }
    }
  }

  class MovieComparator : DiffUtil.ItemCallback<MovieWithGenres>() {

    override fun areContentsTheSame(oldItem: MovieWithGenres, newItem: MovieWithGenres): Boolean {
      return oldItem.movie.movie_title == newItem.movie.movie_title
    }

    override fun areItemsTheSame(oldItem: MovieWithGenres, newItem: MovieWithGenres): Boolean {
      return oldItem === newItem
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    Log.d("TABLET", "binding")
    val current = getItem(position)
    Log.d("MOVIE", current.toString())
    holder.bind(current.movie, current.genres.get(0))

    holder.binding.root.setOnClickListener { view -> frag.selectMovie(current.movie.idMovie) }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    Log.d("TABLET", "creating view holder")
    return ViewHolder.create(parent)
  }
}
