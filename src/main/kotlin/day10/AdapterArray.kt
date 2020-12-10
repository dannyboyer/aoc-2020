package day10

import java.io.InputStream

object AdapterArray {
    tailrec fun getJoltDistributions(rating: Int, adapters: List<Int>, distribution: MutableMap<Int, Int>): MutableMap<Int, Int> {
        if (adapters.isEmpty()) {
            val count = distribution.getOrPut(3, { 0 })
            distribution[3] = count + 1
            return distribution
        }
        val nextAdapter = adapters.filter { it - 3 <= rating }.minOrNull() ?: return mutableMapOf()
        val delta = nextAdapter - rating
        val count = distribution.getOrPut(delta, { 0 })
        distribution[delta] = count + 1
        return getJoltDistributions(adapters.first(), adapters.drop(1), distribution)
    }

    fun getMagicNumber(distribution: MutableMap<Int, Int>): Int {
        return distribution.getOrDefault(1, 0) * distribution.getOrDefault(3, 0)
    }
}

fun main() {
    val adapters = mutableListOf<Int>()
    val openStream: InputStream = AdapterArray::class.java.getResource("/day10-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { adapters.add(it.toInt()) } }

    val distributions = AdapterArray.getJoltDistributions(0, adapters.sorted(), mutableMapOf())
    val answer1 = AdapterArray.getMagicNumber(distributions)
    println("Answer 1 : $answer1")
}