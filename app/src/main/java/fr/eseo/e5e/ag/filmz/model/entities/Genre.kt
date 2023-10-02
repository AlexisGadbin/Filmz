package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genres")
data class Genre (
    @PrimaryKey() @ColumnInfo(name="id_genre") var idGenre : Int,
    var title : String,
    var description : String
)
