package net.mikka.adventofcode2023

internal class Dec06Test : AbstractAdventOfCodeTest<Int, Long>() {


    override fun getInput(): String = """
Time:      7  15   30
Distance:  9  40  200
"""

    override fun getExpectedValueForPuzzle1(): Int = 288

    override fun getExpectedValueForPuzzle2(): Long = 71503

}