package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Artists",
    foreignKeys = [
        ForeignKey(entity = Country::class,
            childColumns = ["ref_country"],
            parentColumns = ["code_country"],
            onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value=["ref_country"])])
data class Artist(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id_artist") var idArtist : Int,
    var surname : String,
    var forename : String,
    @ColumnInfo(name="dob") var dateOfBirth : Int,
    @ColumnInfo(name="ref_country") var nationality : String
)
