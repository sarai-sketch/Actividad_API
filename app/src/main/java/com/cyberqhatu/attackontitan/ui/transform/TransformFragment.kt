package com.cyberqhatu.attackontitan.ui.transform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyberqhatu.attackontitan.R
import com.cyberqhatu.attackontitan.adapter.EpisodeAdapter

class TransformFragment : Fragment() {

    // Instanciamos el ViewModel usando el delegado 'viewModels()'
    private val viewModel: TransformViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_transform, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerview_transform)

        // Configurar RecyclerView y Adaptador
        val adapter = EpisodeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Observar los datos desde el ViewModel
        viewModel.episodes.observe(viewLifecycleOwner) { episodeList ->
            adapter.submitList(episodeList)
        }

        return root
    }
}
