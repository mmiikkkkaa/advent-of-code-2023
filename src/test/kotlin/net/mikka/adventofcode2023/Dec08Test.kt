package net.mikka.adventofcode2023

internal class Dec08Test : AbstractAdventOfCodeTest<Long, Long>() {


    override fun getInput(): String = """
LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)
"""

    override fun getInput2(): String = """
LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)
"""

    override fun getExpectedValueForPuzzle1(): Long = 6

    override fun getExpectedValueForPuzzle2(): Long = 6

}