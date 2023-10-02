package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class Country(
    @PrimaryKey() @ColumnInfo(name="code_country") var code : String,
    var name : String,
    var nationality : String
)
