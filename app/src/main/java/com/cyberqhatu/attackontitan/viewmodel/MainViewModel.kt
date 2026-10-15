package com.cyberqhatu.attackontitan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberqhatu.attackontitan.data.SnkRepository
import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.Titan
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

sealed class MainUiState<out T> {
    object Loading : MainUiState<Nothing>()
    data class Success<T>(val data: List<T>) : MainUiState<T>()
    data class Error(val message: String) : MainUiState<Nothing>()
}

class MainViewModel(
    private val repository: SnkRepository = SnkRepository()
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(AotTab.CHARACTERS)
    val selectedTab: StateFlow<AotTab> = _selectedTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _charactersState = MutableStateFlow<MainUiState<Character>>(MainUiState.Loading)
    val charactersState: StateFlow<MainUiState<Character>> = _charactersState.asStateFlow()

    private val _titansState = MutableStateFlow<MainUiState<Titan>>(MainUiState.Loading)
    val titansState: StateFlow<MainUiState<Titan>> = _titansState.asStateFlow()

    private val _episodesState = MutableStateFlow<MainUiState<Episode>>(MainUiState.Loading)
    val episodesState: StateFlow<MainUiState<Episode>> = _episodesState.asStateFlow()

    init {
        loadCharacters()
        loadTitans()
        loadEpisodes()
    }

    fun selectTab(tab: AotTab) {
        _selectedTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _charactersState.value = MainUiState.Loading
            try {
                val response = repository.getCharacters(1)
                _charactersState.value = MainUiState.Success(response.results)
            } catch (e: Exception) {
                _charactersState.value = MainUiState.Error(e.localizedMessage ?: "Error al cargar personajes")
            }
        }
    }

    fun loadTitans() {
        viewModelScope.launch {
            _titansState.value = MainUiState.Loading
            try {
                val response = repository.getTitans(1)
                _titansState.value = MainUiState.Success(response.results)
            } catch (e: Exception) {
                _titansState.value = MainUiState.Error(e.localizedMessage ?: "Error al cargar titanes")
            }
        }
    }

    fun loadEpisodes() {
        viewModelScope.launch {
            _episodesState.value = MainUiState.Loading
            try {
                val response = repository.getEpisodes(1)
                _episodesState.value = MainUiState.Success(response.results)
            } catch (e: Exception) {
                _episodesState.value = MainUiState.Error(e.localizedMessage ?: "Error al cargar episodios")
            }
        }
    }
}
