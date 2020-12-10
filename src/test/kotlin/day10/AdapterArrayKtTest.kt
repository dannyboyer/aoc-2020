package day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AdapterArrayKtTest {

    @Test
    fun getJoltDistributions() {
        val distributions = AdapterArray.getJoltDistributions(0, listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4).sorted(), mutableMapOf())
        assertEquals(2, distributions.size)
        val magicNumber = AdapterArray.getMagicNumber(distributions)
        assertEquals(35, magicNumber)
    }

    @Test
    fun getJoltDistributions_alt() {
        val distributions = AdapterArray.getJoltDistributions(0, listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3).sorted(), mutableMapOf())
        assertEquals(2, distributions.size)
        val magicNumber = AdapterArray.getMagicNumber(distributions)
        assertEquals(220, magicNumber)
    }
}