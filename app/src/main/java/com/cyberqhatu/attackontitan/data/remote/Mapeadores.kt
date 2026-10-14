package com.cyberqhatu.attackontitan.data

import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.Group
import com.cyberqhatu.attackontitan.model.Relative
import com.cyberqhatu.attackontitan.model.Titan
import com.cyberqhatu.attackontitan.data.remote.EpisodioDto
import com.cyberqhatu.attackontitan.data.remote.PersonajeDto
import com.cyberqhatu.attackontitan.data.remote.TitanDto

/** "https://api.attackontitanapi.com/characters/188" -> 188 */
private fun idDeUrl(url: String): Int? = url.trimEnd('/').substringAfterLast('/').toIntOrNull()

/** La API entrega links en vez de nombres; los convertimos con tablas id -> nombre. */
private fun nombreDeUrl(url: String, personajes: Map<Int, String>, titanes: Map<Int, String>): String {
    val id = idDeUrl(url) ?: return url
    val tabla = if (url.contains("/titans/")) titanes else personajes
    return tabla[id] ?: "Desconocido #$id"
}

private fun textoOpcional(valor: String?): String =
    if (valor.isNullOrBlank() || valor.equals("unknown", ignoreCase = true)) "Desconocido" else valor

private fun traducirGenero(genero: String?): String = when (genero) {
    "Male" -> "Masculino"
    "Female" -> "Femenino"
    else -> textoOpcional(genero)
}

/** HomeScreen pinta en verde solo "Vivo"/"Viva", por eso se traduce el estado. */
private fun traducirEstado(estado: String?): String {
    val texto = estado.orEmpty().lowercase()
    return when {
        texto.contains("deceased") || texto.contains("dead") -> "Fallecido"
        texto.contains("alive") -> "Vivo"
        else -> "Desconocido"
    }
}

fun PersonajeDto.aPersonaje(): Character = Character(
    id = id,
    name = name ?: "Sin nombre",
    img = img,
    alia = alias.orEmpty(),
    species = species.orEmpty(),
    gender = traducirGenero(gender),
    age = (age as? Double)?.toInt(),
    height = textoOpcional(height),
    relatives = relatives.orEmpty().map { Relative(it.family.orEmpty(), it.members.orEmpty()) },
    birthplace = birthplace,
    residence = residence,
    status = traducirEstado(status),
    occupation = occupation,
    groups = groups.orEmpty().map { Group(it.name.orEmpty(), it.subGroups.orEmpty()) },
    roles = roles.orEmpty(),
    episodes = episodes.orEmpty()
)

fun TitanDto.aTitan(personajes: Map<Int, String>): Titan = Titan(
    id = id,
    name = name ?: "Sin nombre",
    img = img.orEmpty(),
    height = textoOpcional(height),
    abilities = abilities.orEmpty().map { it.trim() },
    currentInheritor = currentInheritor?.let { nombreDeUrl(it, personajes, emptyMap()) },
    formerInheritors = formerInheritors.orEmpty().map { nombreDeUrl(it, personajes, emptyMap()) },
    allegiance = textoOpcional(allegiance)
)

fun EpisodioDto.aEpisodio(personajes: Map<Int, String>, titanes: Map<Int, String>): Episode = Episode(
    id = id,
    name = name ?: "Sin nombre",
    img = img.orEmpty(),
    episode = episode.orEmpty(),
    characters = characters.orEmpty().map { nombreDeUrl(it, personajes, titanes) }
)