package com.codeenemy.assignmentapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
/**
 * [Character] is a data class representing a Star Wars character.
 *
 * @property birthYear The birth year of the character.
 * @property eyeColor The eye color of the character.
 * @property films The list of films in which the character appears.
 * @property gender The gender of the character.
 * @property hairColor The hair color of the character.
 * @property height The height of the character.
 * @property homeworld The homeworld of the character.
 * @property mass The mass of the character.
 * @property name The name of the character.
 * @property skinColor The skin color of the character.
 */
@Parcelize
data class Character(
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("homeworld")
    val homeworld: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String
) : Parcelable {
    /**
     * Constructor for creating a [Character] instance from a [Parcel].
     */
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    /**
     * [Parceler] implementation for efficient parcelization.
     */
    companion object : Parceler<Character> {

        /**
         * Writes the [Character] data to a [Parcel].
         */
        override fun Character.write(parcel: Parcel, flags: Int) {
            parcel.writeString(birthYear)
            parcel.writeString(eyeColor)
            parcel.writeStringList(films)
            parcel.writeString(gender)
            parcel.writeString(hairColor)
            parcel.writeString(height)
            parcel.writeString(homeworld)
            parcel.writeString(mass)
            parcel.writeString(name)
            parcel.writeString(skinColor)
        }

        /**
         * Creates a [Character] instance from a [Parcel].
         */
        override fun create(parcel: Parcel): Character {
            return Character(parcel)
        }
    }
}
