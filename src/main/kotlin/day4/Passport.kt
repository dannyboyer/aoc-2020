package day4

/**
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID)
 */
class Passport(
    val byr: String,
    val iyr: String,
    val eyr: String,
    val hgt: String,
    val hcl: String,
    val ecl: String,
    val pid: String,
    val cid: String
) {
    val invalid = "INVALID"

    /**
     * four digits; at least 1920 and at most 2002.
     */
    fun isByrValid(): Boolean {
        return if (byr.contains(invalid)) {
            false
        } else {
            val numByr = byr.toInt()
            (byr.length == 4
                    && numByr >= 1920
                    && numByr <= 2002)
        }
    }

    /**
     * four digits; at least 2010 and at most 2020.
     */
    fun isIyrValid(): Boolean {
        return if (iyr.contains(invalid)) {
            false
        } else {
            val numByr = iyr.toInt()
            (iyr.length == 4
                    && numByr >= 2010
                    && numByr <= 2020)
        }
    }

    /**
     * four digits; at least 2020 and at most 2030.
     */
    fun isEyrValid(): Boolean {
        return if (eyr.contains(invalid)) {
            false
        } else {
            val numByr = eyr.toInt()
            (eyr.length == 4
                    && numByr >= 2020
                    && numByr <= 2030)
        }
    }

    /**
     * a number followed by either cm or in:
     */
    fun isHgtValid(): Boolean {
        return if (hgt.contains(invalid)) {
            false
        } else {
            when {
                hgt.contains("in") -> {
                    val num = hgt.substringBefore("in").toInt()
                    num in 59..76
                }
                hgt.contains("cm") -> {
                    val num = hgt.substringBefore("cm").toInt()
                    num in 150..193
                }
                else -> false
            }
        }
    }

    /**
     * a # followed by exactly six characters 0-9 or a-f.
     */
    fun isHclValid(): Boolean {
        val regex = Regex("^#[a-fA-F0-9]{6}")
        return !hcl.contains(invalid) && hcl.length == 7 && regex.matches(hcl)
    }

    /**
     * exactly one of: amb blu brn gry grn hzl oth.
     */

    fun isEclValid(): Boolean {
        val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        return !ecl.contains(invalid) && eyeColors.contains(ecl)
    }

    /**
     * a nine-digit number, including leading zeroes.
     */
    fun isPidValid(): Boolean {
        return !pid.contains(invalid) && pid.length == 9
    }

    fun isValid(): Boolean {
        return isByrValid() &&
                isIyrValid() &&
                isEyrValid() &&
                isHgtValid() &&
                isHclValid() &&
                isEclValid() &&
                isPidValid()
    }
}