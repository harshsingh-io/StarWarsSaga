package com.codeenemy.assignmentapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.codeenemy.assignmentapp.adapters.CharactersAdapter
import com.codeenemy.assignmentapp.databinding.FragmentCharactersBinding
import com.codeenemy.assignmentapp.utils.hideKeyboard
import com.codeenemy.assignmentapp.models.Character
import com.codeenemy.assignmentapp.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

/**
 * [CharactersFragment] is a fragment displaying a list of Star Wars characters using pagination.
 */
@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(CharactersAdapter.OnClickListener { character ->
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailsFragment(
                    character
                )
            findNavController().navigate(action)
            binding.searchView.editText!!.setText("")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set up the click listener for the search icon
        binding.searchView.setEndIconOnClickListener {
            // Trigger the character data fetching with the provided search string
            setUpObserver(binding.searchView.editText!!.text.toString())
            binding.charactersProgressBar.isVisible = true
            hideKeyboard()
        }

        // Initial setup with an empty search string
        setUpObserver("")

        // Set up the adapter for the characters RecyclerView
        setUpAdapter()

        return view
    }

    /**
     * Set up the observer for characters data based on the provided search string.
     */
    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getCharacters(searchString).collect {
                charactersAdapter.submitData(lifecycle, it)
            }
        }
    }

    /**
     * Set up the adapter for the characters RecyclerView.
     */
    private fun setUpAdapter() {

        binding.charactersRecyclerview.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        // Listen for load state changes in the characters adapter
        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (charactersAdapter.snapshot().isEmpty()) {
                    binding.charactersProgressBar.isVisible = true
                }
                binding.textViewError.isVisible = false
            } else {
                binding.charactersProgressBar.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    if (charactersAdapter.snapshot().isEmpty()) {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }
}
