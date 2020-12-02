package day2

import day1.ReportRepair
import java.io.InputStream

interface PasswordValidator {
    fun isValid(password: String): Boolean
}

object OldPasswordValidation : PasswordValidator {
    private val operation: (index1: Int, index2: Int, letter: Char, pwd: String) -> Boolean = { min, max, letter, pwd ->
        var count = 0
        for (c in pwd) {
            if (c == letter) count++
        }
        count in min..max
    }

    override fun isValid(password: String): Boolean {
        return extractAndCall(password, operation)
    }
}

object NewPasswordValidation : PasswordValidator {
    private val operation: (index1: Int, index2: Int, letter: Char, pwd: String) -> Boolean = { index1, index2, letter, pwd ->
        var count = 0
        if (letter == pwd.get(index1 - 1)) count++
        if (letter == pwd.get(index2 - 1)) count++
        count == 1;
    }

    override fun isValid(password: String): Boolean {
        return extractAndCall(password, operation)
    }
}

private fun extractAndCall(password: String, operation: (Int, Int, Char, String) -> Boolean): Boolean {
    val letter = password.substringAfter(' ').substringBefore(':')[0]
    val pwd = password.substringAfter(": ")
    val min = password.substringBefore('-').toInt()
    val max = password.substringAfter('-').substringBefore(' ').toInt()
    return operation(min, max, letter, pwd);
}

object Problem2 : PasswordValidator by OldPasswordValidation {
    private val validator: (String) -> Boolean = this::isValid
    fun solve(passwordList: List<String>): Int {
        return countValidPasswordInList(passwordList, validator)
    }
}

object Problem1 : PasswordValidator by NewPasswordValidation {
    private val validator: (String) -> Boolean = this::isValid
    fun solve(passwordList: List<String>): Int {
        return countValidPasswordInList(passwordList, validator)
    }
}

private fun countValidPasswordInList(passwordList: List<String>, isValid: (String) -> Boolean): Int {
    var count = 0
    for (pwd in passwordList) {
        if (isValid(pwd)) count++
    }
    return count
}

fun main() {
    val passwordList = mutableListOf<String>()
    val openStream: InputStream = ReportRepair::class.java.getResource("/day2-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { passwordList.add(it) } }

    val answer1 = Problem2.solve(passwordList)
    val answer2 = Problem1.solve(passwordList)

    println("First part: $answer1 valid password found")
    println("First part: $answer2 valid password found")
}