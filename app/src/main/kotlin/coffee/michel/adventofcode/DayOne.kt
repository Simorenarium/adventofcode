package coffee.michel.adventofcode

import java.io.FileReader
import java.io.InputStreamReader
import java.util.*

class DayOne {

    companion object {
        private const val TARGET_SUM = 2020
    }

    fun solve(input: List<Int>): Int {
        for(number in input) {
            val possibleResult = TARGET_SUM - number
            if(input.contains(possibleResult))
                return number * possibleResult
        }
        return -1
    }

    fun solve_partTwo(input: List<Int>): Int {
        for((i, firstNum) in input.withIndex()) {
            for((j, secondNum) in input.withIndex()) {
                if (i == j) continue
                val sumOfFirstTwo = firstNum + secondNum
                if(sumOfFirstTwo > TARGET_SUM)
                    continue

                val possibleThird = TARGET_SUM - sumOfFirstTwo
                if(input.contains(possibleThird))
                    return possibleThird * firstNum * secondNum
            }
        }

        return -1
    }

}

fun main() {
    val inp = DayOne::class.java.classLoader.getResourceAsStream("DayOne.input.txt")
    val inputs = LinkedList<Int>()

    val isr = InputStreamReader(inp)
    isr.forEachLine { line -> inputs.add(Integer.valueOf(line)) }
    isr.close()

    println("Solution Part One: " + DayOne().solve(inputs))
    println("Solution Part Two: " + DayOne().solve_partTwo(inputs))
}