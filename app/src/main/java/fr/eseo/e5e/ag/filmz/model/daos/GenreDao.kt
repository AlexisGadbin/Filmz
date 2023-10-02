package fr.eseo.e5e.ag.filmz.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import fr.eseo.e5e.ag.filmz.model.entities.Genre

@Dao
interface GenreDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(genre: Genre)

  @Update fun update(genre: Genre)

  @Upsert fun upsert(genre: Genre)

  @Delete fun delete(genre: Genre)

  @Query("SELECT * FROM Genres") fun selectAll(): LiveData<List<Genre>>

  @Query("SELECT * FROM Genres WHERE id_genre = :idGenre")
  fun selectByType(idGenre: Int): LiveData<Genre>
}
