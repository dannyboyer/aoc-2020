package day2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PasswordPhilosophyTest {

    @Test
    internal fun isValid_Old_String() {
        val password1 = "1-3 a: abcde"
        val password2 = "1-3 b: cdefg"
        val password3 = "2-9 c: ccccccccc"

        val result1 = isValidOld(password1)
        val result2 = isValidOld(password2)
        val result3 = isValidOld(password3)

        assertEquals(true, result1)
        assertEquals(false, result2)
        assertEquals(true, result3)
    }

    @Test
    fun isValid_New_String() {
        val password1 = "1-3 a: abcde"
        val password2 = "1-3 b: cdefg"
        val password3 = "2-9 c: ccccccccc"

        val result1 = isValidNew(password1)
        val result2 = isValidNew(password2)
        val result3 = isValidNew(password3)

        assertEquals(true, result1)
        assertEquals(false, result2)
        assertEquals(false, result3)
    }
}