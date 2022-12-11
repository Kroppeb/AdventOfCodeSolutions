@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d23

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.bounds
import me.kroppeb.aoc.helpers.point.toP
import java.util.Comparator
import java.util.PriorityQueue


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2018_23).ints().map { (a, b, c, z) -> a toP b toP c to z }

	var max = data.maxBy { it.second }

	var count = data.count { (a, _) -> (max.first.manDistTo(a)) <= max.second }.log()

}

private fun part2() {
	var data = getLines(2018_23).ints().map { (a, b, c, z) -> a toP b toP c to z }

	var xsRange = data.map { (a) -> a.x toP 0 }.bounds().xs
	var ysRange = data.map { (a) -> a.y toP 0 }.bounds().xs
	var zsRange = data.map { (a) -> a.z toP 0 }.bounds().xs

	fun dist(range: IntRange, v: Int): Int {
		if (v in range) return 0
		if (v < range.first) return range.first - v
		if (v > range.last) return v - range.last
		else error("")
	}

	val queue = PriorityQueue<Pair<Pair<Int, Int>, List<IntRange>>>(
		Comparator
			.comparingInt<Pair<Pair<Int, Int>, List<IntRange>>?> { -it.first.first }
			.thenComparingInt { it.first.second })

	queue.add(0 to 0 to listOf(xsRange, ysRange, zsRange))

	var best = 0 to 10000000

	while (queue.isNotEmpty()) {
		val (score, ranges) = queue.poll()
		if (score.first < best.first) {
			continue
		}

		val (xs, ys, zs) = ranges

		if (xs.first == xs.last && ys.first == ys.last && zs.first == zs.last) {
			if (score.first > best.first) {
				best = score
			} else if (best.second > score.second) {
				best = score
			}
			continue

		}

		val xs1 = xs.first until (xs.first + (xs.last - xs.first + 1) / 2)
		val xs2 = (xs1.last + 1)..xs.last

		val ys1 = ys.first until (ys.first + (ys.last - ys.first + 1) / 2)
		val ys2 = (ys1.last + 1)..ys.last

		val zs1 = zs.first until (zs.first + (zs.last - zs.first + 1) / 2)
		val zs2 = (zs1.last + 1)..zs.last

		for (xx in listOf(xs1, xs2).filter { it.last >= it.first })
			for (yy in listOf(ys1, ys2).filter { it.last >= it.first })
				for (zz in listOf(zs1, zs2).filter { it.last >= it.first }) {
					val count =
						data.count { dist(xx, it.first.x) + dist(yy, it.first.y) + dist(zz, it.first.z) <= it.second }

					val d = dist(xx, 0) + dist(yy, 0) + dist(zz, 0)
					queue.add(count to d to listOf(xx,yy,zz))
				}
	}

	best.second.log()
}


fun main() {
	println("Day 23: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }