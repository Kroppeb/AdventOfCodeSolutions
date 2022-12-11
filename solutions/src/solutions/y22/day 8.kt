@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d08


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


import me.kroppeb.aoc.helpers.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_08).e().map2 { it - '0' }

	val seen = msot<Pair<Int, Int>>()
	var size = -1
	for (i in data.indices) {
		for (j in 0 until data[i].size) {
			var x = data[i][j]
			if (x > size) {
				size = x
				seen += i to j
			}
		}

		size = -1
		for (j in (0 until data[i].size).reversed()) {
			var x = data[i][j]
			if (x > size) {
				size = x
				seen += i to j
			}
		}
	}

	data = data.transpose()
	size = -1

	for (i in data.indices) {
		for (j in 0 until data[i].size) {
			var x = data[i][j]
			if (x > size) {
				size = x
				seen += j to i
			}
		}

		size = -1
		for (j in (0 until data[i].size).reversed()) {
			var x = data[i][j]
			if (x > size) {
				size = x
				seen += j to i
			}
		}
	}

	seen.size.log()
}


private fun part2() {
	var data = getLines(2022_08).e().map2 { it - '0' }
	data.indices.maxOf { u -> data[0].indices.maxOf { v -> extracted(data, u, v, data[u][v]) } }.log()


}

private fun extracted(
	data: List<List<Int>>,
	u: Int,
	v: Int,
	max: Int
): Int {
	var data1 = data
	var mul = 1
	var size = -1
	var count = 0
	for (j in (v + 1) until data1[u].size) {
		var x = data1[u][j]

		count++

		if (x >= max) {
			break
		}
	}
	mul *= count

	size = -1
	count = 0
	for (j in (0 until v).reversed()) {
		var x = data1[u][j]

		count++
		if (x >= max) {
			break
		}
	}

	data1 = data1.transpose()

	mul *= count

	size = -1
	count = 0

	for (j in (u + 1) until data1[v].size) {
		var x = data1[v][j]

		count++
		if (x >= max) {
			break
		}
	}


	mul *= count

	size = -1
	count = 0
	for (j in (0 until u).reversed()) {
		var x = data1[v][j]
//		if (x > size) {
//			size = x
		count++
//		}
		if (x >= max) {
			break
		}
	}


	mul *= count

	size = -1
	count = 0

	return mul
}


fun main() {
	println("Day 8: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
//	.also { setClipboard(it.toString()) }

private infix fun <T> T.log(_ignored: Any?): T = this.log()
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}