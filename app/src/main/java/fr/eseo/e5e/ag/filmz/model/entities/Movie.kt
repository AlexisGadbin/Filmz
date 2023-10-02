package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Movies",
    foreignKeys = [
        ForeignKey(
            entity = Genre::class,
            childColumns = ["ref_genre"],
            parentColumns = ["id_genre"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Country::class,
            childColumns = ["ref_country"],
            parentColumns = ["code_country"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Artist::class,
            childColumns = ["ref_director"],
            parentColumns = ["id_artist"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value=["ref_country"]), Index(value=["ref_genre"]), Index(value=["ref_director"])]
)
data class Movie(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id_movie") var idMovie : Int,
    var movie_title : String,
    var year : Int,
    @ColumnInfo(name="ref_director") var refDirecteur : Int,
    @ColumnInfo(name="ref_genre") var refGenre : Int,
    var synopsis : String,
    @ColumnInfo(name="ref_country") var refCountry : String
)
