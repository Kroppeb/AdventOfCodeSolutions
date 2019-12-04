package solutions

import helpers.*
import kotlin.math.*

private fun part1() {
	val paswords = 145852..616942
	println(paswords.map { it.toString().toList() }.filter {
		it.isSorted() &&
				it.blocks().any { it.size >= 2 }
	}.count())

}

private fun part2() {
	val paswords = 145852..616942
	println(paswords.map { it.toString().toList() }.filter {
		it.isSorted() &&
				it.blocks().any { it.size == 2 }
	}.count())

}


fun main() {
	part1()
	part2()
}