package day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HandheldHaltingKtTest {

    val instructions = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    ).map { HandheldHalting.makeIns(it) }

    val instructionsAlt = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "nop -4",
        "acc +6"
    ).map { HandheldHalting.makeIns(it) }

    @Test
    fun makeIns() {
        val ins1 = HandheldHalting.makeIns("nop +0")
        assertEquals(ins1.op, "nop")
        assertEquals(ins1.inc, 0)

        val ins2 = HandheldHalting.makeIns("acc +1")
        assertEquals(ins2.op, "acc")
        assertEquals(ins2.inc, 1)

        val ins3 = HandheldHalting.makeIns("jmp -3")
        assertEquals(ins3.op, "jmp")
        assertEquals(ins3.inc, -3)

        val ins4 = HandheldHalting.makeIns("acc -99")
        assertEquals(ins4.op, "acc")
        assertEquals(ins4.inc, -99)

        val ins5 = HandheldHalting.makeIns("jmp +4")
        assertEquals(ins5.op, "jmp")
        assertEquals(ins5.inc, 4)
    }

    @Test
    internal fun testProgram() {
        val program = Program(instructions)
        val output = program.runProgram(instructions.first())
        assertEquals(-1, output)
        assertEquals(5, program.acc)
    }

    @Test
    internal fun testFailure() {
        val program = Program(instructions)
        program.runProgram(instructions.first())
        val possibleBugs = program.getPossibleBugs()
        assertEquals(4, possibleBugs.size)
    }

    @Test
    internal fun testSuccess() {
        val program = Program(instructionsAlt)
        val output = program.runProgram(instructionsAlt.first())
        assertEquals(0, output)
        assertEquals(8, program.acc)
    }

    @Test
    internal fun testAutoFixProgram() {
        val program = Program(instructions)
        program.runProgram()
        val possibleBugs = program.getPossibleBugs()
        val result = findSolution(instructions, possibleBugs)
        assertEquals(8, result)
    }
}