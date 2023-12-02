fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.replace("""\D""".toRegex(), "") }.fold(0){
            acc, s ->
                acc + if (s.length == 1) s.repeat(2).toInt() else (s[0].toString() + s.last()).toInt()
        }

    }

    val reg =  """\d""".toRegex()
    val check = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7,
        "eight" to 8, "nine" to 9)

    fun parseString(string: String, start: Int=0):String {
        var res:String = ""
        if (start < string.length){
            if (reg.matches(string.slice(start..start))){
                res = res + string.slice(start..start) + parseString(string, start + 1)
            } else{
                val l = string.length - start
                var flag = true
                check.forEach {
                    val (tar, value) = it
                    if (tar.length <= l && string.slice(start..start+tar.length-1) == tar){
                        flag = false
                        res += value.toString() + parseString(string, start + tar.length)
                        return@forEach
                    }
                }
                if (flag)
                    res += parseString(string, start + 1)
            }
        }

        return res
    }

    fun parse(string: String, start: Int=0, forward:Boolean = true):Int {
        if (start < string.length && start > -1){
            if (reg.matches(string.slice(start..start))){
                return string.slice(start..start).toInt()
            } else{
                val l = string.length - start
                var flag = true
                check.forEach {
                    val (tar, value) = it
                    if (tar.length <= l && string.slice(start..start+tar.length-1) == tar){
                        return value
                    }
                }
                return parse(string, start + if (forward) 1 else -1, forward)
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            val v1 = parse(s)
            val v2 = parse(s, s.length-1, false)
            if (v1 < 0 || v2 < 0) throw Exception("$s at $index")
            v1 * 10 + v2
        }.fold(0){
              acc, i ->
            acc + i
        }
    }


    val input = readInput("Input01")
    //part1(input).println()
    part2(input).println()
}
