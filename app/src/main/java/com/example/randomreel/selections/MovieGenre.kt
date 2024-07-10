package com.example.randomreel.selections

enum class MovieGenre(val id: Int, val displayName: String) {
    ACTION(28, "Action"),
    ANIMATION(16, "Animation"),
    ANIME(105, "Anime"), // Example ID, replace with actual if needed
    COMEDY(35, "Comedy"),
    DOCUMENTARY(99, "Documentary"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    FANTASY(14, "Fantasy"),
    HORROR(27, "Horror"),
    MUSICAL(10402, "Musical"),
    ROMANCE(10749, "Romance"),
    SCIFI(878, "Sci-Fi"),
    WESTERN(37, "Western");

    override fun toString(): String {
        return displayName
    }
}
