package day1

import java.io.InputStream

object ReportRepair {

    /**
     * Brute Force approach O(n^2)
     */
    fun findTwoEntries(numberList: List<Int>): Int {
        for (a in numberList) {
            for (b in numberList) {
                if (2020 == a + b) {
                    return a * b
                }
            }
        }
        return -1
    }

    /**
     * Brute Force approach O(n^3)
     */
    fun findThreeEntries(numberList: List<Int>): Int {
        for (a in numberList) {
            for (b in numberList) {
                for (c in numberList) {
                    if (2020 == a + b + c) {
                        return a * b * c
                    }
                }
            }
        }
        return -1
    }
}

fun main() {
    val reportExpenses = mutableListOf<Int>()
    val openStream: InputStream = ReportRepair::class.java.getResource("/day1-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { reportExpenses.add(it.toInt()) } }

    val answer1 = ReportRepair.findTwoEntries(reportExpenses)
    val answer2 = ReportRepair.findThreeEntries(reportExpenses)

    println("First part: $answer1")
    println("Second part: $answer2")
}