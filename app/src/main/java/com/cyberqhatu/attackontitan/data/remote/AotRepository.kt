package com.cyberqhatu.attackontitan.data

import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.PagedResponse
import com.cyberqhatu.attackontitan.model.Titan
import com.cyberqhatu.attackontitan.data.remote.AotApiService
import com.cyberqhatu.attackontitan.data.remote.RetrofitClient
import com.cyberqhatu.attackontitan.util.Resultado
import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

/** Único acceso a la API: llama a Retrofit, convierte DTOs y traduce excepciones en [Resultado]. */
class AotRepository(
    private val api: AotApiService = RetrofitClient.api
) {

    suspend fun obtenerPersonajes(pagina: Int): Resultado<PagedResponse<Character>> =
        llamarApi {
            val respuesta = api.obtenerPersonajes(pagina)
            PagedResponse(respuesta.info, respuesta.results.map { it.aPersonaje() })
        }

    suspend fun obtenerTitanes(personajes: Map<Int, String>): Resultado<List<Titan>> =
        llamarApi { api.obtenerTitanes(1).results.map { it.aTitan(personajes) } }

    suspend fun obtenerEpisodios(
        pagina: Int,
        personajes: Map<Int, String>,
        titanes: Map<Int, String>
    ): Resultado<PagedResponse<Episode>> =
        llamarApi {
            val respuesta = api.obtenerEpisodios(pagina)
            PagedResponse(respuesta.info, respuesta.results.map { it.aEpisodio(personajes, titanes) })
        }

    private suspend fun <T> llamarApi(llamada: suspend () -> T): Resultado<T> =
        try {
            Resultado.Exito(llamada())
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Resultado.Error("El servidor respondió con un error (código ${e.code()}).")
        } catch (e: IOException) {
            Resultado.Error("No hay conexión a internet. Revisa tu red e inténtalo de nuevo.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado: ${e.localizedMessage ?: "desconocido"}")
        }
}