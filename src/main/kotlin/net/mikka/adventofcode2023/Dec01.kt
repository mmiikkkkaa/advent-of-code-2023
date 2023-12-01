package net.mikka.adventofcode2023

class Dec01 : PuzzleDay<Int, Int>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }
    override fun getPuzzle2(): Puzzle<Int> {
        return Puzzle02()
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {

            var sum = 0
            for (entry in input) {
                val digitRegex = "\\d".toRegex()
                val firstDigit = digitRegex.find(entry)!!.value
                val lastDigit = digitRegex.find(entry.reversed())!!.value
                sum += "$firstDigit$lastDigit".toInt()
            }
            return sum
        }
    }

    class Puzzle02 : Puzzle<Int> {
        val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        override fun solve(input: List<String>): Int {
            var sum = 0
            for (entry in input) {
                val firstDigit = buildRegex().find(entry)!!.value.replaceToDigit()
                val lastDigit = buildRegex(true).find(entry.reversed())!!.value.reversed().replaceToDigit()
                val number = "$firstDigit$lastDigit"
                sum += number.toInt()
                println("line $entry -> $number sums up to $sum" )

            }
            return sum
        }

        private fun buildRegex(reversed: Boolean = false): Regex {
            val stringBuilder = StringBuilder("\\d").append("|")
            digits.forEach { stringBuilder.append(if (!reversed) it else it.reversed() ).append("|") }

            return stringBuilder.toString().removeSuffix("|").toRegex()
        }
        private fun String.replaceToDigit():String {
            return this
                    .replace("one", "1")
                    .replace("two", "2")
                    .replace("three", "3")
                    .replace("four", "4")
                    .replace("five", "5")
                    .replace("six", "6")
                    .replace("seven", "7")
                    .replace("eight", "8")
                    .replace("nine", "9")
//                    .replace("zero", "0")
        }
    }
}