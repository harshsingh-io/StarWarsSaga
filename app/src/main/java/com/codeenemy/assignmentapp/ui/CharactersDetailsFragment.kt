package com.codeenemy.assignmentapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.codeenemy.assignmentapp.adapters.FilmsAdapter
import com.codeenemy.assignmentapp.databinding.FragmentCharactersDetailsBinding
import com.codeenemy.assignmentapp.utils.Resource
import com.codeenemy.assignmentapp.viewmodels.CharacterDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * [CharactersDetailsFragment] is a fragment displaying details of a Star Wars character, including films and homeworld.
 */
@AndroidEntryPoint
class CharactersDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharactersDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Observe character details
        viewModel.details.observe(viewLifecycleOwner, Observer { result ->
            binding.textViewFullNameValue.text = result.name
            binding.textViewSkinColorValue.text = result.skinColor
            binding.textViewHairColorValue.text = result.hairColor
            binding.textViewHeightValue.text = result.height
            binding.textViewMassValue.text = result.mass
            binding.textViewEyeColorValue.text = result.eyeColor
            binding.textViewGenderValue.text = result.gender
            binding.textViewBirthYearValue.text = result.birthYear
        })

        // Observe film details
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.filmResponseDetails.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        binding.filmProgressBar.isVisible = false
                        filmsAdapter.submitList(event.data)
                        binding.recyclerViewFilms.adapter = filmsAdapter
                    }
                    is Resource.Failure -> {
                        binding.filmProgressBar.isVisible = false
                        binding.textViewFilmsError.isVisible = true
                        binding.textViewFilmsError.text = event.message
                    }
                    is Resource.Loading -> {
                        binding.filmProgressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

        // Observe homeworld details
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeWorldResponse.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.data!!.name
                    }
                    is Resource.Failure -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.message
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.progressBarHomeWord.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

        return view
    }
}
