package com.cyberqhatu.attackontitan.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val img: String? = null,
    @SerializedName("alias") val alia: List<String>? = emptyList(),
    val species: List<String>? = emptyList(),
    val gender: String? = null,
    val age: Int? = null,
    val height: String? = null,
    val relatives: List<Relative>? = emptyList(),
    val birthplace: String? = null,
    val residence: String? = null,
    val status: String? = null,
    val occupation: String? = null,
    val groups: List<Group>? = emptyList(),
    val roles: List<String>? = emptyList(),
    val episodes: List<String>? = emptyList()
)

data class Group(
    val name: String,
    @SerializedName("sub_groups") val subGroups: List<String>? = emptyList()
)

data class Relative(
    val family: String,
    val members: List<String>? = emptyList()
)
