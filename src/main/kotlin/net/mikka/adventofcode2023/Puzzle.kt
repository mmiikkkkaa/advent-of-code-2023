package net.mikka.adventofcode2023

interface Puzzle<T> {
    fun solve(input:List<String>): T
}