package day1

import java.io.File
import java.io.InputStream

object ReportRepair {
    fun findEntries(numberList: List<Int>): Int {
        var a = -1
        var b = -1

        loop@ for (nextA in numberList) {
            a = nextA
            b = 2020 - a
            for (n in numberList) {
                if (n == b) break@loop
            }
        }
        return a * b
    }
}

fun main() {
    // read from file
    // readFileLineByLineUsingForEachLine("day1-input")
    val reportExpenses = mutableListOf<Int>()
    val openStream: InputStream = ReportRepair::class.java.getResource("/day1-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { reportExpenses.add(it.toInt()) } }

    val answer = ReportRepair.findEntries(reportExpenses)

    println(answer)
}