@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d20


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
import java.util.Comparator
import java.util.PriorityQueue
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
	var data = getLines(2022_20).int()

	class Node(val value: Int) {
		lateinit var prev: Node
		lateinit var next: Node
	}

	val nodes = data.map { Node(it * 811589153) }

	for ((a, b) in nodes.zipWithNext()) {
		a.next = b
		b.prev = a
	}

	nodes.first().prev = nodes.last()
	nodes.last().next = nodes.first()

	var first = nodes.single { it.value == 0 }

	for (node in nodes) {
		val steps = node.value
		if (steps == 0) {
			continue
		}

		node.prev.next = node.next
		node.next.prev = node.prev

		var dest = node

		if (steps > 0) {
			repeat(steps) {
				dest = dest.next
			}

			node.next = dest.next
			node.prev = dest
			dest.next.prev = node
			dest.next = node
		} else {
			repeat(-steps) {
				dest = dest.prev
			}

			node.next = dest
			node.prev = dest.prev
			dest.prev.next = node
			dest.prev = node
		}

//		generateSequence(first) {it.next.takeIf { it != first }}.joinToString() {it.value.toString()}.let(::println)
	}
//		val seq = generateSequence(first) {it.next.takeIf { it != first }}.map{it.value}.toList() log 0

	val seq = generateSequence(first) { it.next.takeIf { it != first } }.filterIndexed { index, node ->
		index == 1000 % data.size || index == 2000 % data.size || index == 3000 % data.size
	}.sumOf { it.value } log 1

}

private fun part2() {
	var data = getLines(2022_20).sint()

	class Node(val value: Sint) {
		lateinit var prev: Node
		lateinit var next: Node
	}

	val nodes = data.map { Node(it * 811589153) }

	for ((a, b) in nodes.zipWithNext()) {
		a.next = b
		b.prev = a
	}

	nodes.first().prev = nodes.last()
	nodes.last().next = nodes.first()

	var first = nodes.single { it.value == 0.s }

	repeat(10) {
		for (node in nodes) {
			val steps = node.value % (data.size - 1)
			if (steps == 0.s) {
				continue
			}

			node.prev.next = node.next
			node.next.prev = node.prev

			var dest = node

			if (steps > 0) {
				repeat(steps) {
					dest = dest.next
				}

				node.next = dest.next
				node.prev = dest
				dest.next.prev = node
				dest.next = node
			} else {
				repeat(-steps) {
					dest = dest.prev
				}

				node.next = dest
				node.prev = dest.prev
				dest.prev.next = node
				dest.prev = node
			}

//		generateSequence(first) {it.next.takeIf { it != first }}.joinToString() {it.value.toString()}.let(::println)
		}
//		val seq = generateSequence(first) {it.next.takeIf { it != first }}.map{it.value}.toList() log 0
	}

	val seq = generateSequence(first) { it.next.takeIf { it != first } }.filterIndexed { index, node ->
		index == 1000 % data.size || index == 2000 % data.size || index == 3000 % data.size
	}.sumOf { it.value.l } log 2

}


fun main() {
	println("Day 20: ")
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