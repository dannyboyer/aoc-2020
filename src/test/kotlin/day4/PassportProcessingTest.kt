package day4

import day3.PassportProcessing
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class PassportProcessingTest {


    fun readPassportFromString() {
        val passports = PassportProcessing.initPassport(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:1937 iyr:2017 cid:147 hgt:183cm",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#cfa07d byr:1929",
                "",
                "hcl:#ae17e1 iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:179cm",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:59in"
            )
        )
        val count = PassportProcessing.solve1(passports)
        assertEquals(2, count)
    }

    @Test
    internal fun `chekByr`() {
        val passports = PassportProcessing.initPassport(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:2003 iyr:2017 cid:147 hgt:183cm",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#cfa07d byr:1929",
                "",
                "hcl:#ae17e1 iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:179cm",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:59in"
            )
        )
        assertEquals(false, passports[0].isByrValid())
        assertEquals(true, passports[1].isByrValid())
    }

    @Test
    internal fun `chekHgt`() {
        val passports = PassportProcessing.initPassport(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:2003 iyr:2017 cid:147 hgt:60in",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#cfa07d byr:1929 hgt:190cm",
                "",
                "hcl:#ae17e1 iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:190in",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:190"
            )
        )
        assertEquals(true, passports[0].isHgtValid())
        assertEquals(true, passports[1].isHgtValid())
        assertEquals(false, passports[2].isHgtValid())
        assertEquals(false, passports[3].isHgtValid())
    }

    @Test
    internal fun `chekHcl`() {
        val passports = PassportProcessing.initPassport(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#123abc",
                "byr:2003 iyr:2017 cid:147 hgt:60in",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#123abz byr:1929 hgt:190cm",
                "",
                "hcl:123abc iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:190in",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:190"
            )
        )
        assertEquals(true, passports[0].isHclValid())
        assertEquals(false, passports[1].isHclValid())
        assertEquals(false, passports[2].isHclValid())

    }


}