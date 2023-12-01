package net.mikka.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal abstract class AbstractAdventOfCodeTest<T1, T2> {

    abstract fun getInput(): String
    // in case the second input is different from first
    open fun getInput2(): String = getInput()

    abstract fun getExpectedValueForPuzzle1(): T1
    abstract fun getExpectedValueForPuzzle2(): T2

    private val puzzle: PuzzleDay<*, *> = getPuzzle()

    private fun getPuzzle(): PuzzleDay<*, *> {
        return Class.forName(this.javaClass.name.substringBefore("Test"))
                .getDeclaredConstructor()
                .newInstance() as PuzzleDay<*, *>
    }

    @Test
    fun `should solve puzzle 1`() {
        // when

        val result = puzzle.getPuzzle1().solve(getInput().trimIndent().split("\n"))

        // then
        assertThat(result).isEqualTo(getExpectedValueForPuzzle1())
    }

    @Test
    fun `should solve puzzle 2`() {
        // when
        val result = puzzle.getPuzzle2().solve(getInput2().trimIndent().split("\n"))

        // then
        assertThat(result).isEqualTo(getExpectedValueForPuzzle2())
    }
}