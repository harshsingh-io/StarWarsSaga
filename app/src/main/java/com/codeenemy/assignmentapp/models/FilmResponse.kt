package com.codeenemy.assignmentapp.models

import com.google.gson.annotations.SerializedName

/**
 * [FilmResponse] is a data class representing a response from the Star Wars API for film details.
 *
 * @property openingCrawl The opening crawl text of the film.
 * @property title The title of the film.
 */
data class FilmResponse(
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("title")
    val title: String
)
