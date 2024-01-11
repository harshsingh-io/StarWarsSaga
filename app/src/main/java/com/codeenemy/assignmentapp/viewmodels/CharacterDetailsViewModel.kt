package com.codeenemy.assignmentapp.viewmodels

import androidx.lifecycle.*
import com.codeenemy.assignmentapp.data.repositories.CharactersRepository
import com.codeenemy.assignmentapp.models.FilmResponse
import com.codeenemy.assignmentapp.models.Character
import com.codeenemy.assignmentapp.models.HomeWorldResponse
import com.codeenemy.assignmentapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [CharacterDetailsViewModel] is a ViewModel responsible for handling data related to character details.
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Retrieve character details from the savedStateHandle
    private val myArguments = savedStateHandle.get<Character>("characterDetails")

    // LiveData for character details
    private val _details = MutableLiveData<Character>()
    val details: LiveData<Character>
        get() = _details

    // StateFlow for home world response
    private val _homeWorld = MutableStateFlow<Resource<HomeWorldResponse>>(Resource.Empty())
    val homeWorldResponse: StateFlow<Resource<HomeWorldResponse>>
        get() = _homeWorld

    // StateFlow for film response details
    private val _filmDetails = MutableStateFlow<Resource<List<FilmResponse>>>(Resource.Empty())
    val filmResponseDetails: StateFlow<Resource<List<FilmResponse>>>
        get() = _filmDetails

    // List to store film responses
    private val filmsList: ArrayList<FilmResponse> = ArrayList()

    init {
        // Set character details to LiveData
        _details.value = myArguments!!

        // Fetch home world and film data
        getHomeWorldData(myArguments.homeworld)
        getFilmData()
    }

    /**
     * Fetch film data for each film in the character's films list.
     */
    private fun getFilmData() {
        myArguments!!.films.forEach { film ->

            viewModelScope.launch(Dispatchers.IO) {
                _filmDetails.value = Resource.Loading()
                when (val characterDetailsResponse = charactersRepository.getFilm(film)) {
                    is Resource.Failure -> {
                        _filmDetails.value =
                            Resource.Failure(characterDetailsResponse.message!!)
                    }
                    is Resource.Success -> {
                        if (characterDetailsResponse.data == null) {
                            _filmDetails.value = Resource.Failure("Empty Film Response List")
                        } else {
                            filmsList.add(characterDetailsResponse.data)
                            _filmDetails.value = Resource.Success(filmsList)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    /**
     * Fetch home world data for the character.
     */
    private fun getHomeWorldData(homeWorldUrl: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _filmDetails.value = Resource.Loading()
            when (val homeWorldResponse = charactersRepository.getHomeWorld(homeWorldUrl)) {
                is Resource.Failure -> {
                    _homeWorld.value = Resource.Failure(homeWorldResponse.message!!)
                }
                is Resource.Success -> {
                    if (homeWorldResponse.data == null) {
                        _homeWorld.value = Resource.Failure("N/A")
                    } else {
                        _homeWorld.value = Resource.Success(homeWorldResponse.data)
                    }
                }

                else -> {}
            }
        }
    }
}
