package day6

object CustomCustoms {
    tailrec fun sumCounts(lines: List<String>, acc: Int): Int {
        return if (lines.isEmpty()) acc else {
            val group = lines.takeWhile { it.isNotEmpty() }
            val newList = if (group.size >= lines.size) listOf() else lines.subList(group.size + 1, lines.size)
            sumCounts(
                newList,
                acc + getGroupSum(group)
            )
        }
    }

    tailrec fun sumCountsFixed(lines: List<String>, acc: Int): Int {
        return if (lines.isEmpty()) acc else {
            val group = lines.takeWhile { it.isNotEmpty() }
            val newList = if (group.size >= lines.size) listOf() else lines.subList(group.size + 1, lines.size)
            sumCountsFixed(
                newList,
                acc + getGroupSumFixed(group)
            )
        }
    }

    fun getGroupSum(lines: List<String>): Int {
        val charSet = HashSet<Char>()
        for (line in lines) {
            for (char in line) {
                charSet.add(char)
            }
        }
        return charSet.size;
    }

    fun getGroupSumFixed(lines: List<String>): Int {
        val charMap = mutableMapOf<Char, Int>()
        for (line in lines) {
            for (char in line) {
                if (charMap[char] == null) {
                    charMap[char] = 1
                } else {
                    charMap[char] = charMap[char]!!.plus(1)
                }
            }
        }
        return charMap.values.count { it >= lines.size }
    }
}

fun main() {
    val lineList = mutableListOf<String>()
    val openStream = CustomCustoms::class.java.getResource("/day6-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

    val answer1 = CustomCustoms.sumCounts(lineList, 0)
    val answer2 = CustomCustoms.sumCountsFixed(lineList, 0)

    println("First part: $answer1 is the sum")
    println("Second part: $answer2 is the sum")
}