@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d22c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.dijkstra
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.grid
import me.kroppeb.aoc.helpers.point.*
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2021_22)


	var p = BoundsTree<Bounds3D, Boolean>(Bounds3D.INFINITE, false)
//	val asReversed = data.filter { it.startsWith("on") }.asReversed()
//	asReversed.size log 0
//	for (s in asReversed) {
//		p.add(s.sints(){it.chunked(2).transpose().map{(a,b,c) -> a toP b toP c}}.bounds())
//		p.fractureCount log 0
//	}

	for (ling in data) {
		val x = ling.sints(){it.chunked(2).transpose().map{(a,b,c) -> a toP b toP c}}.bounds()

		if (ling.startsWith("on")) {
			p.set(x, true)
		} else {
			p.set(x, false)
		}
		ling log 0
		p.count { it } log 1
	}

}

private fun part2() {

}


fun main() {
	println("Day 15: ")
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