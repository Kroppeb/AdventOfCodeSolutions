@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d09


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_09).e().first()

	var decomp = mlot<Char>()

	var i = 0
	while (i < data.size) {
		if (data[i] != '('){
			decomp += data[i]
			i++
		} else {
			var c = ""
			while (data[++i] != 'x') {
				c += data[i]
			}
			var r = ""
			while (data[++i] != ')') {
				r += data[i]
			}
			var cc = c.toInt()
			var rr = r.toInt()

			decomp += data.drop(++i).take(cc).repeat(rr)
			i += cc
		}
	}

	decomp.size.log()
}

sealed class Comp
data class Marker(val c: List<Comp>, val r:Int, val l:Int) : Comp()
data class Data(val l: Int) : Comp()

private fun part2() {
	var data = getLines(2016_09).e().first()




	var i = 0
	fun parse(max: Int): List<Comp> {
		val decomp = mlot<Comp>()
		while (i < max) {
			if (data[i] != '(') {
				if (decomp.size > 0 && decomp.last() is Data) {
					decomp[decomp.size - 1] = Data((decomp.last() as Data).l + 1)
				} else {
					decomp += Data(1)
				}
				i++
			} else {
				var c = ""
				val s = i
				while (data[++i] != 'x') {
					c += data[i]
				}
				var r = ""
				while (data[++i] != ')') {
					r += data[i]
				}
				i++
				var cc = c.toInt()
				var rr = r.toInt()
				val l = i - s

				decomp += Marker(parse(i + cc), rr, l)
			}
		}
		return decomp
	}

	var decomp = parse(data.size)

	fun length(l: List<Comp>):Long = l.map { when(it) {
		is Data -> it.l.toLong()
		is Marker -> length(it.c) * it.r.toLong()
	} }.sum()

	length(decomp).log()
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }