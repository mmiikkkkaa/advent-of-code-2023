package net.mikka.adventofcode2023

typealias Element = String

class Dec08 : PuzzleDay<Long, Long>() {

    override fun getPuzzle1(): Puzzle<Long> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Long> {
        return Puzzle02()
    }

    data class Node(val position: Element, val left: Element, val right: Element)

    companion object {
        private fun buildNodeMap(input: List<String>): Map<Element, Node> {
            val nodeRegex = Regex("""(\w+) = \((\w+), (\w+)\)""")
            return buildMap {
                input.drop(2).forEach {
                    val (position, left, right) = nodeRegex.matchEntire(it)?.destructured
                            ?: throw IllegalArgumentException("$it doesn't match the expected format")
                    put(position, Node(position, left, right))
                }
            }
        }
    }

    class Puzzle01 : Puzzle<Long> {
        override fun solve(input: List<String>): Long {
            var result = 0L
            val sequence = input[0]

            val nodeMap = buildNodeMap(input)

            var currentPosition = nodeMap["AAA"]
            while (true) {

                sequence.forEach {
                    result++
                    val nextNode = nodeMap[if (it == 'L') currentPosition!!.left else currentPosition!!.right]
                    if (nextNode!!.position == "ZZZ") {
                        return result
                    }
                    currentPosition = nextNode
                }
            }

            throw IllegalStateException("no result found")
        }
    }

    class Puzzle02 : Puzzle<Long> {

        override fun solve(input: List<String>): Long {
            var result = 0L
            val sequence = input[0]

            val nodeMap = buildNodeMap(input)

            var currentPositions = nodeMap.entries.filter { it.key.endsWith("A") }.map { it.value }.toList()
            while (true) {
                if (result % 1_000_000 == 0L )
                    print(".")

                sequence.forEach {
                    if (currentPositions.all { it.position.endsWith("Z")}) {
                        return result
                    }
                    result++
                    currentPositions = buildList {
                        currentPositions.forEach { entry ->
                            add(nodeMap[if (it == 'L') entry.left else entry!!.right]!!)
                        }
                    }
                }
            }
            throw IllegalStateException("no result found")
        }
    }
}

