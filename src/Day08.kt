fun main() {
    fun part1(input: List<String>): Long {
       var visited = mutableSetOf<String>()

        val path = input.first().trim().split("").filter { it.isNotEmpty() }.map { if (it=="R") 1 else 0 }
        val nodes = input.drop(2).map {
            val s = it.split(" = ")
            s.first().trim() to s[1].slice(1 .. s[1].length-2).split(", ") .map { it.trim() }
        }.toMap()

        var target = "AAA"

        fun dfs(target:String,visited:MutableSet<String>,steps:Int = 0):Int{
            if (target == "ZZZ")
                return steps

            if (visited.contains(target))
                return -1

            visited.add(target)

            val l = dfs(nodes[target]!![0], visited, steps+1)
            val r = dfs(nodes[target]!![1], visited, steps+1)
            if (l > 0 && r > 0) return Math.min(l,r)
            if (l > 0) return l
            return r
        }

        var step = 0
        path.forEach {
            if (target == "ZZZ")
                return@forEach

            if (visited.contains(target)){
                step = -1
                return@forEach
            }

            visited.add(target)
            target = nodes[target]!![it]
            step = step + 1
        }

        if (step > 0) return step.toLong()

        step = 0
        visited = mutableSetOf()
        target = "AAA"
        var index = 0
        while (target != "ZZZ") {
            target = nodes[target]!![path[index]]
            step = step + 1
            index = if (step >= path.size) step.rem(path.size) else step
        }
        return step.toLong()


        //return dfs("AAA", visited).toLong()
    }

    fun part2(input: List<String>): Long {
        val path = input.first().trim().split("").filter { it.isNotEmpty() }.map { if (it=="R") 1 else 0 }
        val nodes = input.drop(2).map {
            val s = it.split(" = ")
            s.first().trim() to s[1].slice(1 .. s[1].length-2).split(", ") .map { it.trim() }
        }.toMap()

        fun lcm(n1: Long, n2:Long):Long{
           val ma = if (n1 < n2) n2 else n1
           val mi = n1 + n2 - ma
           var res = ma
           while (res.rem(mi) != 0L){
              res += ma
           }
            return res
        }

     return  nodes.keys.filter { it.endsWith("A") }.map {
            var step = 0
            var target = it
            var index = 0
            while (!target.endsWith("Z")) {
                target = nodes[target]!![path[index]]
                step += 1
                index = if (step >= path.size) step.rem(path.size) else step
            }
            return@map step.toLong()
        }.reduce { acc, i ->  lcm(acc, i) }

    }

    val input = readInput("Input08")
    val test = readInput("Test08")

    println("-- Test --")
  //  part1(test).println()
    part2(test).println()

    println("-- Input --")
   // part1(input).println()
    part2(input).println()
}
