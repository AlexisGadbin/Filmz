package fr.eseo.e5e.ag.filmz.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import fr.eseo.e5e.ag.filmz.model.entities.Country

@Dao
interface CountryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(country: Country)

  @Update fun update(country: Country)

  @Upsert fun upsert(country: Country)

  @Delete fun delete(country: Country)

  @Query("SELECT * FROM Countries") fun selectAll(): LiveData<List<Country>>

  @Query("SELECT * FROM Countries WHERE code_country = :codeCountry")
  fun selectByCode(codeCountry: String): LiveData<Country>
}
