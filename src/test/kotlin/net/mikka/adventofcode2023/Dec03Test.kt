package net.mikka.adventofcode2023

internal class Dec03Test : AbstractAdventOfCodeTest<Int, Int>() {


    override fun getInput(): String = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
"""

    override fun getExpectedValueForPuzzle1(): Int = 4361

    override fun getExpectedValueForPuzzle2(): Int = 467835

}