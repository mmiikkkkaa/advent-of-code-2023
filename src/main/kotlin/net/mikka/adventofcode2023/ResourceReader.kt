package net.mikka.adventofcode2023

import java.io.File
import java.nio.charset.StandardCharsets
import java.time.LocalDate

class ResourceReader {

    fun fileExist(file: String): Boolean {
        return this::class.java.getResource(file)?.toURI()
                ?.let { File(it) }
                ?.exists()
                ?: false
    }

    fun createFile(file: String, content: String) {
        // Define the folder you want to write in to
        // this will vary especially if you have a nested project structure
        // IntelliJ under the Edit > Copy Path menu option will help you find the resources
        // relative location
        val year = LocalDate.now().year
        val parentFolder = File("$year/src/main/resources")
        require(parentFolder.exists())
        val outFile = File(parentFolder, file)
        outFile.printWriter(StandardCharsets.UTF_8).use {
            it.println("")
        }
    }

    fun readLinesFromResourceFile(file: String): List<String> {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return this::class.java
                .getResource(file)
                .readText()
                .lines()
                .dropLastWhile { it.isBlank() }
    }
}