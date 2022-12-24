@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d22


/*

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*

 */



import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.graph.*
import me.kroppeb.aoc.helpers.grid.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.*
import itertools.*
import me.kroppeb.aoc.helpers.collections.list.*
import kotlin.*
import kotlin.annotation.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var (mp, ins) = getLines(2022_22).splitOnEmpty()

	var mx = mp.maxOf { it.length }

	val map = mp.e().map{it + repeat(' ', mx - it.size).toList()}.grid()

	var pos = (0 toP 0).eastsInc().first { map[it.int] == '.' }

	val rs = ins[0].sints().zip(listOf("X") + ins[0].split(Regex("\\d+")).filter{it != ""})

	var dir = (0 toP 0).right
	val bounds_ = map.boundsI
	val bounds = bounds_.lower.sint toB bounds_.higher.sint

	for ((n, r) in rs) {
		when (r) {
			"X" -> {}
			"R" -> dir = dir.rotateClock()
			"L" -> dir = dir.rotateAntiClock()
			else -> error(r)
		}

		for (i in 1..n) {
			var next = (pos + dir) % bounds


			if (map[next.int] == '#') {
				break
			} else if (map[next.int] == '.') {
				pos = next
			} else {
				while (map[next.int] == ' ') {
					next += dir
					next %= bounds
				}

				if (map[next.int] == '#') {
					break
				} else if (map[next.int] == '.') {
					pos = next
				} else {
					throw Exception()
				}
			}
		}
	}

	val f = when(dir) {
		(0 toP 0).right-> 0
		(0 toP 0).down -> 1
		(0 toP 0).left -> 2
		(0 toP 0).up -> 3
		else -> throw Exception()
	}

	pos log 0
	val row = pos.x + 1
	val col = pos.y + 1

	1000 * row + 4 * col + f log 1


}


private fun part2() {
	var (mp, ins) = getLines(2022_22).splitOnEmpty()

	var mx = mp.maxOf { it.length }

	val map = mp.e().map { it + repeat(' ', mx - it.size).toList() }.grid()

	var pos = (0 toP 0).eastsInc().first { map[it.int] == '.' }

	val rs = ins[0].sints().zip(listOf("X") + ins[0].split(Regex("\\d+")).filter { it != "" })

	var dir = (0 toP 0).right
	val bounds_ = map.boundsI
	val bounds = bounds_.lower.sint toB bounds_.higher.sint

	for ((n, r) in rs) {
		when (r) {
			"X" -> {}
			"R" -> dir = dir.rotateClock()
			"L" -> dir = dir.rotateAntiClock()
			else -> error(r)
		}


		for (i in 1..n) {
			var next = (pos + dir)
			val od = dir

			if (next !in bounds || map[next.int] == ' ') {
				run {

					val __x = getLet(pos)


					val __f = when (dir) {
						(0 toP 0).right -> "right"
						(0 toP 0).down -> "down"
						(0 toP 0).left -> "left"
						(0 toP 0).up -> "up"
						else -> throw Exception()
					}

					"$__x $__f" log -5
				}

				val (n,d) = wrap(next, dir) log -1
				next = n
				dir = d

				val __x = getLet(pos)
				val __f = when (dir) {
					(0 toP 0).right -> "right"
					(0 toP 0).down -> "down"
					(0 toP 0).left -> "left"
					(0 toP 0).up -> "up"
					else -> throw Exception()
				}

				"$__x $__f" log -6
			}


			if (map[next.int] == '#') {
				dir = od // restore
				break
			} else if (map[next.int] == '.') {
				pos = next
			} else {
				throw Exception()
			}
		}
	}

	val f = when (dir) {
		(0 toP 0).right -> 0
		(0 toP 0).down -> 1
		(0 toP 0).left -> 2
		(0 toP 0).up -> 3
		else -> throw Exception()
	}

	pos log 0
	val row = pos.x + 1
	val col = pos.y + 1

	1000 * row + 4 * col + f log 1


}

