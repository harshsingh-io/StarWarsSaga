package com.codeenemy.assignmentapp.network

import com.codeenemy.assignmentapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * [SafeApiCall] is a class providing a safe wrapper for making API calls.
 */
open class SafeApiCall {

    /**
     * Executes the provided API call in a safe manner, handling exceptions and returning a [Resource] result.
     *
     * @param apiCall The suspend function representing the API call.
     * @return A [Resource] representing the result of the API call.
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(throwable.response()?.errorBody().toString(), null)
                    }
                    else -> {
                        Resource.Failure(throwable.localizedMessage, null)
                    }
                }
            }
        }
    }
}
