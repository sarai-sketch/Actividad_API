package com.cyberqhatu.attackontitan.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberqhatu.attackontitan.data.AotRepository
import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.PagedResponse
import com.cyberqhatu.attackontitan.model.Titan
import com.cyberqhatu.attackontitan.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AotTab {
    CHARACTERS,
    TITANS,
    EPISODES,
    ABOUT
}

class MainViewModel(
    private val repositorio: AotRepository = AotRepository()
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(AotTab.CHARACTERS)
    val selectedTab: StateFlow<AotTab> = _selectedTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun selectTab(tab: AotTab) {
        _selectedTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Listas que lee HomeScreen (datos reales de la API)
    var sampleCharacters by mutableStateOf<List<Character>>(emptyList())
        private set
    var sampleTitans by mutableStateOf<List<Titan>>(emptyList())
        private set
    var sampleEpisodes by mutableStateOf<List<Episode>>(emptyList())
        private set

    // Tablas id -> nombre para convertir los links de la API en nombres legibles
    private var nombresPersonajes: Map<Int, String> = emptyMap()
    private var nombresTitanes: Map<Int, String> = emptyMap()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            cargarPersonajes() // primero: los demás necesitan sus nombres
            cargarTitanes()
            cargarEpisodios()
        }
    }

    private suspend fun cargarPersonajes() {
        recorrerPaginas({ repositorio.obtenerPersonajes(it) }) {
            sampleCharacters = sampleCharacters + it
        }
        nombresPersonajes = sampleCharacters.associate { it.id to it.name }
    }

    private suspend fun cargarTitanes() {
        when (val resultado = repositorio.obtenerTitanes(nombresPersonajes)) {
            is Resultado.Exito -> {
                sampleTitans = resultado.datos
                nombresTitanes = resultado.datos.associate { it.id to it.name }
            }
            is Resultado.Error -> Log.e(TAG, resultado.mensaje)
        }
    }

    private suspend fun cargarEpisodios() {
        recorrerPaginas({ repositorio.obtenerEpisodios(it, nombresPersonajes, nombresTitanes) }) {
            sampleEpisodes = sampleEpisodes + it
        }
    }

    /** Pide la página 1, 2, 3... hasta llegar a la última que indica la API. */
    private suspend fun <T> recorrerPaginas(
        obtener: suspend (Int) -> Resultado<PagedResponse<T>>,
        alRecibir: (List<T>) -> Unit
    ) {
        var pagina = 1
        var totalPaginas = 1
        while (pagina <= totalPaginas) {
            when (val resultado = obtener(pagina)) {
                is Resultado.Exito -> {
                    alRecibir(resultado.datos.results)
                    totalPaginas = resultado.datos.info.pages
                    pagina++
                }
                is Resultado.Error -> {
                    Log.e(TAG, resultado.mensaje)
                    return
                }
            }
        }
    }

    private companion object {
        const val TAG = "AotApi"
    }
}
