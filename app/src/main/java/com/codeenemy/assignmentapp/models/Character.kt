package com.codeenemy.assignmentapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

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
    ) {
    }

    companion object : Parceler<Character> {

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

        override fun create(parcel: Parcel): Character {
            return Character(parcel)
        }
    }
}