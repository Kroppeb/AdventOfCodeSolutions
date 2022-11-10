@file:Suppress("PackageDirectoryMismatch", "PackageName")
package trainings.`2017`.`02`
import helpers.*
import grid.*
import kotlinx.coroutines.*

private val xxxxx = Clock(3,6);

private fun part1(data: Data) = runBlocking {
	println(data.sumBy { it.max() - it.min()})
}

private fun part2(data: Data) = runBlocking {
	println(data.sumBy { it.orderedPairWise().find{(a,b) -> a % b == 0}!!.let{(a,b) -> a/b} })
}

private typealias Data = List<List<Int>>


fun main() {
	val data: Data = getIntLines(2017_02)
	part1(data)
	part2(data)
}