package com.cyberqhatu.attackontitan.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyberqhatu.attackontitan.data.model.Character

class CharacterViewModel : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    init {
        loadMockCharacters()
    }

    private fun loadMockCharacters() {
        // Datos de prueba simulando la respuesta de la API de Attack on Titan
        val mockCharacters = listOf(
            Character(
                id = 1,
                name = "Eren Jaeger",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/a/a1/Eren_Jaeger_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Male",
                age = 19,
                height = "183 cm",
                status = "Deceased",
                occupation = "Scout Regiment (Formerly)",
                species = listOf("Human", "Intelligent Titan"),
                alia = listOf("Suicidal Blockhead", "Attack Titan", "Founding Titan", "Usurper"),
                birthplace = "Shiganshina District",
                residence = "Wall Rose",
                episodes = listOf("S1E1", "S1E2", "S1E3", "S1E4", "S1E5", "S4E1", "S4E16", "S4E28")
            ),
            Character(
                id = 2,
                name = "Mikasa Ackermann",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/4/4f/Mikasa_Ackermann_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Female",
                age = 19,
                height = "176 cm",
                status = "Alive",
                occupation = "Scout Regiment Officer",
                species = listOf("Human (Ackermann)"),
                alia = listOf("Ackermann", "Monster"),
                birthplace = "Shiganshina District Outskirts",
                residence = "Wall Rose",
                episodes = listOf("S1E1", "S1E2", "S1E3", "S1E4", "S1E5", "S4E28")
            ),
            Character(
                id = 3,
                name = "Armin Arlert",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/0/02/Armin_Arlert_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Male",
                age = 19,
                height = "168 cm",
                status = "Alive",
                occupation = "15th Commander of Scout Regiment",
                species = listOf("Human", "Colossal Titan"),
                alia = listOf("Colossal Titan", "Tactician"),
                birthplace = "Shiganshina District",
                residence = "Wall Rose",
                episodes = listOf("S1E1", "S1E2", "S1E3", "S3E18", "S4E28")
            ),
            Character(
                id = 4,
                name = "Levi Ackermann",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/9/94/Levi_Ackermann_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Male",
                age = 30,
                height = "160 cm",
                status = "Alive",
                occupation = "Captain of Special Operations Squad",
                species = listOf("Human (Ackermann)"),
                alia = listOf("Humanity's Strongest Soldier", "Captain Levi"),
                birthplace = "Underground City",
                residence = "Scout Regiment Headquarters",
                episodes = listOf("S1E4", "S1E9", "S1E13", "S3E12", "S4E14", "S4E28")
            ),
            Character(
                id = 5,
                name = "Erwin Smith",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/c/c5/Erwin_Smith_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Male",
                age = 38,
                height = "188 cm",
                status = "Deceased",
                occupation = "13th Commander of Scout Regiment",
                species = listOf("Human"),
                alia = listOf("Commander Erwin", "Eyebrows"),
                birthplace = "Wall Rose",
                residence = "Scout Regiment Headquarters",
                episodes = listOf("S1E4", "S1E13", "S2E12", "S3E16", "S3E18")
            ),
            Character(
                id = 6,
                name = "Sasha Blouse",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/8/82/Sasha_Blouse_profile_picture.png/revision/latest/scale-to-width-down/350",
                gender = "Female",
                age = 20,
                height = "168 cm",
                status = "Deceased",
                occupation = "Scout Regiment Soldier",
                species = listOf("Human"),
                alia = listOf("Potato Girl"),
                birthplace = "Dauper Village",
                residence = "Wall Rose",
                episodes = listOf("S1E3", "S1E4", "S2E2", "S4E8")
            )
        )
        _characters.value = mockCharacters
    }
}
