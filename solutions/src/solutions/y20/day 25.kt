@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d25

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val (a,b) = data.flatten()
	var i = 0
	var k = 1
	var kb = 1L
	while(k != a){
		k*= 7
		k%=20201227

		kb*= b
		kb%=20201227
		i++
	}
	println(kb)
}

private fun part2(data: Data) {
}

private typealias Data = IntLines

fun main() {
	val data: Data = getIntLines(2020_25)
	part1(data)
	part2(data)
}


private fun <T> T.log(): T = also { println(this) }