package net.mikka.adventofcode2023

class Dec04 : PuzzleDay<Int, Int>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Int> {
        return Puzzle02()
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {
            var result = 0
            for (line in input) {
                var cardsWorth = 0
                val cardRegex = """Card.+: (.+)\|(.*)""".toRegex()
                val card = cardRegex.find(line)
                val winningNumbers = card!!.groups[1]!!.value.trim().split(Regex("\\s+"))
                val myNumbers = card.groups[2]!!.value.trim().split(Regex("\\s+"))

                winningNumbers.forEach {
                    if (myNumbers.contains(it)) {
                        cardsWorth = if (cardsWorth == 0) 1 else cardsWorth * 2
                    }
                }
                result += cardsWorth
            }
            return result
        }
    }

    class Puzzle02 : Puzzle<Int> {

        override fun solve(input: List<String>): Int {
            val cards = mutableMapOf<Int, Int>()
            input.forEachIndexed { index, _ -> cards[index + 1] = 1 }

            val cardRegex = """Card.+: (.+)\|(.*)""".toRegex()


            input.forEachIndexed { index, line ->
                var numberOfMatchingNumbers = 0
                val card = cardRegex.find(line)
                val winningNumbers = card!!.groups[1]!!.value.trim().split(Regex("\\s+"))
                val myNumbers = card.groups[2]!!.value.trim().split(Regex("\\s+"))

                winningNumbers.forEach {
                    if (myNumbers.contains(it)) {
                        numberOfMatchingNumbers++
                    }
                }

                for (i in 1..numberOfMatchingNumbers) {
                    val addingIndex = index + i + 1 // +1 (zero-based) +i (additions for the NEXT indexes)
                    val cardsToAdd = cards[index + 1]!! // each card (original + number of copies) of this card is added
                    if (cards.size >= addingIndex) {
                        cards[addingIndex] = cards[addingIndex]!! + cardsToAdd
                    }
                }

            }
            return cards.values.sum()
        }
    }
}