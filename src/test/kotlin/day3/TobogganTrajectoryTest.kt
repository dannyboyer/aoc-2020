package day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TobogganTrajectoryTest {

    /**
     * starting at 0, 0
     * for this map:
     * ..##.......
     * #...#...#..
     * travel of 3, 1
     * should encounter 2 trees
     */
    @Test
    fun countTreesEncounter_small() {
        val result = TobogganTrajectory.countTreesEncounter(listOf("..##.......", "#...#...#.."), 3, 1)
        assertEquals(0, result)
    }

    /**
     * starting at 0, 0
     * for this map:
     * ..##.......
     * #...#...#..
     * travel of 3, 1
     * should encounter 2 trees
     */
    @Test
    fun countTreesEncounter_medium() {
        val result = TobogganTrajectory.countTreesEncounter(
            listOf(
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
                ".#..#...#.#"),
        3, 1)
        assertEquals(7, result)
    }

    @Test
    fun countTreesEncounter_multiply() {
        val pairs = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        val result = TobogganTrajectory.multiplyEncounter(pairs,
            listOf(
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
                ".#..#...#.#"))
        assertEquals(336, result)
    }
}