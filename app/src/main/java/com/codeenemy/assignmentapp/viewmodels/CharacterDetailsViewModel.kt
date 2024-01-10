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


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val myArguments = savedStateHandle.get<Character>("characterDetails")

    private val _details = MutableLiveData<Character>()
    val details: LiveData<Character>
        get() = _details

    private val _homeWorld = MutableStateFlow<Resource<HomeWorldResponse>>(Resource.Empty())
    val homeWorldResponse: StateFlow<Resource<HomeWorldResponse>>
        get() = _homeWorld

    private val _filmDetails = MutableStateFlow<Resource<List<FilmResponse>>>(Resource.Empty())
    val filmResponseDetails: StateFlow<Resource<List<FilmResponse>>>
        get() = _filmDetails

    private val filmsList: ArrayList<FilmResponse> = ArrayList()

    init {
        _details.value = myArguments!!
        getHomeWorldData(myArguments.homeworld)
        getFilmData()
    }

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