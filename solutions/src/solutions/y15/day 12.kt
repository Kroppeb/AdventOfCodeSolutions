@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d12

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import com.beust.klaxon.Parser
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2015_12).ints().flatten().sum().log()

}

private fun part2() {
	var data = getData(2015_12)
	var d = Parser.default().parse(StringBuilder(data))!!

	fun process(obj: Any?): Int {
		if (obj is Int) {
			return obj
		} else if (obj is List<*>) {
			return obj.sumBy { process(it) }
		} else if (obj is Map<*, *>) {
			if (obj.values.none { it == "red" }) {
				return obj.values.sumBy { process(it!!) }
			}
		}

		return 0
	}

	process(d).log()
}


fun main() {
	println("Day 12: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }