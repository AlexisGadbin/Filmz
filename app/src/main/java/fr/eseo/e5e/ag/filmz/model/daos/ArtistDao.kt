package fr.eseo.e5e.ag.filmz.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import fr.eseo.e5e.ag.filmz.model.entities.Artist

@Dao
interface ArtistDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(artist: Artist)

  @Update fun update(artist: Artist)

  @Upsert fun upsert(artist: Artist)

  @Delete fun delete(artist: Artist)

  @Query("SELECT * FROM Artists") fun selectAll(): LiveData<List<Artist>>

  @Query("SELECT * FROM Artists WHERE id_artist = :idArtist")
  fun selectById(idArtist: Int): LiveData<Artist>
}
