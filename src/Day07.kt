fun main() {


    fun part1(input: List<String>): Long {

        val orderMap = "AKQJT98765432".split("").mapIndexed { index, s ->  s to index }.toMap()
        fun secondOrder(s1:String, s2:String):Int{
            s1.zip(s2).forEach {
                if (it.first != it.second){
                    return orderMap[it.second.toString()]!! - orderMap[it.first.toString()]!!
                }
            }
            return 0
        }
        fun firstOrder(s:String):Int{
            val frequency = s.trim().split("").groupBy { it }.mapValues { it.value.size }.filterKeys { it.isNotEmpty() }
            if (frequency.size == 1) return 7

            val values = frequency.values
            val minValue = values.min()
            if (frequency.size == 2 && minValue == 1) return 6
            if (frequency.size == 2 && minValue == 2) return 5

            val maxValue = values.max()
            if (maxValue == 3 && minValue == 1) return 4
            if (maxValue == 2 && minValue == 1 ){
                if (frequency.size ==3)
                    return 3

                return 2
            }

            return 1
        }


        return input.map { val v = it.split(" ");Triple(v[0], firstOrder(v[0]), v[1].toLong()) }.sortedWith{ a, b ->
            val v = a.second - b.second
            if (v != 0)
                v
            else
                secondOrder(a.first, b.first)
        }.mapIndexed { index, triple ->  triple.third * (index + 1) }.sum()
    }

    fun part2(input: List<String>): Long {
        val orderMap = "AKQT98765432J".split("").mapIndexed { index, s ->  s to index }.toMap()
        fun secondOrder(s1:String, s2:String):Int{
            s1.zip(s2).forEach {
                if (it.first != it.second){
                    return orderMap[it.second.toString()]!! - orderMap[it.first.toString()]!!
                }
            }
            return 0
        }
        fun firstOrder(s:String):Int{
            val frequency = s.trim().split("").groupBy { it }.mapValues { it.value.size }.filterKeys { it.isNotEmpty() }.toMutableMap()

            if (frequency.size == 1) return 7
            if (frequency.contains("J")){
                val k = frequency.filterKeys { it != "J" }.entries.maxBy { it.value }.key
                val cnt = frequency["J"]!!
                frequency.remove("J")
                frequency[k] = frequency[k]!! + cnt
            }

            if (frequency.size == 1) return 7
            val values = frequency.values
            val minValue = values.min()
            if (frequency.size == 2 && minValue == 1) return 6
            if (frequency.size == 2 && minValue == 2) return 5

            val maxValue = values.max()
            if (maxValue == 3 && minValue == 1) return 4
            if (maxValue == 2 && minValue == 1 ){
                if (frequency.size ==3)
                    return 3

                return 2
            }

            return 1
        }
        return input.map { val v = it.split(" ");Triple(v[0], firstOrder(v[0]), v[1].toLong()) }.sortedWith{ a, b ->
            val v = a.second - b.second
            if (v != 0)
                v
            else
                secondOrder(a.first, b.first)
        }.apply { this.println() }.mapIndexed { index, triple ->  triple.third * (index + 1) }.sum()
    }

    val input = readInput("Input07")
    val test = readInput("Test07")

    println("-- Test --")
    part1(test).println()
    part2(test).println()

    println("-- Input --")
    part1(input).println()
    part2(input).println()
}
