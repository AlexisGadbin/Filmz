package fr.eseo.e5e.ag.filmz.model.repositories

import androidx.lifecycle.LiveData
import fr.eseo.e5e.ag.filmz.model.daos.ArtistDao
import fr.eseo.e5e.ag.filmz.model.daos.CountryDao
import fr.eseo.e5e.ag.filmz.model.daos.MovieDao
import fr.eseo.e5e.ag.filmz.model.daos.RoleDao
import fr.eseo.e5e.ag.filmz.model.entities.Artist
import fr.eseo.e5e.ag.filmz.model.entities.Country
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres
import fr.eseo.e5e.ag.filmz.model.entities.RoleWithArtists

class MovieDetailsRepository(
    private val movieDao: MovieDao,
    private val roleDao: RoleDao,
    private val artistDao: ArtistDao,
    private val countryDao: CountryDao
) {

  fun movie(idMovie: Int): LiveData<MovieWithGenres> {
    return movieDao.selectByIdWithGenre(idMovie)
  }

  fun roles(idMovie: Int): LiveData<List<RoleWithArtists>> {
    return roleDao.selectAllWithArtistByMovie(idMovie)
  }

  fun director(idDirector: Int): LiveData<Artist> {
    return artistDao.selectById(idDirector)
  }

  fun country(countryCode: String): LiveData<Country> {
    return countryDao.selectByCode(countryCode)
  }
}
