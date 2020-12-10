package day9

import java.io.InputStream

object EncodingError {
    fun isValid(num: Long, numberList: List<Long>): Boolean {
        for (a in numberList) {
            val indexOfB = numberList.indexOf(num - a)
            if (indexOfB != -1 && numberList[indexOfB] != a) {
                return true
            }
        }
        return false
    }

    fun findInvalidNum(numberList: List<Long>, preambleSize: Int): Long {
        var from = 0
        var to = preambleSize

        for (n in numberList.slice(preambleSize..numberList.size - 1)) {
            if (!isValid(n, numberList.subList(from++, to++))) {
                return n
            }
        }
        return -1
    }
}

fun main() {
    val numbers = mutableListOf<Long>()
    val openStream: InputStream = EncodingError::class.java.getResource("/day9-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { numbers.add(it.toLong()) } }

    val answer1 = EncodingError.findInvalidNum(numbers, 25)
    //val answer2 = ReportRepair.findThreeEntries(reportExpenses)

    println("First part: $answer1")
    //rintln("Second part: $answer2")
}