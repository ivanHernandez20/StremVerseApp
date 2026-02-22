package com.example.streamverse.Model

import java.io.Serializable

data class Movie(
    val id: String,
    val title: String,
    val year: Int,
    val rating: Double,
    val category: String,
    val description: String,
    val shortDescription: String,
    val image: String,
    val heroImage: String,
    val trailerUrl: String,
    val duration: String,
    val director: String,
    val cast: List<String>,
    val featured: Boolean
) : Serializable
