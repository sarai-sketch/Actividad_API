package com.cyberqhatu.attackontitan.data

import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.Titan

object SnkMapper {

    fun mapCharacter(character: Character): Character = character

    fun mapTitan(titan: Titan): Titan = titan

    fun mapEpisode(episode: Episode): Episode = episode
}
