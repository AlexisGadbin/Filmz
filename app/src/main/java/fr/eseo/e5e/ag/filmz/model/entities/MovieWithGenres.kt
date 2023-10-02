package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded var movie : Movie,
    @Relation(
        entity = Genre::class,
        entityColumn = "id_genre",
        parentColumn = "ref_genre"
    ) var genres : List<Genre>
)

