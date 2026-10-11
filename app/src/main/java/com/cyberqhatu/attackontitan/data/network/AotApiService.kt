package com.cyberqhatu.attackontitan.data.network

import com.cyberqhatu.attackontitan.data.model.Character
import com.cyberqhatu.attackontitan.data.model.Episode
import com.cyberqhatu.attackontitan.data.model.PagedResponse
import com.cyberqhatu.attackontitan.data.model.Titan
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AotApiService {

    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): PagedResponse<Character>

    @GET("titans")
    suspend fun getTitans(@Query("page") page: Int): PagedResponse<Titan>

    @GET("episodes")
    suspend fun getEpisodes(@Query("page") page: Int): PagedResponse<Episode>

    companion object {
        private const val BASE_URL = "https://api.attackontitanapi.com/"

        fun create(): AotApiService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AotApiService::class.java)
    }
}
