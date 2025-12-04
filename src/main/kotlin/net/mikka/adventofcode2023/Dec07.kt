package net.mikka.adventofcode2023

class Dec07 : PuzzleDay<Int, Long>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Long> {
        return Puzzle02()
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {
            var result = 1
            val times = input[0].substringAfter("Time:").trim().split(Regex("\\s+")).map { it.toInt() }
            val distances = input[1].substringAfter("Distance:").trim().split(Regex("\\s+")).map { it.toInt() }

            times.forEachIndexed { index: Int, time: Int ->
                val winningDistances = mutableListOf<Int>()
                for (t in 0..time) {
                    val distance = t * (time - t)
                    if (distance > distances[index])
                        winningDistances.add(distance)
                }
                result *= winningDistances.size
            }

            return result
        }
    }

    class Puzzle02 : Puzzle<Long> {

        override fun solve(input: List<String>): Long {
            var result = 1L
            val time = input[0].substringAfter("Time:").replace(Regex("\\s+"), "").toLong()
            val distance = input[1].substringAfter("Distance:").replace(Regex("\\s+"), "").toLong()

            val winningDistances = mutableListOf<Long>()
            for (t in 0..time) {
                val currentDistance = t * (time - t)
                if (currentDistance > distance)
                    winningDistances.add(currentDistance)
            }
            result *= winningDistances.size

            return result
        }
    }
}