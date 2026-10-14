package com.cyberqhatu.attackontitan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.databinding.ItemCharacterBinding

class CharacterAdapter : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.tvCharacterName.text = character.name

            // Estado, Género y Edad
            val status = character.status ?: "Desconocido"
            val gender = character.gender ?: "Desconocido"
            val age = if (character.age != null) "${character.age} años" else "Edad N/A"
            binding.tvCharacterStatusGender.text = "Estado: $status • $gender • $age"

            // Ocupación
            binding.tvCharacterOccupation.text = "Ocupación: ${character.occupation ?: "Ninguna"}"

            // Especie
            binding.tvCharacterSpecies.text = if (character.species.isNotEmpty()) {
                "Especie: ${character.species.joinToString(", ")}"
            } else {
                "Especie: Desconocida"
            }

            // Alias
            if (character.alia.isNotEmpty()) {
                binding.tvCharacterAlias.visibility = View.VISIBLE
                binding.tvCharacterAlias.text = "Alias: ${character.alia.joinToString(", ")}"
            } else {
                binding.tvCharacterAlias.visibility = View.GONE
            }

            // Lugar de origen y residencia
            val birth = character.birthplace ?: "Desconocido"
            val residence = character.residence ?: "Desconocido"
            binding.tvCharacterLocation.text = "Origen: $birth | Residencia: $residence"

            // Episodios
            binding.tvCharacterEpisodes.text = "Aparece en ${character.episodes.size} episodio(s)"

            // Cargar imagen con Coil
            binding.ivCharacterImage.load(character.img) {
                crossfade(true)
            }
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
