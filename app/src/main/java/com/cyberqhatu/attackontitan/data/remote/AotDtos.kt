package com.cyberqhatu.attackontitan.data.remote

import com.google.gson.annotations.SerializedName

// GET /characters?page=1
data class PersonajeDto(
    val id: Int,
    val name: String?,
    val img: String?,
    val alias: List<String>?,
    val species: List<String>?,
    val gender: String?,
    val age: Any?, // viene como número (19) o como texto ("unknown")
    val height: String?,
    val relatives: List<FamiliaDto>?,
    val birthplace: String?,
    val residence: String?,
    val status: String?,
    val occupation: String?,
    val groups: List<GrupoDto>?,
    val roles: List<String>?,
    val episodes: List<String>? // links: ".../episodes/12"
)

data class FamiliaDto(
    val family: String?,
    val members: List<String>?
)

data class GrupoDto(
    val name: String?,
    @SerializedName("sub_groups") val subGroups: List<String>?
)

// GET /titans
data class TitanDto(
    val id: Int,
    val name: String?,
    val img: String?,
    val height: String?,
    val abilities: List<String>?,
    @SerializedName("current_inheritor") val currentInheritor: String?, // link: ".../characters/188"
    @SerializedName("former_inheritors") val formerInheritors: List<String>?,
    val allegiance: String?
)

// GET /episodes?page=1
data class EpisodioDto(
    val id: Int,
    val name: String?,
    val img: String?,
    val episode: String?,
    val characters: List<String>? // links a /characters/ID o /titans/ID
)