package day3

import day1.ReportRepair
import java.io.InputStream

object TobogganTrajectory {
    fun countTreesEncounter(map: List<String>, moveLeft: Int, moveDown: Int): Long {
        var treeCount: Long = 0
        var horizontalPos = 0;
        var verticalPos = 0;

        while (verticalPos <= map.size - 1) {
            val currentArea = map[verticalPos]
            if ('#' == currentArea[horizontalPos]) treeCount++
            verticalPos += moveDown
            horizontalPos += moveLeft
            if (horizontalPos > currentArea.length - 1) horizontalPos -= currentArea.length
        }

        return treeCount
    }

    fun multiplyEncounter(slopes: List<Pair<Int, Int>>, map: List<String>): Long {
        var mult: Long = 1
        for (s in slopes) {
            val countTreesEncounter = countTreesEncounter(map, s.first, s.second)
            mult *= countTreesEncounter
        }
        return mult
    }
}

fun main() {
    val mapList = mutableListOf<String>()
    val openStream: InputStream = ReportRepair::class.java.getResource("/day3-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { mapList.add(it) } }

    val pairs = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    val answer1 = TobogganTrajectory.countTreesEncounter(mapList, 3, 1)
    val answer2 = TobogganTrajectory.multiplyEncounter(pairs, mapList)

    println("First part: $answer1 trees encountered")
    println("First part: $answer2 trees encountered")
}