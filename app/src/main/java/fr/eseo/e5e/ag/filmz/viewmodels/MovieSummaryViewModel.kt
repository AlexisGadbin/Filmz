package fr.eseo.e5e.ag.filmz.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.e5e.ag.filmz.model.entities.Movie
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres
import fr.eseo.e5e.ag.filmz.model.repositories.MovieSummaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieSummaryViewModel(private val movieSummaryRepository: MovieSummaryRepository) :
    ViewModel() {

  val movies: LiveData<List<MovieWithGenres>>

  init {
    movies = movieSummaryRepository.movies
    Log.d("MOVIE", movies.value.toString())
  }

  fun delete(movie: Movie) =
      viewModelScope.launch(Dispatchers.IO) { movieSummaryRepository.deleteMovie(movie) }

  fun insert(movie: Movie) =
      viewModelScope.launch(Dispatchers.IO) { movieSummaryRepository.insertMovie(movie) }
}
