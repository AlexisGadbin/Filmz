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
import fr.eseo.e5e.ag.filmz.model.entities.Role
import fr.eseo.e5e.ag.filmz.model.entities.RoleWithArtists

@Dao
interface RoleDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(role: Role)

  @Update fun update(role: Role)

  @Upsert fun upsert(role: Role)

  @Delete fun delete(role: Role)

  @Transaction
  @Query(
      "SELECT * FROM Roles, Artists WHERE Roles.ref_actor = Artists.id_artist AND ref_movie = :idMovie")
  fun selectAllWithArtistByMovie(idMovie: Int): LiveData<List<RoleWithArtists>>
}
