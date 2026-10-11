package com.cyberqhatu.attackontitan.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val img: String,
    @SerializedName("alias") val alia: List<String>,
    val species: List<String>,
    val gender: String?,
    val age: Int?,
    val height: String?,
    val relatives: List<Relative>,
    val birthplace: String?,
    val residence: String?,
    val status: String?,
    val occupation: String?,
    val groups: List<Group>,
    val roles: List<String>,
    val episodes: List<String>
)

data class Group(
    val name: String,
    @SerializedName("sub_groups") val subGroups: List<String>
)

data class Relative(
    val family: String,
    val members: List<String>
)
