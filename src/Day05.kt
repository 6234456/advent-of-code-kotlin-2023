fun main() {
    fun part1(input: List<String>): Long{
        val l1 = input.map { it.split("""\s+""".toRegex()).drop(1).map { x->x.trim().toInt() } }.let { it[0].zip(it[1]) }
        return l1.map { t -> (1 until t.first ).count { (t.first - it)*it > t.second } }.fold(1L){acc, i ->
            acc * i
        }
    }

    fun part2(input: List<String>): Long{
        val l = input.map { it.split(":")[1].replace("""\s+""".toRegex(), "").toLong() }
        val time = l.first()
        val distance = l[1]

        var lo = 0L
        var hi = time
        val m = (hi - lo) / 2 + lo

        fun qualified(mi:Long):Boolean = (time - mi) * mi > distance

        fun binarySearch(l:Long, h:Long, reversed:Boolean=false){
            val mi = (h - l) / 2 + l
            val q = qualified(mi)
            if (q){
                if (!reversed){
                    if (mi == 0L || !qualified(mi-1)){
                        lo = mi
                    }else{
                        binarySearch(l, mi-1, false)
                    }
                }else{
                    if (mi == time ||!qualified(mi + 1)){
                        hi = mi
                    }else{
                        binarySearch(mi+1, h, true)
                    }
                }
            }else{
                if (!reversed){
                    binarySearch(mi+1, h,false)
                }else{
                    binarySearch(l, mi-1, true)
                }
            }
        }

        binarySearch(0L, m, false)
        binarySearch(m+1, time, true)

        return hi -lo +1
    }



    val input = readInput("Input05")
    val test = readInput("Test05")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
