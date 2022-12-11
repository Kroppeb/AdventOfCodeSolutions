@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d25

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toP


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_25).ints().map{(a,b,c,d) -> a toP b to (c toP d)}

	val union = UnionFind()


	for ((a,b) in data.pairWise()){
		if(a.first.manDistTo(b.first) + a.second.manDistTo(b.second) <= 3) {
			union.join(a,b)
		}
	}

	val s = msoa()

	for(d in data) {
		if (s.none{union.areJoined(d,it)}) {
			s.add(d)
		}
	}

	s.size.log()

}

private fun part2() {

}


fun main() {
	println("Day 25: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }