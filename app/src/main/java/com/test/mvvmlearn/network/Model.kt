package com.test.mvvmlearn.network

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "results")
    val results: List<Character>
)

data class Character(
    @Json(name = "name")
    val name: String,

    @Json(name = "image")
    val image: String
)