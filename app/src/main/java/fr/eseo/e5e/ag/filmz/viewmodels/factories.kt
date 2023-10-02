package fr.eseo.e5e.ag.filmz.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.eseo.e5e.ag.filmz.model.repositories.MovieDetailsRepository
import fr.eseo.e5e.ag.filmz.model.repositories.MovieSummaryRepository

class MovieSummaryViewModelFactory(private val repository: MovieSummaryRepository) :
    ViewModelProvider.Factory {

  override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
    if (viewModelClass.isAssignableFrom(MovieSummaryViewModel::class.java)) {
      return MovieSummaryViewModel(repository) as T
    } else {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
  }
}

class MovieDetailViewModelFactory(private val repository: MovieDetailsRepository) :
    ViewModelProvider.Factory {

  override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
    if (viewModelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
      return MovieDetailViewModel(repository) as T
    } else {
      throw IllegalArgumentException("Unknown ViewModel class")
    }
  }
}
