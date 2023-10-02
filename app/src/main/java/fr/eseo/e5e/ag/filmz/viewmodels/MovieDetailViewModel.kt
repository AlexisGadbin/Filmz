package fr.eseo.e5e.ag.filmz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.eseo.e5e.ag.filmz.model.entities.Artist
import fr.eseo.e5e.ag.filmz.model.entities.Country
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres
import fr.eseo.e5e.ag.filmz.model.entities.RoleWithArtists
import fr.eseo.e5e.ag.filmz.model.repositories.MovieDetailsRepository

class MovieDetailViewModel(private val movieDetailsRepository: MovieDetailsRepository) :
    ViewModel() {

  fun getMovieFromId(idMovie: Int): LiveData<MovieWithGenres> {
    return movieDetailsRepository.movie(idMovie)
  }

  fun getRolesFromId(idRole: Int): LiveData<List<RoleWithArtists>> {
    return movieDetailsRepository.roles(idRole)
  }

  fun getDirector(idDirector: Int): LiveData<Artist> {
    return movieDetailsRepository.director(idDirector)
  }

  fun getCountry(codeCountry: String): LiveData<Country> {
    return movieDetailsRepository.country(codeCountry)
  }
}
