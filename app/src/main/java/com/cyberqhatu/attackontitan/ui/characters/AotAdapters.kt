package com.cyberqhatu.attackontitan.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cyberqhatu.attackontitan.data.model.Character
import com.cyberqhatu.attackontitan.data.model.Episode
import com.cyberqhatu.attackontitan.data.model.Titan
import com.cyberqhatu.attackontitan.databinding.ItemAotCardBinding

class AotCardHolder(private val binding: ItemAotCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(img: String?, title: String, subtitle: String, details: String) {
        binding.image.load(img) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_report_image)
        }
        binding.title.text = title
        binding.subtitle.text = subtitle
        binding.details.text = details
    }

    companion object {
        fun from(parent: ViewGroup) = AotCardHolder(
            ItemAotCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

private fun <T : Any> diffById(idOf: (T) -> Int): DiffUtil.ItemCallback<T> =
    object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = idOf(oldItem) == idOf(newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }

class CharacterAdapter : ListAdapter<Character, AotCardHolder>(diffById<Character> { it.id }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AotCardHolder.from(parent)

    override fun onBindViewHolder(holder: AotCardHolder, position: Int) {
        val c = getItem(position)
        holder.bind(
            img = c.img,
            title = c.name,
            subtitle = listOfNotNull(c.status, c.gender, c.occupation).joinToString(" • "),
            details = c.species.joinToString(", ")
        )
    }
}

class TitanAdapter : ListAdapter<Titan, AotCardHolder>(diffById<Titan> { it.id }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AotCardHolder.from(parent)

    override fun onBindViewHolder(holder: AotCardHolder, position: Int) {
        val t = getItem(position)
        holder.bind(
            img = t.img,
            title = t.name,
            subtitle = "Altura: ${t.height} • Lealtad: ${t.allegiance ?: "N/A"}",
            details = t.abilities.joinToString(", ")
        )
    }
}

class EpisodeAdapter : ListAdapter<Episode, AotCardHolder>(diffById<Episode> { it.id }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AotCardHolder.from(parent)

    override fun onBindViewHolder(holder: AotCardHolder, position: Int) {
        val e = getItem(position)
        holder.bind(
            img = e.img,
            title = e.name,
            subtitle = e.episode,
            details = "${e.characters.size} personajes"
        )
    }
}
