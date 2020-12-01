package day1

import java.io.InputStream

object ReportRepair {

    /**
     * Brute Force approach O(n^2)
     */
    fun findTwoEntries(numberList: List<Int>): Int {
        var result = 0
        for (a in numberList) {
            for (b in numberList) {
                if (2020 - (a + b) == 0) {
                    result = a * b
                }
            }
        }
        return result
    }

    /**
     * Brute Force approach O(n^3)
     */
    fun findThreeEntries(numberList: List<Int>): Int {
        var result = 0
        for (a in numberList) {
            for (b in numberList) {
                for (c in numberList) {
                    if (2020 - (a + b + c) == 0) {
                        result = a * b * c
                    }
                }
            }
        }
        return result
    }
}

fun main() {
    // read from file
    // readFileLineByLineUsingForEachLine("day1-input")
    val reportExpenses = mutableListOf<Int>()
    val openStream: InputStream = ReportRepair::class.java.getResource("/day1-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { reportExpenses.add(it.toInt()) } }

    val answer1 = ReportRepair.findTwoEntries(reportExpenses)
    val answer2 = ReportRepair.findThreeEntries(reportExpenses)

    println("First part: $answer1")
    println("Second part: $answer2")
}