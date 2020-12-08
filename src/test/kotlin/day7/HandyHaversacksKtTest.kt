package day7

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HandyHaversacksKtTest {

    val input = listOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags.",
    )

    @Test
    fun makeBagFromTest() {
        val bag = HandyHaversacks.makeBagFrom("light red", " 1 bright white bag, 2 muted yellow bags.")
        assertEquals(2, bag.children.size)
        assertEquals(1, bag.quantity)
        assertEquals("light red", bag.color)
        assertEquals("bright white", bag.children[0]?.color)
        bag.children[0]?.let { assertEquals(1, it.quantity) }
        assertEquals("muted yellow", bag.children[1]?.color)
        bag.children[1]?.let { assertEquals(2, it.quantity) }
    }

    @Test
    internal fun makeRuleTest() {
        val rules = HandyHaversacks.makeRules(input)
        assertEquals(9, rules.size)
    }

    @Test
    internal fun makeTreeTest() {
        val rules = HandyHaversacks.makeRules(input)
        val compiledRules = HandyHaversacks.makeTree(rules)
        assertEquals(9, compiledRules.size)
    }

    @Test
    internal fun countPossibleBagContainersTest() {
        val rules = HandyHaversacks.makeRules(input)
        val compiledRules = HandyHaversacks.makeTree(rules)
        val result = HandyHaversacks.sumCount(compiledRules)

        assertEquals(4, result)
    }

    @Test
    internal fun countNumberOfChildrenTest() {
        val rules = HandyHaversacks.makeRules(input)
        val compiledRules = HandyHaversacks.makeTree(rules)
        val result = HandyHaversacks.countNumberOfChildren(compiledRules["shiny gold"]!!)
        assertEquals(32, result)
    }
}