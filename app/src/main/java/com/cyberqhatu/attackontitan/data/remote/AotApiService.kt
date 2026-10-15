package com.cyberqhatu.attackontitan.data.remote

import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.PagedResponse
import com.cyberqhatu.attackontitan.model.Titan
import retrofit2.http.GET
import retrofit2.http.Query

interface AotApiService {

    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int = 1): PagedResponse<Character>

    @GET("titans")
    suspend fun getTitans(@Query("page") page: Int = 1): PagedResponse<Titan>

    @GET("episodes")
    suspend fun getEpisodes(@Query("page") page: Int = 1): PagedResponse<Episode>
}
