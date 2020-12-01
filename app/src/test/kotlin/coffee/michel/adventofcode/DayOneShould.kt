package coffee.michel.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DayOneShould {

    private val dayOne= DayOne()

    @Test
    fun `multiply the two entries which sum up to 2020`() {
        val numbers = setOf(1721, 979, 366, 299, 675, 1456)

        val result = dayOne.solve(numbers)

        assertThat(result).isEqualTo(514579)
    }

    @Test
    fun `multiply the three entries which sum up to 2020`() {
        val numbers = setOf(1721, 979, 366, 299, 675, 1456)

        val result = dayOne.solve_partTwo(numbers)

        assertThat(result).isEqualTo(241861950)
    }
}