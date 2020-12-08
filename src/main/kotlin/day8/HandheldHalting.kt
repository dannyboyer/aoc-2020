package day8

class Instruction(val op: String, val inc: Int, var pos: Int = -1)

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

    fun runProgram(inst: Instruction): Int {
        if (inst.pos != -1) return acc
        inst.pos = 1
        return when (inst.op) {
            "nop" -> {
                position++
                runProgram(instructions[position])
            }
            "acc" -> {
                position++
                acc += inst.inc
                runProgram(instructions[position])
            }
            "jmp" -> {
                position += inst.inc
                runProgram(instructions[position])
            }
            else -> return -1
        }
    }

    fun getPossibleBugs(): List<Instruction> {
        return instructions
            .filter { it.op == "jmp"}
            .filter { it.pos == 1 }
    }
}

fun main() {
    val inputList = mutableListOf<String>()
    val openStream = HandheldHalting::class.java.getResource("/day8-input").openStream()
    openStream.bufferedReader().useLines { lines ->
        lines.forEach { inputList.add(it) }
    }

    val instructions = inputList.map { HandheldHalting.makeIns(it) }
    val answer1 = Program(instructions).runProgram(instructions.first())
//    val answer2 = HandyHaversacks.countChildren(compiledRules["shiny gold"]!!, 0) - 1
//
    println("First part: $answer1 is the sum")
//    println("Second part: $answer2 is the sum")
}