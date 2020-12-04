package day4

import day1.ReportRepair

object PassportProcessing {
    fun solve1(passports: List<Passport>): Int {
        var count = 0
        for (p in passports) {
            if (p.isValid()) {
                count++
            }
        }
        return count
    }

    fun initPassport(lines: List<String>): List<Passport> {
        val passports = mutableListOf<Passport>()
        var currentString = ""
        for (line in lines) {
            currentString += " $line"
            if (currentString.isEmpty()) continue
            else if (line.isEmpty() || line == lines.last()) {
                passports.add(readPassportFromString(currentString))
                currentString = ""
            }
        }
        return passports
    }

    private fun readPassportFromString(input: String): Passport {
        val invalid = "INVALID"
        val byr = input.substringAfter("byr:", invalid).substringBefore(' ')
        val iyr = input.substringAfter("iyr:", invalid).substringBefore(' ')
        val eyr = input.substringAfter("eyr:", invalid).substringBefore(' ')
        val hgt = input.substringAfter("hgt:", invalid).substringBefore(' ')
        val hcl = input.substringAfter("hcl:", invalid).substringBefore(' ')
        val ecl = input.substringAfter("ecl:", invalid).substringBefore(' ')
        val pid = input.substringAfter("pid:", invalid).substringBefore(' ')
        val cid = input.substringAfter("cid:", invalid).substringBefore(' ')
        return Passport(byr, iyr, eyr, hgt, hcl, ecl, pid, cid)
    }
}

fun main() {
    val lineList = mutableListOf<String>()
    val openStream = ReportRepair::class.java.getResource("/day4-input").openStream()
    openStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

    val passports = PassportProcessing.initPassport(lineList)
    val answer1 = PassportProcessing.solve1(passports)

    println("First part: $answer1 valid passports")
}