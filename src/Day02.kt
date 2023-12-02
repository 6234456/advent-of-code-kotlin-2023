fun main() {
    val condition = mapOf("(\\d+)\\s+red".toRegex() to 12,"(\\d+)\\s+green".toRegex() to 13, "(\\d+)\\s+blue".toRegex() to 14)
    fun judgement(s: String): Boolean {
        return s.split(",").map { it.trim() }.all {
            condition.all {
                x ->
                if (x.key.matches(it)) x.value >= x.key.matchEntire(it)!!.groups[1]!!.value.toInt() else true
            }
        }
    }


    fun agg(x: String): Int {
        val m = listOf ("(\\d+)\\s+red".toRegex() ,"(\\d+)\\s+green".toRegex() , "(\\d+)\\s+blue".toRegex())
        val res = intArrayOf(0, 0, 0)
        x.split(";").forEach {
            s ->
            s.split(",").map { it.trim() } .forEach {
                m.forEachIndexed{
                    index, regex ->
                    if (regex.matches(it)){
                        res[index] = res[index].coerceAtLeast(regex.matchEntire(it)!!.groups[1]!!.value.toInt())
                    }
                }
            }
        }
        return res.filter { it > 0 }.fold(1){acc, i ->  acc * i}
    }

    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            if(s.split(":")[1].trim().split(";").all { judgement(it) }) index + 1 else 0
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map {
            s ->
            agg(s.split(":")[1].trim())
        }.sum()
    }


    val input = readInput("Input02")
    part1(input).println()
    part2(input).println()
}
