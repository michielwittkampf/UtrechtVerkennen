package com.example.utrechtontdekker.model

data class Location(
    val tag: String,
    val displayName: String,
    val type: LocationType,
    val descriptionHtml: String,
)
