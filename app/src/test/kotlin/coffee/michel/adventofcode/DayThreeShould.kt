package coffee.michel.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DayThreeShould {

    companion object {
        private val INPUT = listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
        )
    }

    private val dayThree = DayThree()

    @Test
    fun `count the number of trees passed`() {

        assertThat(dayThree.solve(INPUT)).isEqualTo(7)

    }

}