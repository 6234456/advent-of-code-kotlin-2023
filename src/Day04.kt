import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Long{
        return input.sumOf {
            val c = it.split(":")[1].split("|")
            val ans = c[1].trim().split("""\s+""".toRegex()).map { it.toInt() }.toHashSet()


            val times = c[0].trim().split("""\s+""".toRegex()).map { if (ans.contains(it.toInt())) 1 else 0 }.sum()
            if (times == 0) 0.0
            else 2.0.pow(times - 1)
        }.toLong()
    }

    fun part2(input: List<String>): Long {
        val ans:Array<Long> = Array(input.size) {1L}
        val coverage = input.map {
            val c = it.split(":")[1].split("|")
            val ans = c[1].trim().split("""\s+""".toRegex()).map { it.toInt() }.toHashSet()

            c[0].trim().split("""\s+""".toRegex()).map { if (ans.contains(it.toInt())) 1 else 0 }.sum()
        }

        ans.indices.forEach { index  ->
            val v = ans[index]
            val c = coverage[index]

            (index+1 .. index+c).forEach {
                if (it < ans.size)
                    ans[it] += 1L * v
            }
        }


        return ans.sum()
    }

    val input = readInput("Input04")
    val test = readInput("Test04")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
