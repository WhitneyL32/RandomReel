package com.example.randomreel.selections

enum class ViewerRatings (val description: String, val minRating: Int, val maxRating: Int) {
    RANGE_01_15("01-15", 1, 15),
    RANGE_16_30("16-30", 16, 30),
    RANGE_31_45("31-45", 31, 45),
    RANGE_46_60("46-60", 46, 60),
    RANGE_61_75("61-75", 61, 75),
    RANGE_76_90("76-90", 76, 90),
    RANGE_91_100("91-100", 91, 100)
}