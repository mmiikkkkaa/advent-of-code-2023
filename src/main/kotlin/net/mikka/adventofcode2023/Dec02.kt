package net.mikka.adventofcode2023

class Dec02 : PuzzleDay<Int, Int>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Int> {
        return Puzzle02()
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {

            val numberOfRed = 12
            val numberOfGreen = 13
            val numberOfBlue = 14

            val gameRegex = """Game (\d+): (.+)""".toRegex()
            val redRegex = """(\d+) red""".toRegex()
            val greenRegex = """(\d+) green""".toRegex()
            val blueRegex = """(\d+) blue""".toRegex()

            var sum = 0
            for (entry in input) {
                val game = gameRegex.find(entry)
                val gameId = game!!.groups[1]!!.value.toInt()
                val gameCubes = game.groups[2]!!.value

                var possible = true
                val games = gameCubes.split(";")
                games.forEach {
                    val red = redRegex.find(it)?.groups?.get(1)?.value?.toInt()
                    val green = greenRegex.find(it)?.groups?.get(1)?.value?.toInt()
                    val blue = blueRegex.find(it)?.groups?.get(1)?.value?.toInt()

                    if ((red != null && red > numberOfRed) || (green != null && green > numberOfGreen) || (blue != null && blue > numberOfBlue))
                        possible = false
                }
                if (possible) sum += gameId
            }
            return sum
        }
    }

    class Puzzle02 : Puzzle<Int> {

        override fun solve(input: List<String>): Int {
            val gameRegex = """Game (\d+): (.+)""".toRegex()
            val redRegex = """(\d+) red""".toRegex()
            val greenRegex = """(\d+) green""".toRegex()
            val blueRegex = """(\d+) blue""".toRegex()

            var sum = 0
            for (entry in input) {
                val game = gameRegex.find(entry)
                val gameCubes = game!!.groups[2]!!.value

                var fewestRed = 0
                var fewestGreen = 0
                var fewestBlue = 0
                val games = gameCubes.split(";")
                games.forEach {
                    val red = redRegex.find(it)?.groups?.get(1)?.value?.toInt()
                    val green = greenRegex.find(it)?.groups?.get(1)?.value?.toInt()
                    val blue = blueRegex.find(it)?.groups?.get(1)?.value?.toInt()
                    red?.let { currentRed ->  if (fewestRed < currentRed) fewestRed = currentRed }
                    green?.let { currentGreen ->  if (fewestGreen < currentGreen) fewestGreen = currentGreen }
                    blue?.let { currentBlue ->  if (fewestBlue < currentBlue) fewestBlue = currentBlue }
                }
                sum += (fewestRed*fewestGreen*fewestBlue)
            }
            return sum
        }
    }
}