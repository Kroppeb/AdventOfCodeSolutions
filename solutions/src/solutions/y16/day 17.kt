@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d17


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.toB
import me.kroppeb.aoc.helpers.point.toP
import java.security.MessageDigest


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_17).first()

	val bounds = 0 toP 0 toB (3 toP 3)

	bfs(0 toP 0 to data, { (a, _) -> a.x == 3 && a.y == 3 }) { (p, d) ->
		val hash = md5(d)
		buildList {
			if (p.up in bounds && (hash[0].toInt() and 0xf0 in 0xb0..0xff)){
				add(p.up to d + "U")
			}
			if (p.down in bounds && (hash[0].toInt() and 0x0f in 0xb..0xf)){
				add(p.down to d + "D")
			}
			if (p.left in bounds && (hash[1].toInt() and 0xf0 in 0xb0..0xff)){
				add(p.left to d + "L")
			}
			if (p.right in bounds && (hash[1].toInt() and 0x0f in 0xb..0xf)){
				add(p.right to d + "R")
			}
		}
	}.log()

}

private fun part2() {
	var data = getLines(2016_17).first()

	val bounds = 0 toP 0 toB (3 toP 3)

	bfsLong(0 toP 0 to data, { (a, _) -> a.x == 3 && a.y == 3 }) { (p, d) ->
		val hash = md5(d)
		buildList {
			if (p.up in bounds && (hash[0].toInt() and 0xf0 in 0xb0..0xff)){
				add(p.up to d + "U")
			}
			if (p.down in bounds && (hash[0].toInt() and 0x0f in 0xb..0xf)){
				add(p.down to d + "D")
			}
			if (p.left in bounds && (hash[1].toInt() and 0xf0 in 0xb0..0xff)){
				add(p.left to d + "L")
			}
			if (p.right in bounds && (hash[1].toInt() and 0x0f in 0xb..0xf)){
				add(p.right to d + "R")
			}
		}
	}.log()
}



inline fun <State> bfsLong(start: State, isEnd: (State) -> Boolean, next: (State) -> Iterable<State>): Pair<State, Int>? {
	// val seen = mutableSetOf(start)
	var queue = mutableListOf(start)
	var nextQueue = mutableListOf<State>()
	var dist = -1
	var longest: Pair<State, Int>? = null

	while (queue.isNotEmpty()) {
//		if (dist % 100 == 0)
//			println("dist:$dist, seen: ${seen.size}, queue: ${queue.size}")
		dist++
		for (current in queue) {
			if (isEnd(current)) longest = current to dist
			else

			for (i in next(current)) {
		//		if (seen.add(i))
					nextQueue.add(i)
			}
		}
		val p = nextQueue
		nextQueue = queue
		queue = p
		nextQueue.clear()
	}
	return longest
}

fun md5(input:String): ByteArray {
	val md = MessageDigest.getInstance("MD5")
	return md.digest(input.toByteArray())
}


fun main() {
	println("Day 17: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }