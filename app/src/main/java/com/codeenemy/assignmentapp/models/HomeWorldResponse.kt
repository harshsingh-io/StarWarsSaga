package com.codeenemy.assignmentapp.models

import com.google.gson.annotations.SerializedName

/**
 * [HomeWorldResponse] is a data class representing a response from the Star Wars API for planet details.
 *
 * @property name The name of the planet (homeworld).
 */
data class HomeWorldResponse(
    @SerializedName("name")
    val name: String
)