fun wrap(p: Point, dir: Point): Pair<Point, Point> {
	(p to dir) log 0
	val (x, y) = p

	// AB
	// C
	//DE
	//F

	when (dir) {
		(0 toP 0).right -> {
			if (y == 50.s && x in 150..199.s) { //F
				return 149.s toP x - 100 to (0 toP 0).up
			} else if (y == 100.s) {
				if (x in 50..99.s) { // C -> B
					return 49.s toP 50 + x to (0 toP 0).up
				} else if (x in 100..149.s) { // E
					return 149 - x toP 149.s to (0 toP 0).left
				}
			} else if (y == 150.s) { // B
				return 149 - x toP 99.s to (0 toP 0).left
			}
		}

		(0 toP 0).down -> {
			if (x == 50.s && y in 100..149.s) { // B -> C
				return y - 50 toP 99.s to (0 toP 0).left
			} else if (x == 150.s && y in 50..99.s) { // E -> F
				return y + 100 toP 49.s to (0 toP 0).left
			} else if (x == 200.s) { // F -> B
				return 0.s toP y + 100 to (0 toP 0).down
			}
		}

		(0 toP 0).left -> {
			// AB
			// C
			//DE
			//F
			if (y == -1.s) {
				if (x in 100..149.s) { // D -> A
					return 149 - x toP 50.s to (0 toP 0).right
				} else if (x in 150..199.s) { // F -> A
					return 0.s toP x - 100 to (0 toP 0).down
				}
			} else if (y == 49.s) {
				if (x in 0..49.s) { // A -> D
					return 149 - x toP 0.s to (0 toP 0).right
				} else if (x in 50..99.s) { // C -> D
					return 100.s toP (x - 50) to (0 toP 0).down
				}
			}

		}

		(0 toP 0).up -> {
			// AB
			// C
			//DE
			//F
			if (x == -1.s) {
				if (y in 50..99.s) { // A
					return y + 100 toP 0 to (0 toP 0).right
				} else if (y in 100..149.s) { // B
					return 199 toP y - 100 to (0 toP 0).up
				}
			} else if (x == 99.s && y in 0..49.s) { // D -> C
				return 50 + y toP 50 to (0 toP 0).right
			}
		}
	}

	error ( p to dir)


}

// AB
// C
//DE
//F

val loc = mapOf(
'A' to (0 toP 1),
'B' to (0 toP 2),
'C' to (1 toP 1),
'D' to (2 toP 0),
'E' to (2 toP 1),
'F' to (3 toP 0),
)

val rLoc = loc.entries.associate { (v,k) -> k to v }

fun getLet(p: Point) = rLoc[p.x / 50 toP p.y / 50] ?: error(loc)

fun getUp(let: Char): Line {
	val (x, y) = loc[let] ?: error(let)
	return (50 * x toP 50 * y) toL (50 * x toP 50 * y + 49)
}

fun getRight(let: Char): Line {
	val (x, y) = loc[let] ?: error(let)
	return (50 * x toP 50 * y + 49) toL (50 * x + 49 toP 50 * y + 49)
}

fun getDown(let: Char): Line {
	val (x, y) = loc[let] ?: error(let)
	return (50 * x + 49 toP 50 * y + 49) toL (50 * x + 49 toP 50 * y)
}

fun getLeft(let: Char): Line {
	val (x, y) = loc[let] ?: error(let)
	return (50 * x + 49 toP 50 * y) toL (50 * x toP 50 * y)
}

// AB
// C
//DE
//F

val trans = mapOf(
	'A' to mapOf(
		(0 toP 0).right to ('B' to (0 toP 0).right),
		(0 toP 0).down to ('C' to (0 toP 0).down),
		(0 toP 0).left to ('D' to (0 toP 0).right),
		(0 toP 0).up to ('a' to (0 toP 0).up),
	)
)


fun main() {
	println("Day 22: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		// .also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}