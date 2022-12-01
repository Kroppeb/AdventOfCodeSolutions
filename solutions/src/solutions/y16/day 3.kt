@file:Suppress("PackageDirectoryMismatch", "PackageName")

package solutions.y16.d03

import grid.Clock
import helpers.SSV
import helpers.getAlphaNumLines
import helpers.transpose
import kotlinx.coroutines.runBlocking

private val xxxxx = Clock(3, 6);


/**
 *
 */
private fun part1(data: Data) = runBlocking {
    // valid triangle lengths
    println(data.map { it.map { it.toInt() }.sorted() }.count { (a, b, c) -> c < a + b })
}

private fun part2(data: Data) = runBlocking {

    // valid triangle lengths
    println(data.chunked(3).map { it.transpose() }.flatten()
            .map { it.map { it.toInt() }.sorted() }.count { (a, b, c) -> c < a + b })
}

private typealias Data = SSV


fun main() {
    val data: Data = getAlphaNumLines(2016_03)
    part1(data)
    part2(data)
}