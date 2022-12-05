fun main() {
    fun partOne(input: List<String>): Int = input.count {
        val (first, second) = it.split(",").map(String::toSet)
        first.containsAll(second) || second.containsAll(first)
    }

    fun partTwo(input: List<String>): Int = input.count {
        val (first, second) = it.split(",").map(String::toSet)
        first.intersect(second).isNotEmpty()
    }


    val input = readInput("Day04")

    println(partOne(input))
    println(partTwo(input))
}

fun String.toSet(): Set<Int> = this.split("-").map(String::toInt).let { (it.first()..it.last()).toSet() }
