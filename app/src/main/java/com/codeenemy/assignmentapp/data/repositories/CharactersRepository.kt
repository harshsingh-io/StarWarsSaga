package com.codeenemy.assignmentapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.codeenemy.assignmentapp.data.datasources.CharactersPagingSource
import com.codeenemy.assignmentapp.models.Character
import com.codeenemy.assignmentapp.network.ApiService
import com.codeenemy.assignmentapp.network.SafeApiCall
import com.codeenemy.assignmentapp.utils.Constants.NETWORK_PAGE_SIZE

class CharactersRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall() {

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

    suspend fun getFilm(url: String) = safeApiCall {
        apiService.getFilm(url)
    }

    suspend fun getHomeWorld(url: String) = safeApiCall {
        apiService.getHomeWorld(url)
    }
}