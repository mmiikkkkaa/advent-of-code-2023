package net.mikka.adventofcode2023


class Dec05 : PuzzleDay<Int, Int>() {

    override fun getPuzzle1(): Puzzle<Int> {
        return Puzzle01()
    }

    override fun getPuzzle2(): Puzzle<Int> {
        return Puzzle02()
    }

    class Puzzle01 : Puzzle<Int> {
        override fun solve(input: List<String>): Int {
//            return 26273516; // skip the actual, long-running computation

            var currentMap = mutableMapOf<Long, Long>()
            for ((index, line) in input.withIndex()) {
                if (index == 0) {
                    val seeds = line.substringAfter("seeds: ").split(Regex("\\s+")).map { it.toLong() }
                    seeds.forEach { currentMap[it] = it }
                    continue
                }

                if (line.isBlank()) continue

                if (line == "seed-to-soil map:") {
                    continue
                }
                if (line == "soil-to-fertilizer map:") {
                    // forget seeds, set everything to soil
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "fertilizer-to-water map:") {
                    // forget soil, set everything to fertilizer
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "water-to-light map:") {
                    // forget fertilizer, set everything to water
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "light-to-temperature map:") {
                    // forget water, set everything to light
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "temperature-to-humidity map:") {
                    // forget light, set everything to temperature
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "humidity-to-location map:") {
                    // forget temperature, set everything to humidity
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                val mapEntry = MapEntry.of(line)

                var rangeOffset = 0
                for (i in mapEntry.sourceRangeStart until mapEntry.sourceRangeStart + mapEntry.range) {
                    // only handle numbers, that are of interest for elements from the current map
                    if (!currentMap.keys.contains(i)) {
                        rangeOffset++
                        continue
                    }
                    currentMap[i] = mapEntry.destinationRangeStart + rangeOffset++
                }
            }

            return currentMap.entries.minOf { it.value }.toInt()
        }
    }

    class Puzzle02 : Puzzle<Int> {

        override fun solve(input: List<String>): Int {
            var currentMap = mutableMapOf<Long, Long>()
            for ((index, line) in input.withIndex()) {
                if (index == 0) {
                    val seeds = line.substringAfter("seeds: ").split(Regex("\\s+")).map { it.toLong() }
                    seeds.forEachIndexed{ index, _ ->
                        if (index % 2 == 0){
                            val rangeStart = seeds[index]
                            val rangeLength = seeds[index + 1]

                            for( i in rangeStart until rangeStart+rangeLength) {
                                currentMap[i] = i
                            }
                        }
                    }
                    continue
                }

                if (line.isBlank()) continue
                if (line == "seed-to-soil map:") {
                    continue
                }
                if (line == "soil-to-fertilizer map:") {
                    // forget seeds, set everything to soil
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "fertilizer-to-water map:") {
                    // forget soil, set everything to fertilizer
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "water-to-light map:") {
                    // forget fertilizer, set everything to water
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "light-to-temperature map:") {
                    // forget water, set everything to light
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "temperature-to-humidity map:") {
                    // forget light, set everything to temperature
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                if (line == "humidity-to-location map:") {
                    // forget temperature, set everything to humidity
                    val newMap = mutableMapOf<Long, Long>()
                    currentMap.values.forEach { value -> newMap[value] = value }
                    currentMap = newMap
                    continue
                }
                val mapEntry = MapEntry.of(line)

                currentMap.keys.forEach { key ->
                    if (key in mapEntry.sourceRangeStart until mapEntry.sourceRangeStart + mapEntry.range) {
                        var rangeOffset = 0
                        for (i in mapEntry.sourceRangeStart until mapEntry.sourceRangeStart + mapEntry.range) {
                            // only handle numbers, that are of interest for elements from the current map
                            if (!currentMap.keys.contains(i)) {
                                rangeOffset++
                                continue
                            }
                            currentMap[i] = mapEntry.destinationRangeStart + rangeOffset++
                        }
                    }
                }
            }
            return currentMap.entries.minOf { it.value }.toInt()
        }
    }
}

class MapEntry(val destinationRangeStart: Long, val sourceRangeStart: Long, val range: Long) {
    companion object {
        private val lineRegex = Regex("""(\d+)\s+(\d+)\s+(\d+)""")
        fun of(line: String): MapEntry {
            try {
                val matchResult = lineRegex.find(line)!!
                return MapEntry(
                        matchResult.groups[1]!!.value.toLong(),
                        matchResult.groups[2]!!.value.toLong(),
                        matchResult.groups[3]!!.value.toLong())
            }
            catch (e: Exception) {
                throw e
            }
        }
    }
}