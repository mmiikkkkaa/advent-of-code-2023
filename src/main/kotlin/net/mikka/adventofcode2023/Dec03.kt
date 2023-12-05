package net.mikka.adventofcode2023

class Dec03 : PuzzleDay<Int, Int>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Int> {
        return Puzzle02()
    }

    companion object {
        fun extractNumbers(input: List<String>): MutableList<Number> {
            val numberRegex = Regex("\\d+")
            val numbers = mutableListOf<Number>()

            for ((row, line) in input.withIndex()) {
                val allNumbersOfRow = numberRegex.findAll(line).iterator()
                while (allNumbersOfRow.hasNext()) {
                    val match = allNumbersOfRow.next()
                    numbers.add(Number(match.value.toInt(), row, match.range.first))
                }
            }
            return numbers
        }

        fun extractGears(input: List<String>): MutableList<Position> {
            val gearRegex = Regex("\\*")
            val gears = mutableListOf<Position>()

            for ((row, line) in input.withIndex()) {
                val allNumbersOfRow = gearRegex.findAll(line).iterator()
                while (allNumbersOfRow.hasNext()) {
                    val match = allNumbersOfRow.next()
                    gears.add(Position(row, match.range.first))
                }
            }
            return gears
        }

        fun Number.isAdjacentToSign(input: List<String>): Boolean {
            val signRegex = Regex("""[^\.\d]""")

            val numberConvexHull = this.convexHull(input)

            for (row in numberConvexHull.first.row..numberConvexHull.second.row) {
                for (column in numberConvexHull.first.column..numberConvexHull.second.column) {
                    if (signRegex.matches(input[row][column].toString())) {
                        return true
                    }
                }
            }
            return false
        }

        fun Position.isAdjacentToNumber(number: Number, input: List<String>): Boolean {
            val numberConvexHull = number.convexHull(input)
            return this.row in numberConvexHull.first.row..numberConvexHull.second.row
                    && this.column in numberConvexHull.first.column..numberConvexHull.second.column
        }
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {
            var result = 0
            val numbers = extractNumbers(input)

            numbers.forEach { number ->
                run {
                    if (number.isAdjacentToSign(input))

                        result += number.value
                }
            }

            return result
        }


    }

    class Puzzle02 : Puzzle<Int> {

        override fun solve(input: List<String>): Int {
            var result = 0
            val numbers = extractNumbers(input)
            val gears = extractGears(input)

            gears.forEach { gear ->
                run {
                    val adjacentNumbers = mutableListOf<Number>()

                    numbers.forEach { number ->
                        run {
                            if (gear.isAdjacentToNumber(number, input))
                                adjacentNumbers.add(number)
                        }
                    }

                    if (adjacentNumbers.size == 2) {
                        result += adjacentNumbers[0].value * adjacentNumbers[1].value
                    }
                }
            }

            return result
        }
    }

    class Number(val value: Int, val row: Int, val startColumn: Int) {
        fun convexHull(input: List<String>): Pair<Position, Position> {
            val globalMaxCol = input[0].length - 1

            val numberMinRow = 0.coerceAtLeast(this.row - 1)
            val numberMaxRow = (this.row + 1).coerceAtMost(input.size - 1)
            val numberMinCol = 0.coerceAtLeast(this.startColumn - 1)
            val numberMaxCol = (this.startColumn + this.value.toString().length).coerceAtMost(globalMaxCol)

            return Pair(Position(numberMinRow, numberMinCol), Position(numberMaxRow, numberMaxCol))
        }
    }

    class Position(val row: Int, val column: Int)
}