package coffee.michel.adventofcode

import java.io.InputStreamReader
import java.util.*

class DayTwo {

    fun solve(inputs: List<String>): Int {
        return inputs.filter { isInvalid(it) }
                .count()
    }

    private fun isInvalid(input: String): Boolean {
        val parts = input.split("\\s".toRegex())
        val count = parts[0]
        val char = parts[1].toCharArray()[0]
        val pw = parts[2]

        val allowedMinMax = count.split("-").map { Integer.valueOf(it) }

        val min = allowedMinMax[0]
        val max = allowedMinMax[1]
        return pw.count { it == char } in min..max
    }

    fun solve_partTwo(input: List<String>): Int {
        return input.filter { hasCharAtOccurrences(it) }
                .count()
    }

    private fun hasCharAtOccurrences(input: String) : Boolean{
        val parts = input.split("\\s".toRegex())
        val count = parts[0]
        val char = parts[1].codePointAt(0)
        val pw = parts[2]

        val expectedOccurrencesOfChar = count.split("-").map { Integer.valueOf(it) - 1 }

        return expectedOccurrencesOfChar.count { pw.codePointAt(it) == char } == 1
    }
}

fun main() {
    val inp = DayTwo::class.java.classLoader.getResourceAsStream("DayTwo.input.txt")
    val inputs = LinkedList<String>()

    val isr = InputStreamReader(inp)
    isr.forEachLine { line -> inputs.add(line) }
    isr.close()

    timed({DayTwo().solve(inputs)}, { time, res -> println("Solution Part One ($time ms): $res")})
    timed({DayTwo().solve_partTwo(inputs)}, { time, res -> println("Solution Part Two ($time ms): $res")})
}