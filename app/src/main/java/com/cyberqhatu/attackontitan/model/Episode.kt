package com.cyberqhatu.attackontitan.model

data class Episode(
    val id: Int,
    val name: String,
    val img: String? = null,
    val episode: String,
    val characters: List<String>? = emptyList()
)
