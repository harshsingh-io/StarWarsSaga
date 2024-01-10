package com.codeenemy.assignmentapp.network

import com.codeenemy.assignmentapp.models.FilmResponse
import com.codeenemy.assignmentapp.models.HomeWorldResponse
import com.codeenemy.assignmentapp.models.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {

    @GET("people/?page/")
    suspend fun getCharacters(@Query("page") page: Int): PeopleResponse

    @GET
    suspend fun getFilm(@Url url: String): FilmResponse

    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
}