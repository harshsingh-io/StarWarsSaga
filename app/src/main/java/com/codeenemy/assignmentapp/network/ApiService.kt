package com.codeenemy.assignmentapp.network

import com.codeenemy.assignmentapp.models.FilmResponse
import com.codeenemy.assignmentapp.models.HomeWorldResponse
import com.codeenemy.assignmentapp.models.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url



/**
 * [ApiService] is an interface defining the Star Wars API endpoints for network requests.
 */
interface ApiService {

    /**
     * Retrieves a page of Star Wars characters from the API.
     *
     * @param page The page number.
     * @return A [PeopleResponse] containing character details.
     */
    @GET("people/?page/")
    suspend fun getCharacters(@Query("page") page: Int): PeopleResponse

    /**
     * Retrieves details about a specific Star Wars film.
     *
     * @param url The URL of the film.
     * @return A [FilmResponse] containing film details.
     */
    @GET
    suspend fun getFilm(@Url url: String): FilmResponse

    /**
     * Retrieves details about a specific Star Wars planet (homeworld).
     *
     * @param url The URL of the planet.
     * @return A [HomeWorldResponse] containing planet details.
     */
    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
}