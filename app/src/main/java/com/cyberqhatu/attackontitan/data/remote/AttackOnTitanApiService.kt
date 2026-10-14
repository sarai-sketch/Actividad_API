package com.cyberqhatu.attackontitan.data.remote

import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.PagedResponse
import com.cyberqhatu.attackontitan.model.Titan
import retrofit2.http.GET

interface AttackOnTitanApiService {
    @GET("characters")
    suspend fun getCharacters(): PagedResponse<Character>

    @GET("titans")
    suspend fun getTitans(): PagedResponse<Titan>

    @GET("episodes")
    suspend fun getEpisodes(): PagedResponse<Episode>
}
