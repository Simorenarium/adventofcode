package coffee.michel.adventofcode

import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class DayOne {

    companion object {
        private const val TARGET_SUM = 2020
    }

    fun solve(input: Set<Int>): Int {
        val computed = HashSet<Int>()
        for (number in input) {
            if (computed.contains(number))
                return (TARGET_SUM - number) * number
            else
                computed.add(TARGET_SUM - number)
        }
        return -1
    }

    fun solve_partTwo(input: Set<Int>): Int {
        for((i, firstNum) in input.withIndex()) {
            for((j, secondNum) in input.withIndex()) {
                if (i == j) continue
                if(input.contains(2020 - (firstNum + secondNum)))
                    return (2020 - firstNum - secondNum) * firstNum * secondNum
            }
        }

        return -1
    }

}

fun <T> timed(solve: () -> T, eval: (Long, T) -> Unit) {
    val start = System.currentTimeMillis()
    val res = solve()
    val time = System.currentTimeMillis() - start
    eval(time, res)
}

fun main() {
    val inp = DayOne::class.java.classLoader.getResourceAsStream("DayOne.input.txt")
    val inputs = HashSet<Int>()

    val isr = InputStreamReader(inp)
    isr.forEachLine { line -> inputs.add(Integer.valueOf(line)) }
    isr.close()

    timed({DayOne().solve(inputs)}, { time, res -> println("Solution Part One, List ($time ms): $res")})
    timed({DayOne().solve_partTwo(inputs)}, { time, res -> println("Solution Part Two, List ($time ms): $res")})
}