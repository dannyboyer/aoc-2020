package day2

import day1.ReportRepair
import java.io.InputStream

val isValidOldImpl: (index1: Int, index2: Int, letter: Char, pwd: String) -> Boolean = { min, max, letter, pwd ->
    var count = 0
    for (c in pwd) {
        if (c == letter) count++
    }
    count in min..max
}

val isValidNewImpl: (index1: Int, index2: Int, letter: Char, pwd: String) -> Boolean = { index1, index2, letter, pwd ->
    (letter == pwd[index1 - 1]).xor(letter == pwd[index2 - 1])
}

val isValidOld: (String) -> Boolean = { password ->
    extractThenValidate(password, isValidOldImpl)
}

val isValidNew: (String) -> Boolean = { password ->
    extractThenValidate(password, isValidNewImpl)
}

fun extractThenValidate(password: String, operation: (Int, Int, Char, String) -> Boolean): Boolean {
    val letter = password.substringAfter(' ').substringBefore(':')[0]
    val pwd = password.substringAfter(": ")
    val min = password.substringBefore('-').toInt()
    val max = password.substringAfter('-').substringBefore(' ').toInt()
    return operation(min, max, letter, pwd);
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

    val answer1 = countValidPasswordInList(passwordList, isValidOld)
    val answer2 = countValidPasswordInList(passwordList, isValidNew)

    println("First part: $answer1 valid password found")
    println("First part: $answer2 valid password found")
}