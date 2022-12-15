@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d17

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.BoundsI
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.bounds
import me.kroppeb.aoc.helpers.point.toPI


private val xxxxx = Clock(3, 6);


private fun part1() {
	var data = getLines(2018_17)
	var clay = mutableSetOf<PointI>()

	for (line in data) {
		val (a, b) = line.split(" ")
		val (u, v, w) = line.ints()
		if (a[0] == 'x') {
			for (y in v..w) {
				clay.add(PointI(u, y))
			}
		} else {
			for (x in v..w) {
				clay.add(PointI(x, u))
			}
		}
	}

	val bounds_ = clay.bounds()
	val ys = bounds_.ys
	var xs_ = bounds_.xs
	val xs = (xs_.first - 1)..(xs_.last + 1)
	val bounds = BoundsI(xs = xs, ys = ys)

	val water = mutableMapOf<PointI, Boolean>()

	fun check(p: PointI){
		fun checkLeft(p: PointI, mark: Boolean): Boolean {
			if (p !in bounds) error("wu")
			if (p in clay) return true
			if (p.down in clay) {
				water[p] = mark
				return checkLeft(p.left, mark)
			} else {
				check(p.down)
			}
			if (water[p.down] == true) {
				water[p] = mark
				return checkLeft(p.left, mark)
			}
			water[p] = false
			return false
		}

		fun checkRight(p: PointI, mark: Boolean): Boolean {
			if (p !in bounds) error("wu")
			if (p in clay) return true
			if (p.down in clay) {
				water[p] = mark
				return checkRight(p.right, mark)
			} else {
				check(p.down)
			}
			if (water[p.down] == true) {
				water[p] = mark
				return checkRight(p.right, mark)
			}
			water[p] = false
			return false
		}

		if (Math.random() * 10_000 < 1) {
			"check $p".log()
		}

		if (p.y > ys.last) return
		if (p in clay) return
		if (water[p] == true) return
		if (water[p] == false) return

		check(p.down)

		if (water[p.down] == true || p.down in clay) {
			if (checkLeft(p.left, false) and
				checkRight(p.right, false)
			) {
				checkLeft(p.left, true)
				checkRight(p.right, true)
				water[p] = true
			} else {
				water[p] = false
			}
		} else {
			water[p] = false
		}
	}

	check(500 toPI 0)



	bounds.ys.forEach {y->
		bounds.xs.forEach { x ->
			when{
				(x toPI y) in clay -> print('#')
				water[(x toPI y)] == true -> print('~')
				water[(x toPI y)] == false -> print('|')
				else -> print('.')
			}
		}
		println()
	}

	bounds.log()
	water.keys.filter{it in bounds}.size.log()
	water.entries.filter { (_,v) -> v }.filter{(k, _) -> k in bounds}.size.log()

}

private fun part2() {


}


fun main() {
	println("Day 17: ")
	part1()
	// part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }