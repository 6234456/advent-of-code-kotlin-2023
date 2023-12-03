fun main() {
    val reg = """\d""".toRegex()
    val regSym = """[^\d.]""".toRegex()
    fun part1(input: List<String>): Int {
        val x = input.first().length
        val y = input.size
        var ans = 0

        val s = input.map { it.toCharArray().map { it.toString() } }

        (0 until y).forEach {
            i ->
            var res = ""
            var flag = false
            (0 until  x).forEach {
                j ->
                val c = s[i][j]
                if (reg.matches(c)){
                    res += c

                    if (!flag){
                        if (
                            (i > 0 && regSym.matches(s[i-1][j])) ||
                            (i < y-1 && regSym.matches(s[i+1][j])) ||
                            (j > 0 && regSym.matches(s[i][j-1])) ||
                            (j < x-1 && regSym.matches(s[i][j+1])) ||
                            (j < x-1 && i < y-1 && regSym.matches(s[i+1][j+1])) ||
                            (j > 0 && i < y-1 && regSym.matches(s[i+1][j-1])) ||
                            (j > 0 && i > 0 && regSym.matches(s[i-1][j-1])) ||
                            (j < x-1 && i > 0 && regSym.matches(s[i-1][j+1]))
                            ){
                            flag = true
                        }
                    }

                    if (j == x -1){
                        ans += if (flag) res.toInt() else 0
                    }
                }else{
                    ans += if (flag) res.toInt() else 0
                    flag = false
                    res = ""
                }
            }
        }

        return ans
    }

    fun part2(input: List<String>): Long {
        val x = input.first().length
        val y = input.size
        var res = 0L

        val s = input.map { it.toCharArray().map { it.toString() } }


        (0 until y).forEach {
                i ->

            (0 until  x).forEach {
                    j ->
                val c = s[i][j]
                if (c == "*"){

                    val visited = Array<Array<Boolean>>(3){
                        Array<Boolean>(x){false}
                    }

                    var cnt = 0
                    var ans = 1L

                    fun paint(i0:Int, j:Int):Int{
                        val row = i0-i+1
                        if (i0 > -1 && j < x && j > -1 && i0 < y && !visited[row][j] && reg.matches(s[i0][j])){
                            visited[row][j] = true
                            paint(i0, j+1)
                            paint(i0, j-1)

                            return 1
                        }

                        return 0
                    }

                    fun parse(i0:Int, j:Int):Int{
                        val row = i0-i+1
                        var j0 = j

                        while (j0 > -1 && visited[row][j0]){
                            j0 -= 1
                        }

                        val start = j0+1

                        j0 = j

                        while (j0 < x && visited[row][j0]){
                            j0 += 1
                        }
                        val end = j0-1
                        return input[i0].slice(start .. end).toInt()
                    }


                    if (i > 0 && reg.matches(s[i-1][j])){
                        cnt += paint(i-1, j).apply {
                            if (this == 1)
                                ans *= parse(i-1, j)
                        }
                    }

                    if(i < y-1 && reg.matches(s[i+1][j])){
                        cnt += paint(1+i, j).apply {
                            if (this == 1)
                                ans *= parse(i+1, j)
                        }
                    }
                    if(j > 0 && reg.matches(s[i][j-1])){
                        cnt += paint(i, j-1).apply {
                            if (this == 1)
                                ans *= parse(i, j-1)
                        }
                    }
                    if(j < x-1 && reg.matches(s[i][j+1])){
                        cnt += paint(i, j+1).apply {
                            if (this == 1)
                                ans *= parse(i, j+1)
                        }
                    }
                    if(j < x-1 && i < y-1 && reg.matches(s[i+1][j+1])){
                        cnt += paint(i+1, j+1).apply {
                            if (this == 1)
                                ans *= parse(i+1, j+1)
                        }
                    }

                    if(j > 0 && i < y-1 && reg.matches(s[i+1][j-1])){
                        cnt += paint(i+1, j-1).apply {
                            if (this == 1)
                                ans *= parse(i+1, j-1)
                        }
                    }
                    if(j > 0 && i > 0 && reg.matches(s[i-1][j-1])){
                        cnt += paint(i-1, j-1).apply {
                            if (this == 1)
                                ans *= parse(i-1, j-1)
                        }
                    }
                    if(j < x-1 && i > 0 && reg.matches(s[i-1][j+1])){
                        cnt += paint(i-1, j+1).apply {
                            if (this == 1)
                                ans *= parse(i-1, j+1)
                        }
                    }


                    res += if (cnt == 2) ans else 0

                }
            }
        }

        return res
    }

    val input = readInput("Input03")
    val test = readInput("Test03")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
