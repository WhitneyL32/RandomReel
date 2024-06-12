package com.example.randomreel.selections

enum class MovieRating (val code: String, val description: String) {
    G("G", "General Audiences"),
    PG("PG", "Parental Guidance Suggested"),
    PG_13("PG-13", "Parents Strongly Cautioned"),
    R("R", "Restricted"),
    NC_17("NC-17", "Adults Only")
}