package com.codeenemy.assignmentapp.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codeenemy.assignmentapp.network.ApiService
import com.codeenemy.assignmentapp.utils.Constants.FIRST_PAGE_INDEX
import com.codeenemy.assignmentapp.models.Character
/**
 * [CharactersPagingSource] is a PagingSource responsible for loading paginated Star Wars characters data.
 *
 * @param apiService The [ApiService] for making network requests.
 * @param searchString The optional search string for filtering characters by name.
 */
class CharactersPagingSource(private val apiService: ApiService, private val searchString: String) :
    PagingSource<Int, Character>() {

    /**
     * Loads a page of characters data based on the given parameters.
     *
     * @param params The load parameters, including the page position.
     * @return A [LoadResult] containing the loaded data, next and previous keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            // Fetch characters data from the API
            val response = apiService.getCharacters(position)
            val characters = response.results

            // Filter characters based on the provided search string
            val filteredData = characters.filter { it.name.contains(searchString, true) }

            // Calculate next and previous keys
            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            // Handle errors and return an error result
            LoadResult.Error(e)
        }
    }

    /**
     * Gets the anchor position as the refresh key.
     *
     * @param state The current [PagingState].
     * @return The anchor position as the refresh key.
     */
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}