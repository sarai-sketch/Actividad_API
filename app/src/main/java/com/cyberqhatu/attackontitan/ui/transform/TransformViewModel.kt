package com.cyberqhatu.attackontitan.ui.transform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyberqhatu.attackontitan.model.Episode

class TransformViewModel : ViewModel() {

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    init {
        loadDummyEpisodes()
    }

    private fun loadDummyEpisodes() {
        // Dummy data moved to ViewModel
        val dummyEpisodes = listOf(
            Episode(
                id = 1,
                name = "To You, in 2000 Years: The Fall of Shiganshina, Part 1",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/c/c2/To_You%2C_in_2000_Years_-_The_Fall_of_Shiganshina%2C_Part_1.png/revision/latest/scale-to-width-down/1000",
                episode = "S1E1",
                characters = listOf("Eren", "Mikasa")
            ),
            Episode(
                id = 2,
                name = "That Day: The Fall of Shiganshina, Part 2",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/9/94/That_Day_-_The_Fall_of_Shiganshina%2C_Part_2.png/revision/latest/scale-to-width-down/1000",
                episode = "S1E2",
                characters = listOf("Eren", "Mikasa", "Armin")
            ),
            Episode(
                id = 3,
                name = "A Dim Light Amid Despair: Humanity's Comeback, Part 1",
                img = "https://static.wikia.nocookie.net/shingekinokyojin/images/f/fb/A_Dim_Light_Amid_Despair_-_Humanity%27s_Comeback%2C_Part_1.png/revision/latest/scale-to-width-down/1000",
                episode = "S1E3",
                characters = listOf("Eren", "Mikasa", "Armin", "Levi")
            )
        )
        _episodes.value = dummyEpisodes
    }
}
