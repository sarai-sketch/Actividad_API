package com.cyberqhatu.attackontitan.data

import com.cyberqhatu.attackontitan.data.remote.AotApiService
import com.cyberqhatu.attackontitan.data.remote.RetrofitClient
import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.PagedResponse
import com.cyberqhatu.attackontitan.model.Titan

class SnkRepository(
    private val apiService: AotApiService = RetrofitClient.apiService,
    private val mapper: SnkMapper = SnkMapper
) {

    suspend fun getCharacters(page: Int = 1): PagedResponse<Character> {
        val response = apiService.getCharacters(page)
        val mappedResults = response.results.map { mapper.mapCharacter(it) }
        return response.copy(results = mappedResults)
    }

    suspend fun getTitans(page: Int = 1): PagedResponse<Titan> {
        val response = apiService.getTitans(page)
        val mappedResults = response.results.map { mapper.mapTitan(it) }
        return response.copy(results = mappedResults)
    }

    suspend fun getEpisodes(page: Int = 1): PagedResponse<Episode> {
        val response = apiService.getEpisodes(page)
        val mappedResults = response.results.map { mapper.mapEpisode(it) }
        return response.copy(results = mappedResults)
    }
}
