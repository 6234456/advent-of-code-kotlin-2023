import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Long {
        val arr = input.map { it.split("").filter { it.isNotEmpty() }.toTypedArray() }.toTypedArray()
        val visited = Array<Array<Boolean>>(arr.size){
            Array<Boolean>(arr.first().size){false}
        }

        fun getStartPoint():Pair<Int, Int>{
            visited.indices.forEach{
                    i ->
                visited.first().indices.forEach{
                        j ->
                    if (arr[i][j] == "S")
                        return i to j
                }
            }
            return -1 to -1
        }

        val s = getStartPoint()

        val w = visited.first().size
        val h = visited.size

        visited[s.first][s.second] = true
        val res:MutableList<MutableMap<Pair<Int, Int>, Int>> = mutableListOf()

        val x = s.second
        val y = s.first


        fun process0(x0:Int, y0:Int, visited:MutableMap<Pair<Int, Int>, Int>, lastVisited0:Pair<Int, Int>):MutableMap<Pair<Int, Int>, Int>{
            var x = x0
            var y = y0
            var lastVisited = lastVisited0

            fun left(){
                lastVisited = x to y
                x -= 1
                visited[x to y] = visited[x+1 to y]!! + 1
            }

            fun right(){
                lastVisited = x to y
                x += 1
                visited[x to y] = visited[x-1 to y]!! + 1
            }

            fun up(){
                lastVisited = x to y
                y -= 1
                visited[x to y] = visited[x to y+1]!! + 1
            }

            fun down(){
                lastVisited = x to y
                y += 1
                visited[x to y] = visited[x to y-1]!! + 1
            }

            while (arr[y][x] != "S"){
                when(arr[y][x]){
                   "-" -> if (x+1 to y != lastVisited) right() else left()
                    "|" -> if (x to y+1  != lastVisited) down() else up()
                    "F" -> if (x to y+1  != lastVisited) down() else right()
                    "7" -> if (x to y+1  != lastVisited) down() else left()
                    "J" -> if (x to y-1  != lastVisited) up() else left()
                    "L" -> if (x to y-1  != lastVisited) up() else right()
                }
            }

            return visited

        }

        if (x > 0 && (arr[y][x-1] == "L" || arr[y][x-1] == "-"|| arr[y][x-1] == "F")) res.add(process0(x-1, y, mutableMapOf((x to y) to 0, (x-1 to y) to 1), x to y))
        if (y > 0 && (arr[y-1][x] == "7" || arr[y-1][x] == "|"|| arr[y-1][x] == "F")) res.add(process0(x, y-1, mutableMapOf((x to y) to 0, (x to y-1) to 1), x to y))
        if (y < h - 1 && (arr[y+1][x] == "J" || arr[y+1][x] == "|"|| arr[y+1][x] == "L")) res.add(process0(x, y+1, mutableMapOf((x to y) to 0, (x to y+1) to 1), x to y))
        if (x < w - 1 && (arr[y][x+1] == "J" || arr[y][x+1] == "-"|| arr[y][x+1] == "7")) res.add(process0(x+1, y, mutableMapOf((x to y) to 0, (x+1 to y) to 1), x to y  ))

        return res.first().keys.filter { it != s.second to s.first }.map { min(res[0][it]!!, res[1][it]!!) }.maxBy { it }.toLong()
    }

    fun part2(input: List<String>): Long {

        val arr = input.map { it.split("").filter { it.isNotEmpty() }.toTypedArray() }.toTypedArray()
        val visited = Array<Array<Boolean>>(arr.size){
            Array<Boolean>(arr.first().size){false}
        }

        fun getStartPoint():Pair<Int, Int>{
            visited.indices.forEach{
                    i ->
                visited.first().indices.forEach{
                        j ->
                    if (arr[i][j] == "S")
                        return i to j
                }
            }
            return -1 to -1
        }

        val s = getStartPoint()

        val w = visited.first().size
        val h = visited.size

        visited[s.first][s.second] = true
        val res:MutableList<MutableMap<Pair<Int, Int>, Int>> = mutableListOf()

        val x = s.second
        val y = s.first


        fun process0(x0:Int, y0:Int, visited:MutableMap<Pair<Int, Int>, Int>, lastVisited0:Pair<Int, Int>):MutableMap<Pair<Int, Int>, Int>{
            var x = x0
            var y = y0
            var lastVisited = lastVisited0

            fun left(){
                lastVisited = x to y
                x -= 1
                visited[x to y] = visited[x+1 to y]!! + 1
            }

            fun right(){
                lastVisited = x to y
                x += 1
                visited[x to y] = visited[x-1 to y]!! + 1
            }

            fun up(){
                lastVisited = x to y
                y -= 1
                visited[x to y] = visited[x to y+1]!! + 1
            }

            fun down(){
                lastVisited = x to y
                y += 1
                visited[x to y] = visited[x to y-1]!! + 1
            }

            while (arr[y][x] != "S"){
                when(arr[y][x]){
                    "-" -> if (x+1 to y != lastVisited) right() else left()
                    "|" -> if (x to y+1  != lastVisited) down() else up()
                    "F" -> if (x to y+1  != lastVisited) down() else right()
                    "7" -> if (x to y+1  != lastVisited) down() else left()
                    "J" -> if (x to y-1  != lastVisited) up() else left()
                    "L" -> if (x to y-1  != lastVisited) up() else right()
                }
            }

            return visited

        }

        if (x > 0 && (arr[y][x-1] == "L" || arr[y][x-1] == "-"|| arr[y][x-1] == "F")) res.add(process0(x-1, y, mutableMapOf((x to y) to 0, (x-1 to y) to 1), x to y))
        else if (y > 0 && (arr[y-1][x] == "7" || arr[y-1][x] == "|"|| arr[y-1][x] == "F")) res.add(process0(x, y-1, mutableMapOf((x to y) to 0, (x to y-1) to 1), x to y))
        else if (y < h - 1 && (arr[y+1][x] == "J" || arr[y+1][x] == "|"|| arr[y+1][x] == "L")) res.add(process0(x, y+1, mutableMapOf((x to y) to 0, (x to y+1) to 1), x to y))
        else if (x < w - 1 && (arr[y][x+1] == "J" || arr[y][x+1] == "-"|| arr[y][x+1] == "7")) res.add(process0(x+1, y, mutableMapOf((x to y) to 0, (x+1 to y) to 1), x to y  ))

        val path = res.first().keys.sortedBy { it.first }
        return path.groupBy { it.second }.map {
            val r = it.key
            val v = it.value.sortedBy { it.first}.map { it.first}
            var cnt = 0
            if (v.last() - v.first() + 1 > v.size){
                var wall = 0
                (v.first() .. v.last()).forEach {
                    if (v.contains(it)){
                        val s0 = arr[r][it]
                        // enclosed = through odd number of walls
                        if (s0 == "|" || s0 == "L"|| s0 == "J") wall += 1
                    }else{
                        cnt += if (wall.rem(2) ==0) 0 else 1
                    }
                }
            }
            cnt
        }.apply { this.println() }.sum().toLong()
    }

    val input = readInput("Input10")
    val test = readInput("Test10")

    println("-- Test --")
   // part1(test).println()
    part2(test).println()

    println("-- Input --")
    // part1(input).println()
    part2(input).println()
}
