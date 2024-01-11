package com.codeenemy.assignmentapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codeenemy.assignmentapp.data.datasources.CharactersPagingSource
import com.codeenemy.assignmentapp.models.Character
import com.codeenemy.assignmentapp.network.ApiService
import com.codeenemy.assignmentapp.network.SafeApiCall
import com.codeenemy.assignmentapp.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [CharactersRepository] is responsible for managing the data operations related to Star Wars characters.
 *
 * @param apiService The [ApiService] for making network requests.
 */
class CharactersRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall() {

    /**
     * Retrieves a paginated flow of Star Wars characters based on the provided search string.
     *
     * @param searchString The optional search string for filtering characters by name.
     * @return A [Flow] of [PagingData] containing Star Wars characters.
     */
    fun getCharacters(searchString: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService, searchString)
            }
        ).flow
    }

    /**
     * Retrieves detailed information about a Star Wars film using its URL.
     *
     * @param url The URL of the Star Wars film.
     * @return The film details as a result of a safe API call.
     */
    suspend fun getFilm(url: String) = safeApiCall {
        apiService.getFilm(url)
    }

    /**
     * Retrieves detailed information about a Star Wars planet using its URL.
     *
     * @param url The URL of the Star Wars planet.
     * @return The planet details as a result of a safe API call.
     */
    suspend fun getHomeWorld(url: String) = safeApiCall {
        apiService.getHomeWorld(url)
    }
}