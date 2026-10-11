package com.cyberqhatu.attackontitan.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyberqhatu.attackontitan.databinding.FragmentAotBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AotFragment : Fragment() {

    private var _binding: FragmentAotBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AotViewModel by viewModels()

    private val characterAdapter = CharacterAdapter()
    private val titanAdapter = TitanAdapter()
    private val episodeAdapter = EpisodeAdapter()

    private var section = AotSection.CHARACTERS
    private var stateJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && !rv.canScrollVertically(1)) viewModel.loadNext(section)
            }
        })

        binding.tabs.addTab(binding.tabs.newTab().setText("Personajes"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Titanes"))
        binding.tabs.addTab(binding.tabs.newTab().setText("Episodios"))
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                select(AotSection.values()[tab.position])
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.retryButton.setOnClickListener { viewModel.retry(section) }

        binding.tabs.getTabAt(section.ordinal)?.select()
        select(section)
    }

    private fun select(newSection: AotSection) {
        section = newSection
        viewModel.ensureLoaded(newSection)
        when (newSection) {
            AotSection.CHARACTERS -> collectState(viewModel.charactersState, characterAdapter)
            AotSection.TITANS -> collectState(viewModel.titansState, titanAdapter)
            AotSection.EPISODES -> collectState(viewModel.episodesState, episodeAdapter)
        }
    }

    private fun <T> collectState(flow: StateFlow<AotUiState<T>>, adapter: ListAdapter<T, *>) {
        binding.recycler.adapter = adapter
        stateJob?.cancel()
        stateJob = viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect { render(it, adapter) }
            }
        }
    }

    private fun <T> render(state: AotUiState<T>, adapter: ListAdapter<T, *>) {
        when (state) {
            is AotUiState.Loading -> {
                binding.progress.visibility = View.VISIBLE
                binding.errorGroup.visibility = View.GONE
            }
            is AotUiState.Success -> {
                binding.progress.visibility = View.GONE
                binding.errorGroup.visibility = View.GONE
                adapter.submitList(state.items)
            }
            is AotUiState.Error -> {
                binding.progress.visibility = View.GONE
                binding.errorGroup.visibility = View.VISIBLE
                binding.errorText.text = state.message
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stateJob?.cancel()
        binding.recycler.adapter = null
        _binding = null
    }
}
