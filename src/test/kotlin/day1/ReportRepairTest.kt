package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReportRepairTest {

    @Test
    fun `find the two entries that sum to 2020`() {
        val report = listOf(1721, 979, 366, 299, 675, 1456)
        assertEquals(514579, ReportRepair.findEntries(report))
    }
}