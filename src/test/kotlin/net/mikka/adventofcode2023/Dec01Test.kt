package net.mikka.adventofcode2023

internal class Dec01Test : AbstractAdventOfCodeTest<Int, Int>() {


    override fun getInput(): String = """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
"""

    override fun getInput2(): String = """
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
"""

    override fun getExpectedValueForPuzzle1(): Int = 142

    override fun getExpectedValueForPuzzle2(): Int = 281

}