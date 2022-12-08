import java.util.stream.Collectors.*

fun main() {
    fun method(input: List<String>, stackFlow: Boolean): String {
        val chunkedStacks = input.takeWhile { it != "" }.map { it.chunked(4) }
        val indexToElement = chunkedStacks.dropLast(1).flatMap {
            it.mapIndexedNotNull { index, string ->
                if (string.isBlank()) null else index to string
            }
        }

        val indexToStack = indexToElement.stream()
            .collect(groupingBy({ it.first }, mapping(Pair<Int, String>::second, toList())))

        val numberRegex = Regex("\\d+")

        input.dropWhile { it.isNotEmpty() }.drop(1).map { numberRegex.findAll(it).toList().map(MatchResult::value) }
            .forEach {
                val (first, second, third) = it
                val let = indexToStack.getOrError(second.toInt() - 1).popFirst(first.toInt(), reversed = stackFlow)
                indexToStack.getOrError(third.toInt() - 1).addAll(0, let)
            }

        val bracketsRegex = Regex("(\\[|\\])")
        return indexToStack.map { it.value.first().trim() }.joinToString("").replace(bracketsRegex, "")
    }

    fun partOne(input: List<String>): String = method(input, stackFlow = true)
    fun partTwo(input: List<String>): String = method(input, stackFlow = false)

    val input = readInput("Day05_test")

    println(partOne(input))
    println(partTwo(input))
}

fun MutableList<String>.popFirst(n: Int = 1, reversed: Boolean): List<String> =
    (0 until n).fold<Int, MutableList<String>>(mutableListOf()) { total,
                                                                  _ ->
        total.add(this.removeFirst())
        total
    }.apply {
        if (reversed) return this.reversed()
    }


fun <K, V> Map<K, MutableList<V>>.getOrError(key: K): MutableList<V> {
    return getOrElse(key) { error("There is no such key in map") }
}