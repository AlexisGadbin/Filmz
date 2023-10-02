package fr.eseo.e5e.ag.filmz.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import fr.eseo.e5e.ag.filmz.model.entities.Movie
import fr.eseo.e5e.ag.filmz.model.entities.MovieWithGenres

@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(movie: Movie)

  @Update fun update(movie: Movie)

  @Upsert fun upsert(movie: Movie)

  @Delete fun delete(movie: Movie)

  @Transaction
  @Query("SELECT * FROM Movies, Genres WHERE Movies.ref_genre = Genres.id_genre")
  fun selectAllWithGenre(): LiveData<List<MovieWithGenres>>

  @Transaction
  @Query(
      "SELECT * FROM Movies, Genres WHERE Movies.ref_genre = Genres.id_genre AND id_movie = :idMovie")
  fun selectByIdWithGenre(idMovie: Int): LiveData<MovieWithGenres>
}
