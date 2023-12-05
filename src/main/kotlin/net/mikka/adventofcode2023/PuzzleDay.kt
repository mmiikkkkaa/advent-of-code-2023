package net.mikka.adventofcode2023

import java.time.LocalDate
import kotlin.system.measureTimeMillis

abstract class PuzzleDay<T1, T2> {

    private val inputFile = "/" + this::class.simpleName!!.lowercase() + ".txt"
    private lateinit var input: List<String>


    fun solve() {

        var timeInMs = measureTimeMillis {
            print("${this::class.simpleName}.Puzzle01: " + getPuzzle1().solve(input = testInput()))
        }
        println(" (in $timeInMs ms)")
        timeInMs = measureTimeMillis {
            print("${this::class.simpleName}.Puzzle02: " + getPuzzle2().solve(input = testInput()))
        }
            println(" (in $timeInMs ms)")
        println("###################")
    }

    abstract fun getPuzzle1(): Puzzle<T1>
    abstract fun getPuzzle2(): Puzzle<T2>

    private fun getDayFromClassName(): Int = Regex("""\D+(\d+)""").find(this::class.simpleName!!)!!.groups[1]!!.value.toInt()

    private fun testInput(): List<String> {
        if (this::input.isInitialized) {
            return input
        }

        if (!ResourceReader().fileExist(inputFile)) {
            val content = WebContentHandler().getPuzzleInput(LocalDate.now().year, getDayFromClassName())
            ResourceReader().createFile(inputFile, content)
        }

        input = ResourceReader().readLinesFromResourceFile("/" + this::class.simpleName!!.lowercase() + ".txt")
        return input
    }
}