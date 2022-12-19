@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22


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
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2022_18).point3D().toSet()

	data.sumOf { it.getHexNeighbours().count { it !in data }.s } log 1
}


private fun part2() {
	var data = getLines(2022_18).point3D().toSet()

	var b = data.bounds()

	b = b.lower - (3 toP 3 toP 3) toB b.higher + (3 toP 3 toP 3)

	val accessible = mutableSetOf<Point3D>()
	val stack = mutableListOf<Point3D>(-1 toP -1 toP -1)

	while (stack.isNotEmpty()) {
		val cur = stack.removeLast()

		if (cur in accessible) continue
		if (cur !in b) continue
		if (cur in data) continue

		accessible.add(cur)

		stack += cur.getHexNeighbours()
	}

	data.sumOf { it.getHexNeighbours().count { it in accessible }.s } log 2
}


fun main() {
	println("Day 18: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(s: String) {
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
	clipboard.setContents(selection, selection)
}