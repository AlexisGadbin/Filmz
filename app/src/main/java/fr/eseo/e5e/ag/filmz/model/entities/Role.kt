package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "Roles",
    primaryKeys = ["ref_movie","ref_actor","role_title"],
    foreignKeys = [
        ForeignKey(entity = Artist::class,
            childColumns = ["ref_actor"],
            parentColumns = ["id_artist"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Movie::class,
            childColumns = ["ref_movie"],
            parentColumns = ["id_movie"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value=["ref_actor"]), Index(value=["ref_movie"])]
)
data class Role(
    @ColumnInfo(name="ref_movie") var refMovie : Int,
    @ColumnInfo(name="ref_actor") var refActor : Int,
    @ColumnInfo(name="role_title") var roleTitle : String,

    )

