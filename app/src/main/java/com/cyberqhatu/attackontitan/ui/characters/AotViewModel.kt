package com.cyberqhatu.attackontitan.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberqhatu.attackontitan.data.model.Character
import com.cyberqhatu.attackontitan.data.model.Episode
import com.cyberqhatu.attackontitan.data.model.PagedResponse
import com.cyberqhatu.attackontitan.data.model.Titan
import com.cyberqhatu.attackontitan.data.repository.AotRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AotSection { CHARACTERS, TITANS, EPISODES }

sealed class AotUiState<out T> {
    object Loading : AotUiState<Nothing>()
    data class Success<T>(val items: List<T>, val hasMore: Boolean) : AotUiState<T>()
    data class Error(val message: String) : AotUiState<Nothing>()
}

/** Maneja la paginación (page=1,2,3...) de una sección. */
class Pager<T>(
    private val scope: CoroutineScope,
    private val fetch: suspend (Int) -> PagedResponse<T>
) {
    private val _state = MutableStateFlow<AotUiState<T>>(AotUiState.Loading)
    val state: StateFlow<AotUiState<T>> = _state.asStateFlow()

    private val items = mutableListOf<T>()
    private var nextPage = 1
    private var hasMore = true
    private var loading = false

    val isEmpty: Boolean get() = items.isEmpty()

    fun loadNext() {
        if (loading || !hasMore) return
        loading = true
        if (items.isEmpty()) _state.value = AotUiState.Loading
        scope.launch {
            try {
                val response = fetch(nextPage)
                items += response.results
                hasMore = response.info.next != null
                nextPage++
                _state.value = AotUiState.Success(items.toList(), hasMore)
            } catch (e: Exception) {
                _state.value = AotUiState.Error(e.localizedMessage ?: "Error al conectar con la API")
            } finally {
                loading = false
            }
        }
    }
}

class AotViewModel(
    private val repository: AotRepository = AotRepository()
) : ViewModel() {

    private val charactersPager = Pager<Character>(viewModelScope) { repository.getCharacters(it) }
    private val titansPager = Pager<Titan>(viewModelScope) { repository.getTitans(it) }
    private val episodesPager = Pager<Episode>(viewModelScope) { repository.getEpisodes(it) }

    val charactersState: StateFlow<AotUiState<Character>> = charactersPager.state
    val titansState: StateFlow<AotUiState<Titan>> = titansPager.state
    val episodesState: StateFlow<AotUiState<Episode>> = episodesPager.state

    /** Carga la primera página de la sección si todavía no tiene datos. */
    fun ensureLoaded(section: AotSection) {
        when (section) {
            AotSection.CHARACTERS -> if (charactersPager.isEmpty) charactersPager.loadNext()
            AotSection.TITANS -> if (titansPager.isEmpty) titansPager.loadNext()
            AotSection.EPISODES -> if (episodesPager.isEmpty) episodesPager.loadNext()
        }
    }

    fun loadNext(section: AotSection) {
        when (section) {
            AotSection.CHARACTERS -> charactersPager.loadNext()
            AotSection.TITANS -> titansPager.loadNext()
            AotSection.EPISODES -> episodesPager.loadNext()
        }
    }

    fun retry(section: AotSection) = loadNext(section)
}
