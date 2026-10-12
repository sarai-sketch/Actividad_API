package com.cyberqhatu.attackontitan.data.model

data class Titan(
    val id: Int,
    val name: String,
    val img: String,
    val height: String,
    val abilities: List<String>,
    val currentInheritor: String?,
    val formerInheritors: List<String>,
    val allegiance: String
)
