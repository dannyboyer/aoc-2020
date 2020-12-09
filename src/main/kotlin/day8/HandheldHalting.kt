package day8

class Instruction(var op: String, val inc: Int, var pos: Int = -1)

object HandheldHalting {
    fun makeIns(input: String): Instruction {
        val op = input.substring(0..2)
        val incPos = input.substringAfter('+')
        val incNeg = input.substringAfter('-')
        val inc = if (incPos == input) -incNeg.toInt() else incPos.toInt()
        return Instruction(op, inc)
    }
}

class Program(val instructions: List<Instruction>) {
    var acc = 0
    var position = 0

    fun runProgram(inst: Instruction? = instructions.first()): Int {
        if (inst == null) return 0
        if (inst.pos != -1) return -1
        inst.pos = position
        return when (inst.op) {
            "nop" -> {
                position++
                runProgram(instructions.getOrNull(position))
            }
            "acc" -> {
                position++
                acc += inst.inc
                runProgram(instructions.getOrNull(position))
            }
            "jmp" -> {
                position += inst.inc
                runProgram(instructions.getOrNull(position))
            }
            else -> return -1
        }
    }

    fun getPossibleBugs(): List<Instruction> {
        return instructions.filter { it.op == "jmp" || it.op == "nop" && it.pos != -1 }
    }
}

fun swapForNewInst(inst: Instruction): Instruction {
    val op = if (inst.op == "jmp") "nop" else "jmp"
    return Instruction(op, inst.inc)
}

fun makeNewProgram(indexOfFix: Int, program: List<Instruction>): Program {
    val newProgram: MutableList<Instruction> = mutableListOf()
    newProgram.addAll(program)
    val fix = swapForNewInst(program[indexOfFix])
    newProgram[indexOfFix] = fix
    return Program(newProgram)
}

fun findSolution(instructions: List<Instruction>, possibleBugs: List<Instruction>): Int {
    val programList = possibleBugs.map { makeNewProgram(instructions.indexOf(it), instructions) }
    for (p in programList) {
        p.instructions.forEach { it.pos = -1 }
        val output = p.runProgram()
        if (output == 0) {
            return p.acc
        }
    }
    return -1
}

fun main() {
    val inputList = mutableListOf<String>()
    val openStream = HandheldHalting::class.java.getResource("/day8-input").openStream()
    openStream.bufferedReader().useLines { lines ->
        lines.forEach { inputList.add(it) }
    }

    val instructions = inputList.map { HandheldHalting.makeIns(it) }
    val program = Program(instructions)
    program.runProgram()
    val answer1 = program.acc
    val possibleBugs = program.getPossibleBugs()
    val answer2 = findSolution(instructions, possibleBugs)

    println("First part: $answer1 is the sum")
    println("Second part: $answer2 is the sum")
}