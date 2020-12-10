package day9

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EncodingErrorTest {

    @Test
    fun isValid() {
        val numberList: List<Long> = (1..25).toList().map { it.toLong() }
        assertTrue(EncodingError.isValid(26, numberList))
        assertTrue(EncodingError.isValid(49, numberList))
        assertFalse(EncodingError.isValid(100, numberList))
        assertFalse(EncodingError.isValid(50, numberList))
    }

    @Test
    fun isValid_20atPost0() {
        val baseList = (1..25).toMutableList()
        baseList.add(45)
        baseList.remove(20)
        val numberList :List<Long> = baseList.toList().map { it.toLong() }

        assertTrue(EncodingError.isValid(26, numberList))
        assertFalse(EncodingError.isValid(65, numberList))
        assertTrue(EncodingError.isValid(64, numberList))
        assertTrue(EncodingError.isValid(66, numberList))
    }

    @Test
    internal fun findInvalid() {
        val numberList = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }
        val invalidNum = EncodingError.findInvalidNum(
            numberList, 5
        )
        assertEquals(127, invalidNum)
    }

    @Test
    internal fun isWeakness() {
        assertTrue(EncodingError.isWeakness(127, listOf(15, 25, 47, 40)))
    }

    @Test
    internal fun findWeakness() {
        val numberList = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }
        assertEquals(62, EncodingError.findWeakness(127, numberList))
    }
}