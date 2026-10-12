package com.cyberqhatu.attackontitan.viewmodel

import androidx.lifecycle.ViewModel
import com.cyberqhatu.attackontitan.data.model.Character
import com.cyberqhatu.attackontitan.data.model.Episode
import com.cyberqhatu.attackontitan.data.model.Group
import com.cyberqhatu.attackontitan.data.model.Relative
import com.cyberqhatu.attackontitan.data.model.Titan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AotTab {
    CHARACTERS,
    TITANS,
    EPISODES,
    ABOUT
}

class MainViewModel : ViewModel() {

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

    val sampleCharacters = listOf(
        Character(
            id = 1,
            name = "Eren Yeager",
            img = "https://attackontitanapi.com/assets/images/characters/eren-jaeger.png",
            alia = listOf("Titán de Ataque", "Suicida"),
            species = listOf("Humano", "Titán Cambiante"),
            gender = "Masculino",
            age = 19,
            height = "183 cm",
            relatives = listOf(Relative("Yeager", listOf("Grisha Yeager", "Carla Yeager", "Zeke Yeager"))),
            birthplace = "Distrito Shiganshina",
            residence = "Muralla Maria",
            status = "Vivo",
            occupation = "Soldado del Cuerpo de Exploración",
            groups = listOf(Group("Cuerpo de Exploración", listOf("Escuadrón de Operaciones Especiales"))),
            roles = listOf("Protagonista"),
            episodes = listOf("Episodio 1", "Episodio 2", "Episodio 25")
        ),
        Character(
            id = 2,
            name = "Mikasa Ackerman",
            img = "https://attackontitanapi.com/assets/images/characters/mikasa-ackerman.png",
            alia = listOf("La mejor soldado de la promoción 104"),
            species = listOf("Humano (Ackerman)"),
            gender = "Femenino",
            age = 19,
            height = "176 cm",
            relatives = listOf(Relative("Ackerman", listOf("Levi Ackerman", "Kenny Ackerman"))),
            birthplace = "Muralla Maria",
            residence = "Cuerpo de Exploración",
            status = "Viva",
            occupation = "Soldado de Élite",
            groups = listOf(Group("Cuerpo de Exploración", listOf("Promoción 104"))),
            roles = listOf("Co-protagonista"),
            episodes = listOf("Episodio 1", "Episodio 5", "Episodio 12")
        ),
        Character(
            id = 3,
            name = "Levi Ackerman",
            img = "https://attackontitanapi.com/assets/images/characters/levi-ackerman.png",
            alia = listOf("El soldado más fuerte de la humanidad", "Capitán Levi"),
            species = listOf("Humano (Ackerman)"),
            gender = "Masculino",
            age = 34,
            height = "160 cm",
            relatives = listOf(Relative("Ackerman", listOf("Kuchel Ackerman", "Kenny Ackerman"))),
            birthplace = "Ciudad Subterránea",
            residence = "Base de Operaciones",
            status = "Vivo",
            occupation = "Capitán del Escuadrón Levi",
            groups = listOf(Group("Cuerpo de Exploración", listOf("Escuadrón Levi"))),
            roles = listOf("Capitán"),
            episodes = listOf("Episodio 4", "Episodio 9", "Episodio 14")
        ),
        Character(
            id = 4,
            name = "Armin Arlert",
            img = "https://attackontitanapi.com/assets/images/characters/armin-arlert.png",
            alia = listOf("Titán Colosal", "Comandante"),
            species = listOf("Humano", "Titán Cambiante"),
            gender = "Masculino",
            age = 19,
            height = "168 cm",
            relatives = listOf(Relative("Arlert", listOf("Abuelo Arlert"))),
            birthplace = "Distrito Shiganshina",
            residence = "Cuerpo de Exploración",
            status = "Vivo",
            occupation = "15° Comandante del Cuerpo de Exploración",
            groups = listOf(Group("Cuerpo de Exploración", listOf("Promoción 104"))),
            roles = listOf("Estratega"),
            episodes = listOf("Episodio 1", "Episodio 10", "Episodio 54")
        )
    )

    val sampleTitans = listOf(
        Titan(
            id = 1,
            name = "Titán de Ataque (Attack Titan)",
            img = "https://attackontitanapi.com/assets/images/titans/attack-titan.png",
            height = "15 metros",
            abilities = listOf("Memoria de herederos futuros", "Resistencia de combate superior", "Endurecimiento parcial"),
            currentInheritor = "Eren Yeager",
            formerInheritors = listOf("Eren Kruger", "Grisha Yeager"),
            allegiance = "Eldia / Cuerpo de Exploración"
        ),
        Titan(
            id = 2,
            name = "Titán Colosal (Colossal Titan)",
            img = "https://attackontitanapi.com/assets/images/titans/colossal-titan.png",
            height = "60 metros",
            abilities = listOf("Explosión de transformación masiva", "Emisión de vapor hirviente", "Fuerza destructiva pura"),
            currentInheritor = "Armin Arlert",
            formerInheritors = listOf("Bertolt Hoover"),
            allegiance = "Eldia (anteriormente Marley)"
        ),
        Titan(
            id = 3,
            name = "Titán Acorazado (Armored Titan)",
            img = "https://attackontitanapi.com/assets/images/titans/armored-titan.png",
            height = "15 metros",
            abilities = listOf("Placas de piel blindada", "Estructura defensiva de embestida", "Endurecimiento de garras"),
            currentInheritor = "Reiner Braun",
            formerInheritors = listOf("Herederos Marleyanos"),
            allegiance = "Marley"
        ),
        Titan(
            id = 4,
            name = "Titán Hembra (Female Titan)",
            img = "https://attackontitanapi.com/assets/images/titans/female-titan.png",
            height = "14 metros",
            abilities = listOf("Atraer titanes puros con grito", "Cristalización focalizada", "Alta agilidad mudo"),
            currentInheritor = "Annie Leonhart",
            formerInheritors = listOf("Herederas previas"),
            allegiance = "Marley"
        )
    )

    val sampleEpisodes = listOf(
        Episode(
            id = 1,
            name = "A ti, dentro de 2000 años",
            img = "https://attackontitanapi.com/assets/images/episodes/episode-1.png",
            episode = "S01E01",
            characters = listOf("Eren Yeager", "Mikasa Ackerman", "Armin Arlert", "Hannes")
        ),
        Episode(
            id = 2,
            name = "Aquel día: La caída de Shiganshina, Parte 2",
            img = "https://attackontitanapi.com/assets/images/episodes/episode-2.png",
            episode = "S01E02",
            characters = listOf("Eren Yeager", "Mikasa Ackerman", "Titán Colosal", "Titán Acorazado")
        ),
        Episode(
            id = 3,
            name = "Una luz tenue en la desesperación",
            img = "https://attackontitanapi.com/assets/images/episodes/episode-3.png",
            episode = "S01E03",
            characters = listOf("Eren Yeager", "Keith Shadis", "Jean Kirstein", "Sasha Blouse")
        ),
        Episode(
            id = 4,
            name = "La noche de la ceremonia de graduación",
            img = "https://attackontitanapi.com/assets/images/episodes/episode-4.png",
            episode = "S01E04",
            characters = listOf("Eren Yeager", "Annie Leonhart", "Reiner Braun", "Bertolt Hoover")
        )
    )
}
