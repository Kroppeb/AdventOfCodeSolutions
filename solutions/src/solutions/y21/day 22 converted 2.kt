@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d22c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.*
import me.kroppeb.aoc.helpers.sint.sumOf
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection

private val xxxxx = Clock(6, 3);

/*

*/


private fun part1() {
	var data = getLines(2021_22)
	var bb = data.sints().map { it.chunked(2) { it.minMaxIR() } }
		.map { (a, b, c) -> Bounds3D(a, b, c) }
		.map { it.intersect((-50 toP -50 toP -50) toB (50 toP 50 toP 50)) }
		.filter { it.isNotEmpty() }

	val p = msop3()

	for ((s, bb) in data zip bb) {
		s log 0
		bb log 0

		if (s.startsWith("on")) {
			p += bb
		} else {
			p -= bb
		}
	}

	p.size log 1
}

private fun part2() {
	var data = getLines(2021_22)
	var bb = data.sints().map { it.chunked(2) { it.minMaxIR() } }
		.map { (a, b, c) -> Bounds3D(a, b, c) }
//		.map { it.intersect((-50 toP -50 toP -50) toB (50 toP 50 toP 50)) }
//		.filter { it.isNotEmpty() }

	var p = mapOf(bb.reduce{a,b -> a.merge(b)} to false)

	for ((s, bb) in data zip bb) {
		s log 0
		bb log 0

		if (s.startsWith("on")) {
			fun fix(b: Bounds3D): List<Pair<Bounds3D, Boolean>> {
				if (b in bb) {
					return listOf(b to true)
				}
				if (b.doesIntersect(bb)) {
					return bb.fracture(b).flatMap { fix(it) }
				} else {
					return listOf(b to false)
				}
			}

			p = p.flatMap { (b, v) ->
				if (v) {
					listOf(b to v)
				} else {
					fix(b)
				}
			}.toMap()
		} else {
			fun fix(b: Bounds3D): List<Pair<Bounds3D, Boolean>> {
				if (b in bb) {
					return listOf(b to false)
				}
				if (b.doesIntersect(bb)) {
					return bb.fracture(b).flatMap { fix(it) }
				} else {
					return listOf(b to true)
				}
			}

			p = p.flatMap { (b, v) ->
				if (!v) {
					listOf(b to v)
				} else {
					fix(b)
				}
			}.toMap()
		}

		p.size log 0
	}

	p.filter{it.value}.keys.sumOf { it.volume } log 1
}


fun main() {
	println("Day 22: ")
//	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(meta: String = ""): T =
	also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
		.also { if (meta in listOf("1", "2")) setClipboard(it.toString()) }

private infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
private fun setClipboard(data: Any?) {
	val s: String = if (data is Loggable) data.getCopyString() else data.toString()
	val selection = StringSelection(s)
	val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
//	clipboard.setContents(selection, selection)
}