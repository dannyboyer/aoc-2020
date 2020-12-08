package day7

class Bag(val color: String, val quantity: Int, var children: List<Bag>)

object HandyHaversacks {
    fun makeBagFrom(color: String, input: String): Bag {
        val children = input
            .replace("bags", "", false)
            .replace("bag", "", false)
            .trim(' ', '.')
            .split(',').mapNotNull {
                val childColor = it.trim(' ').substringAfter(' ').trim(' ')
                val quantityStr = it.substringBefore(" $childColor").trim(' ')
                val quantity = if (quantityStr == "no") 0 else quantityStr.toInt()
                if (childColor == "other") null else Bag(childColor, quantity, mutableListOf())
            }
        return Bag(color, 1, children)
    }

    fun makeRules(lines: List<String>): Map<String, Bag> {
        val rules = mutableMapOf<String, Bag>()
        lines.forEach {
            val color = it.substringBefore(" bags contain")
            val bag = makeBagFrom(color, it.substringAfter("contain"))
            rules[color] = bag
        }
        return rules
    }

    fun makeTree(rules: Map<String, Bag>): Map<String, Bag> {
        for (rule in rules.entries) {
            val bag = rule.component2()
            for (child in bag.children) {
                child.children = rules[child.color]!!.children
            }
        }
        return rules
    }

    fun countPossibleBagContainers(color: String, bags: List<Bag?>): Int {
        var count = 0
        for (bag in bags) {
            if (bag != null) {
                count += if (bag.color == color) 1 else countPossibleBagContainers(color, bag.children)
            }
        }
        return count
    }

    fun sumCount(ofColor: String, rules: Map<String, Bag>): Int {
        return rules.keys.map {
            if (it == "shiny gold") 0
            else (
                    if (countPossibleBagContainers(
                            ofColor,
                            listOf(rules[it])
                        ) >= 1
                    ) 1 else 0
                    )
        }.sum()
    }

    fun countChildren(parentBag: Bag, acc: Int): Int {
        var count = acc
        if (parentBag.children.isEmpty()) {
            return parentBag.quantity
        } else {
            for (bag in parentBag.children) {
                count += parentBag.quantity * countChildren(bag, acc)
            }
            count += parentBag.quantity
        }
        return acc + count
    }
}

fun main() {
    val inputList = mutableListOf<String>()
    val openStream = HandyHaversacks::class.java.getResource("/day7-input").openStream()
    openStream.bufferedReader().useLines { lines ->
        lines.forEach { inputList.add(it) }
    }

    val rules = HandyHaversacks.makeRules(inputList)
    val compiledRules = HandyHaversacks.makeTree(rules)
    val answer1 = HandyHaversacks.sumCount("shiny gold", compiledRules)
    val answer2 = HandyHaversacks.countChildren(compiledRules["shiny gold"]!!, 0) -1

    println("First part: $answer1 is the sum")
    println("Second part: $answer2 is the sum")
}


