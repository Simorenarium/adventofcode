package coffee.michel.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DayTwoShould {

    private val dayTwo = DayTwo()

    @Test
    fun `detect not containing the sought character at at the given range`() {
        val input = listOf(
                "1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc"
        )

        val numberOfWrongPasswords = dayTwo.solve(input)

        assertThat(numberOfWrongPasswords).isEqualTo(1)
    }

    @Test
    fun `detect passwords containing the sought character at one of the given positions`() {
        val input = listOf(
                "1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc"
        )

        val numberOfValidPasswords = dayTwo.solve_partTwo(input)

        assertThat(numberOfValidPasswords).isEqualTo(1)
    }

}