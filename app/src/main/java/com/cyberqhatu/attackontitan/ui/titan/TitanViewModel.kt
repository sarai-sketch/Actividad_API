package com.cyberqhatu.attackontitan.ui.titan

import androidx.lifecycle.ViewModel
import com.cyberqhatu.attackontitan.model.Titan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TitanViewModel : ViewModel() {

    private val _titan = MutableStateFlow<Titan?>(
        Titan(
            id = 1,
            name = "Titán de Ataque (Attack Titan)",
            img = "https://static.wikia.nocookie.net/shingeki/images/f/f8/Attack_Titan_%28Anime%29_character_image.png",
            height = "15 metros",
            abilities = listOf(
                "Capacidad de ver los recuerdos de sus herederos futuros y pasados",
                "Fuerza física y agilidad sobresalientes",
                "Endurecimiento parcial"
            ),
            currentInheritor = "Eren Jaeger",
            formerInheritors = listOf("Grisha Jaeger", "Eren Kruger"),
            allegiance = "Eldia / Paradis"
        )
    )
    val titan: StateFlow<Titan?> = _titan.asStateFlow()
}
