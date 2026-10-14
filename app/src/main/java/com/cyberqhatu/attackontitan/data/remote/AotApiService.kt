package com.cyberqhatu.attackontitan.data.remote

import com.cyberqhatu.attackontitan.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AotApiService {

    @GET("characters")
    suspend fun obtenerPersonajes(@Query("page") pagina: Int): PagedResponse<PersonajeDto>

    @GET("titans")
    suspend fun obtenerTitanes(@Query("page") pagina: Int): PagedResponse<TitanDto>

    @GET("episodes")
    suspend fun obtenerEpisodios(@Query("page") pagina: Int): PagedResponse<EpisodioDto>
}