package com.cyberqhatu.attackontitan.data.model

data class Character(
    val id: Int,
    val name: String,
    val img: String,
    val alia: List<String>,
    val species: List<String>,
    val gender: String,
    val age: Int,
    val height: String,
    val relatives: List<Relative>,
    val birthplace: String,
    val residence: String,
    val status: String,
    val occupation: String,
    val groups: List<Group>,
    val roles: List<String>,
    val episodes: List<String>
)

data class Group(
    val name: String,
    val subGroups: List<String>
)

data class Relative(
    val family: String,
    val members: List<String>
)
