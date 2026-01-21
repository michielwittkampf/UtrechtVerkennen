package com.example.utrechtverkennen.model

data class Location(
    val tag: String,
    val displayName: String,
    val type: LocationCategory,
    val descriptionHtml: String,
)
