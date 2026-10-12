package com.cyberqhatu.attackontitan.data.model

data class CharacterResponse(
    val results: List<AotCharacter>
)

data class AotCharacter(
    val id: Int,
    val name: String,
    val status: String?,
    val gender: String?
)
