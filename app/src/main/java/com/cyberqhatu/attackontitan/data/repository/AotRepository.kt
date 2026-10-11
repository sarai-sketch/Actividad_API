package com.cyberqhatu.attackontitan.data.repository

import com.cyberqhatu.attackontitan.data.model.Character
import com.cyberqhatu.attackontitan.data.model.Episode
import com.cyberqhatu.attackontitan.data.model.PagedResponse
import com.cyberqhatu.attackontitan.data.model.Titan
import com.cyberqhatu.attackontitan.data.network.AotApiService

class AotRepository(
    private val api: AotApiService = AotApiService.create()
) {
    suspend fun getCharacters(page: Int): PagedResponse<Character> = api.getCharacters(page)
    suspend fun getTitans(page: Int): PagedResponse<Titan> = api.getTitans(page)
    suspend fun getEpisodes(page: Int): PagedResponse<Episode> = api.getEpisodes(page)
}
