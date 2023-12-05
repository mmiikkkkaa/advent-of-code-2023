package net.mikka.adventofcode2023

import kotlin.reflect.KClass

fun main() {

    val longRunning: List<KClass<*>> = listOf(
            Dec05::class
    )

    for (day in 1..24) {
        try {
            val puzzle = Class.forName("net.mikka.adventofcode2023.Dec${formatDay(day)}").getDeclaredConstructor().newInstance()
            if (longRunning.any { puzzle::class == it }) {
                println("Puzzle for day $day skipped due to long running time")
                println("###################")
                continue
            }
            (puzzle as PuzzleDay<*, *>).solve()
        } catch (e: NotImplementedError) {
            return
        } catch (e: Exception) {
            println(e)
            return
        }
    }
}

private fun formatDay(day: Int) = if (day <= 9) "0${day}" else day.toString()