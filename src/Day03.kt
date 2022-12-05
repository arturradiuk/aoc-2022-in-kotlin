fun findRepeatedChar(first: String, second: String): Int {
    first.toCharArray().map { char ->
        if (second.find { it == char } != null) return char.mapToPoints()
    }
    throw Error()
}

fun findRepeatedChar(first: String, second: String, third: String): Int {
    first.toCharArray().map { char ->
        val foundS = second.find { it == char }
        val thirdT = third.find { it == char }

        if (foundS == thirdT && foundS == char) {
            return char.mapToPoints()
        }
    }
    throw Error()
}


private fun Char.mapToPoints(): Int = when (this) {
    in 'a'..'z' -> this.code - 96
    in 'A'..'Z' -> this.code - 38
    else -> throw Error()
}


fun partOne(rucksacks: List<String>): Int = rucksacks.sumOf {
    val (first, second) = it.chunked(it.length / 2)
    findRepeatedChar(first, second)
}

fun partTwo(rucksacks: List<String>): Int = rucksacks.windowed(3, 3).sumOf {
    val (first, second, third) = it
    findRepeatedChar(first, second, third)
}

fun main() {

    val rucksacks = readInput("Day03")

    println(partOne(rucksacks))
    println(partTwo(rucksacks))

}