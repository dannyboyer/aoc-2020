package day5

object BinaryBoarding {
    /**
     * returns highest seatId from List
     */
    fun findHighestSeatId(passes: List<String>): Int? {
        return passes
            .map { getSeat(it) }
            .map { getSeatId(it) }
            .maxOrNull()
    }

    /**
     * Find the gap between to seatId
     * i.e.  missing seat is 12 in a listOd(11, 13, 14)
     */
    fun findMySeatFromOrderedList(seatIds: List<Int>): Int {
        var preceding = -1
        var mySeat = -1
        for (id in seatIds) {
            if (id == seatIds.first()) {
                preceding = id
            } else if (id - preceding > 1) {
                mySeat = id - 1
                preceding = id
            } else preceding = id
        }
        return mySeat
    }

    fun getSeat(pass: String): Pair<Int, Int> {
        val row = getRow(pass.substring(0..6))
        val col = getCol(pass.substring(7..9))
        return Pair(row, col)
    }

    /**
     * Takes a string of length 7
     * The first 7 of the pass
     */
    fun getRow(pass: String): Int {
        var arrayOf = IntArray(128) { it }
        var col = -1
        for (char in pass) {
            if ('F' == char) {
                if (arrayOf.size == 2) col = arrayOf.first() else arrayOf = arrayOf.copyOfRange(0, arrayOf.size / 2)
            } else if ('B' == char) {
                if (arrayOf.size == 2) col = arrayOf.last() else arrayOf =
                    arrayOf.copyOfRange(arrayOf.size / 2, arrayOf.size)
            }
        }
        return col
    }

    /**
     * Takes a string of length 3
     * The last 3 of the pass
     */
    fun getCol(pass: String): Int {
        var arrayOf = IntArray(8) { it }
        var col = -1
        for (char in pass) {
            if ('L' == char) {
                if (arrayOf.size == 2) col = arrayOf.first() else arrayOf = arrayOf.copyOfRange(0, arrayOf.size / 2)
            } else if ('R' == char) {
                if (arrayOf.size == 2) col = arrayOf.last() else arrayOf =
                    arrayOf.copyOfRange(arrayOf.size / 2, arrayOf.size)
            }
        }
        return col
    }

    fun getSeatId(pair: Pair<Int, Int>): Int {
        return 8 * pair.first + pair.second
    }
}

fun main() {
    val passesList = mutableListOf<String>()
    val openStream = BinaryBoarding::class.java.getResource("/day5-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { passesList.add(it) } }

    val sortedPass = passesList
        .map { BinaryBoarding.getSeat(it) }
        .map { BinaryBoarding.getSeatId(it) }
        .sorted()

    val answer1 = BinaryBoarding.findHighestSeatId(passesList)
    val answer2 = BinaryBoarding.findMySeatFromOrderedList(sortedPass)

    println("Part1: $answer1 is the highest seat Id")
    println("Part2: $answer2 is my seatId")
}