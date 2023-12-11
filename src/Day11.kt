fun main() {
    fun part1(input: List<String>): Long {
        val s = input.map { it.split("").filter { it.isNotEmpty() } }.fold(mutableListOf<List<String>>()){
            acc, strings ->
            if (strings.all { it == "."}){
                acc.add(strings)
            }
            acc.add(strings)
            acc
        }.fold(mutableListOf<List<String>>()){
            acc, strings ->
            if (acc.isEmpty())
                strings.map { listOf(it) }.toMutableList()
            else
                acc.zip(strings).map { it.first + listOf(it.second) }.toMutableList()
        }.fold(mutableListOf<List<String>>()){
                acc, strings ->
            if (strings.all { it == "."}){
                acc.add(strings)
            }
            acc.add(strings)
            acc
        }.foldIndexed(listOf<Pair<Int,Int>>()){
            index, acc, strings ->
                strings.foldIndexed(acc) { index0, acc, string ->
                    if (string == "#") {
                        acc + Pair(index, index0)
                    } else {
                        acc
                    }
                }

        }


        var res = 0L
        s.indices.forEach {
            i ->
            (i+1 ..< s.size).forEach {
                j ->
                val v1 = s[i]
                val v2 = s[j]
               res += Math.abs(v1.first - v2.first) + Math.abs(v1.second - v2.second)
            }
        }

        return res
    }

    fun part2(input: List<String>): Long {
        val times = 1_000_000
        val s0 = input.map { it.split("").filter { it.isNotEmpty() } }
        val hGap = s0.foldIndexed(listOf<Long>()) {
                _, acc, strings ->
            if (strings.all { it == "." }){
                acc + listOf(( if (acc.isEmpty()) 0L else acc.last() ) + times -1)
            }else
                acc+ listOf(if (acc.isEmpty()) 0L else acc.last())
        }

        val vGap = s0.fold(mutableListOf<List<String>>()){
                acc, strings ->
            if (acc.isEmpty())
                strings.map { listOf(it) }.toMutableList()
            else
                acc.zip(strings).map { it.first + listOf(it.second) }.toMutableList()
        }.foldIndexed(listOf<Long>()) {
                _, acc, strings ->
            if (strings.all { it == "." }){
                acc + listOf(( if (acc.isEmpty()) 0L else acc.last() ) + times - 1)
            }else
                acc+ listOf(if (acc.isEmpty()) 0L else acc.last())
        }




        val s = s0.foldIndexed(listOf<Pair<Long,Long>>()){
                index, acc, strings ->
            strings.foldIndexed(acc) { index0, acc, string ->
                if (string == "#") {
                    acc + Pair(index+hGap[index], index0+vGap[index0])
                } else {
                    acc
                }
            }
        }


        var res = 0L
        s.indices.forEach {
                i ->
            (i+1 ..< s.size).forEach {
                    j ->
                val v1 = s[i]
                val v2 = s[j]
                res += Math.abs(v1.first - v2.first) + Math.abs(v1.second - v2.second)
            }
        }

        return res
    }

    val input = readInput("Input11")
    val test = readInput("Test11")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
