package selections

enum class Length (val range: String, val minMinutes: Int, val maxMinutes: Int) {
    SHORT("Short", 0, 90),
    MEDIUM("Medium", 91, 180),
    LONG("Long", 181, 350)
}