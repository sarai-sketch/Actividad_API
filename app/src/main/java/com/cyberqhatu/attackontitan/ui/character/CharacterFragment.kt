package com.cyberqhatu.attackontitan.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyberqhatu.attackontitan.R
import com.cyberqhatu.attackontitan.adapter.CharacterAdapter

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_character, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerview_character)

        val adapter = CharacterAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner) { characterList ->
            adapter.submitList(characterList)
        }

        return root
    }
}
