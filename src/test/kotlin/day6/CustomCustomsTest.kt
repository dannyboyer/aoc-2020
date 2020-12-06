package day6

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CustomCustomsTest {
    val lines = listOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b"
    )
    val groupLines = listOf("abcx", "abcy", "abcz")

    @Test
    fun getGroupSum() {
        val result = CustomCustoms.getGroupSum(groupLines)
        assertEquals(6, result)
    }

    @Test
    internal fun getGroupSumFixed() {
        val result = CustomCustoms.getGroupSumFixed(groupLines)
        assertEquals(3, result)
    }

    @Test
    internal fun sumCountsTest() {
        val result = CustomCustoms.sumCounts(
            lines, 0)
        assertEquals(11, result)
    }

    @Test
    internal fun sumCountsFixedTest() {
        val result = CustomCustoms.sumCountsFixed(
            lines, 0)
        assertEquals(6, result)
    }
}