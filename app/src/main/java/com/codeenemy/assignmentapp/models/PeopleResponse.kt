package com.codeenemy.assignmentapp.models

import com.google.gson.annotations.SerializedName

/**
 * [PeopleResponse] is a data class representing a response from the Star Wars API for people (characters) details.
 *
 * @property count The total count of characters in the response.
 * @property next The URL for the next page of characters.
 * @property previous The URL for the previous page of characters.
 * @property results The list of characters in the response.
 */
data class PeopleResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Character>
)
