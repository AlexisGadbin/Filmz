package fr.eseo.e5e.ag.filmz.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RoleWithArtists(
    @Embedded var role : Role,
    @Relation(
        entity = Artist::class,
        entityColumn = "id_artist",
        parentColumn = "ref_actor"
    ) var artists : List<Artist>
)

