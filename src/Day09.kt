fun main() {
    fun part1(input: List<String>): Long {

        return input.map {
            var n = it.split("""\s+""".toRegex()).map { it.toLong() }
            val res = mutableListOf<Long>()
            while (n.any { it != 0L }){
                res.add(n.last())
                n = n.drop(1).zip(n.dropLast(1)).map { it.first - it.second }
            }

            res.foldRight(0L){l, acc ->
                acc + l
            }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.map {
            var n = it.split("""\s+""".toRegex()).map { it.toLong() }
            val res = mutableListOf<Long>()
            while (n.any { it != 0L }){
                res.add(n.first())
                n = n.drop(1).zip(n.dropLast(1)).map { it.first - it.second }
            }

            res.foldRight(0L){l, acc ->
                l - acc
            }
        }.sum()
    }

    val input = readInput("Input09")
    val test = readInput("Test09")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
