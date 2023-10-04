package fr.eseo.e5e.ag.filmz

import android.app.Application
import fr.eseo.e5e.ag.filmz.model.FilmzDB
import fr.eseo.e5e.ag.filmz.model.repositories.MovieDetailsRepository
import fr.eseo.e5e.ag.filmz.model.repositories.MovieSummaryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FilmzApp : Application() {
  val applicationScope = CoroutineScope(SupervisorJob())

  val database by lazy { FilmzDB.instance(this, applicationScope) }

  val summaryRepository by lazy { MovieSummaryRepository(database.movies()) }
  val detailsRepository by lazy {
    MovieDetailsRepository(
        database.movies(), database.roles(), database.artists(), database.countries())
  }
}
