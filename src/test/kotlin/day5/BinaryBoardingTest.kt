package day5

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BinaryBoardingTest {

    @Test
    fun getSeatIdTest() {
        val seatId = BinaryBoarding.getSeatId(Pair(44, 5))
        assertEquals(357, seatId)
    }

    @Test
    internal fun getColTest() {
        val col = BinaryBoarding.getCol("RLR")
        assertEquals(5, col)
    }

    @Test
    internal fun getRowTest() {
        val row = BinaryBoarding.getRow("FBFBBFF")
        assertEquals(44, row)
    }

    @Test
    internal fun getSeatTest() {
        val pair = BinaryBoarding.getSeat("FBFBBFFRLR")
        assertEquals(Pair(44, 5), pair)
    }

    @Test
    internal fun findHighestSeatIdTest() {
        val result = BinaryBoarding.findHighestSeatId(listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"))
        assertEquals(820, result)
    }

    @Test
    internal fun findMySeatTest() {
        val list = listOf(11, 12, 13, 15, 16)
        val result = BinaryBoarding.findMySeatFromOrderedList(list)
        assertEquals(14, result)
    }
}