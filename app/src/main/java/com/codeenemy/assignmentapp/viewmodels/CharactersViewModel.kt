package com.codeenemy.assignmentapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.codeenemy.assignmentapp.data.repositories.CharactersRepository
import com.codeenemy.assignmentapp.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [CharactersViewModel] is a ViewModel responsible for handling data related to characters.
 */
@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {

    /**
     * Get characters based on the provided search string.
     *
     * @param searchString The string used for filtering characters.
     * @return Flow of PagingData containing characters.
     */
    fun getCharacters(searchString: String): Flow<PagingData<Character>> {
        // Retrieve characters using the repository and cache the result in the viewModelScope
        return charactersRepository.getCharacters(searchString).cachedIn(viewModelScope)
    }
}
