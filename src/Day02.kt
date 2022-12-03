fun calculateScore(round: List<Char>): Int = when (round.last() - round.first()) {
    21, 24 -> 6 + round.last().code - 87
    9, 23 -> 3 + round.last().code - 87
    else -> round.last().code - 87
}

fun calculateScoreWithInstruction(round: List<Char>): Int = when (round.last()) {
    'X' -> (round.first().code).mod(3) + 1 // lose
    'Y' -> 3 + (round.first().code + 1).mod(3) + 1 // draw
    'Z' -> 6 + 1 + (round.first().code + 2).mod(3) // win
    else -> throw Exception()
}

fun main() {
    fun part1(input: String): Int = input
        .lines()
        .flatMap {
            it.split(" ")
        }.map(String::first)
        .windowed(size = 2, step = 2)
        .map(::calculateScore)
        .sum()

    fun part2(input: String): Int = input
        .lines()
        .flatMap {
            it.split(" ")
        }.map(String::first)
        .windowed(size = 2, step = 2)
        .map(::calculateScoreWithInstruction)
        .sum()


    val input = readInputToString("Day02")
    println(part1(input))
    println(part2(input))

}
