@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d06

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.*
import helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2017_06).first().ints()

	val seen = msoa()

	loop{ i->
		if(!seen.add(data)){
			i.log()
			return
		}

		val max =data.max()
		val idx = data.indexOfFirst { it == max }

		val next = data.toMutableList()
		next[idx] = 0

		for(x in 1..max) {
			next[(idx + x) % next.size]++
		}

		data =next
	}

}

private fun part2() {
	var data = getLines(2017_06).first().ints()

	val seen = mmoai()

	loop{ i->
		if(data in seen){
			(i - seen[data]!!).log()
			return
		}
		seen[data] = i

		val max =data.max()
		val idx = data.indexOfFirst { it == max }

		val next = data.toMutableList()
		next[idx] = 0

		for(x in 1..max) {
			next[(idx + x) % next.size]++
		}

		data =next
	}
}


fun main() {
	println("Day 6: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }