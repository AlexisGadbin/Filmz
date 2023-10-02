package fr.eseo.e5e.ag.filmz.model.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.eseo.e5e.ag.filmz.model.daos.MovieDao
import fr.eseo.e5e.ag.filmz.model.entities.Movie
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres

class MovieSummaryRepository(private val movieDao: MovieDao) {
  val movies: LiveData<List<MovieWithGenres>> = movieDao.selectAllWithGenre()

  @Suppress("RedundantSuspendModifier")
  @WorkerThread
  suspend fun deleteMovie(movie: Movie) {
    movieDao.delete(movie)
  }

  @Suppress("RedundantSuspendModifier")
  @WorkerThread
  suspend fun insertMovie(movie: Movie) {
    movieDao.insert(movie)
  }
}
