fun main() {
    fun findMarkerPosition(stream: String, distinctNumbs: Int): Int {
        stream.windowed(distinctNumbs).forEachIndexed { index, value ->
            if (value.toCharArray().distinct().size === distinctNumbs) {
                return index + distinctNumbs
            }
        }
        error("There is no marker in input")
    }

    fun partOne(input: String) = findMarkerPosition(input, 4)

    fun partTwo(input: String) = findMarkerPosition(input, 14)

    val input = readInputToString("Day06")
    println(partOne(input))
    println(partTwo(input))
}

