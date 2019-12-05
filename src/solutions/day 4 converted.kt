package solutions

import helpers.*
import kotlin.math.*
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis


private fun part1() = (145852..616942).map { it.toString().toList() }.filter {
	it.isNonDescending() &&
			it.blocks().any { it.size >= 2 }
}.count()

private fun part2() = (145852..616942).map { it.toString().toList() }.filter {
	it.isNonDescending() &&
			it.blocks().any { it.size == 2 }
}.count()


fun main() {
	println(part1())
	println(part2())
	println(List(100) {
		measureTimeMillis {
					part1()
					part2()
				}

	}.average())
}