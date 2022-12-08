@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d05cfm

/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import grid.*
import helpers.*
import kotlinx.coroutines.*
import java.nio.file.Files
import java.nio.file.Path


// get current time
var startLoad = System.currentTimeMillis()

class BinTree(var value: Char) {
	var left: BinTree? = null
	var right: BinTree? = null
	var size: Int = if (value == '0') 0 else 1
	var depth: Int = 0
	var flipped: Boolean = false


	fun takeLastN(n: Int): Pair<BinTree?, BinTree> {
		doReverse()

		val right = right
		val left = left
		this.checkSize()
		if (n > size) error("errrr")

		val res = when {
			right != null && n < right.size -> {
				val (l, r) = right.takeLastN(n)
				this.right = l
				size -= n
				this.updateDepth()
				this.checkSize()
				l?.checkSize()
				Pair(this, r)
			}

			right != null && n == right.size -> {
				this.right = null
				size -= n
				this.updateDepth()
				this.checkSize()
				right.checkSize()
				Pair(this, right)
			}

			right != null && n - 1 == right.size -> {
				this.left = null
				this.size = n
				this.updateDepth()
				left?.checkSize()
				this.checkSize()
				left to this
			}

			right != null && n - 1 > right.size -> {
				val (l, r) = left!!.takeLastN(n - 1 - right.size)
				this.left = r
				size = n
				this.updateDepth()
				l?.checkSize()
				this.checkSize()
				l to this
			}

			right == null && n == 1 -> {
				this.left = null
				this.size = n
				this.updateDepth()
				left?.checkSize()
				this.checkSize()
				left to this
			}

			right == null && n > 1 -> {
				val (l, r) = left!!.takeLastN(n - 1)
				this.left = r
				size = n
				this.updateDepth()
				l?.checkSize()
				this.checkSize()
				l to this
			}

			else -> error("errrr")
		}

		res.first?.checkSize()
		res.second.checkSize()
		return res
	}

	fun updateDepth() {
		this.depth = (left?.depth ?: 0).coerceAtLeast((right?.depth ?: 0)) + 1
	}

	fun checkSize(): Int {
		 if (true) return size
		if (this.value == '0') {
			if (this.left != null || this.right != null) error("errrr")
			return 0
		}

		if (this.depth <= (left?.depth ?: 0)) error("errrr")
		if (this.depth <= (right?.depth ?: 0)) error("errrr")
		val left = left
		val right = right
		val size = size
		val leftSize = left?.checkSize() ?: 0
		val rightSize = right?.checkSize() ?: 0
		if (size != leftSize + rightSize + 1) error("errrr: $size != $leftSize + $rightSize + 1")


		return size
	}

	fun last(): Char {
		if (this.flipped) {
			return left?.first() ?: value
		} else {
			return right?.last() ?: value
		}
	}

	private fun first(): Char {
		if (this.flipped) {
			return right?.last() ?: value
		} else {
			return left?.first() ?: value
		}
	}

	fun getAllChildren(): List<BinTree> {
		if (true) return emptyList()
//		doReverse()
		return (left?.getAllChildren() ?: emptyList()) + listOf(this) + (right?.getAllChildren() ?: emptyList())
	}

	fun reverse() {
		this.flipped = !this.flipped
	}

	internal fun doReverse() {
		if (this.flipped) {
			val tmp = left
			left = right
			right = tmp
			this.flipped = false

			left?.reverse()
			right?.reverse()
		}
	}

	override fun toString(): String {
		return "BinTree(value=$value, size=$size, depth=$depth, flipped=$flipped){\n" + "  left=${
			left
				?.toString()
				?.prependIndent("  ")
		}\n" + "  right=${right?.toString()?.prependIndent("  ")}\n" + "}"
	}
}

fun BinTree?.mergeWith(other: BinTree): BinTree {
	if (this == null) return other
	doReverse()
	other.doReverse()
	this.checkSize()
	other.checkSize()
	if (depth >= other.depth) {
		if (right == null) {
			right = other
			size += other.size
			depth = depth.coerceAtLeast(other.depth + 1)
			this.checkSize()
		} else {
			right = right!!.mergeWith(other)
			size = right!!.size + (left?.size ?: 0) + 1
			depth = depth.coerceAtLeast(right!!.depth + 1)
			this.checkSize()
		}
		return this
	} else {
		if (other.left == null) {
			other.left = this
			other.size += size

			other.checkSize()
		} else {
//				println("${this.size} ${other.size} ${other.left!!.size}")
			other.left = this.mergeWith(other.left!!)
//				println("${this.size} ${other.size} ${other.left!!.size}")
			other.size = other.left!!.size + (other.right?.size ?: 0) + 1
//				println("${this.size} ${other.size} ${other.left!!.size}")
			other.depth = other.depth.coerceAtLeast(other.left!!.depth + 1)
			other.left!!.checkSize()
			other.checkSize()
		}
		return other

	}
}

//private val xxxxx = Clock(6, 3);

private fun part1() = runBlocking {
	launch(Dispatchers.Default) {
		run {
			val time = System.currentTimeMillis() - startLoad
			println("start p1 at $time ms")
		}

		val q = ss.map { async(Dispatchers.Default) { createTree(it) } }.mut()

		run {
			val time = System.currentTimeMillis() - startLoad
			println("start p1 moves at $time ms")
		}

		val ints = steps.ints()
		for (ii in ints.indices) {
			val (a, b, c) = ints[ii]
			val bx = q[b - 1].await()!!
			val cx = q[c - 1].await()

			val res = async { bx.takeLastN(a) }
			val rem = async { res.await().first }
			val mov = async { res.await().second.also { it.reverse() } }

			var merged = async { cx.mergeWith(mov.await()) }

			q[b - 1] = rem
			q[c - 1] = merged
		}

		q.awaitAll().map { it!!.last() }.join().log()


		run {
			val time = System.currentTimeMillis() - startLoad
			println("finished p1 at $time ms")
		}
	}.join()
}


private fun part2() {
	run {
		val time = System.currentTimeMillis() - startLoad
		println("start p2 at $time ms")
	}

	val q = ss.map { createTree(it) }.mut()


	run {
		val time = System.currentTimeMillis() - startLoad
		println("start p2 moves at $time ms")
	}

	val ints = steps.ints()
	for (ii in ints.indices) {
		val (a, b, c) = ints[ii]
		var ax = q[b - 1]
		var bx = q[c - 1]

		ax!!.checkSize()
		bx?.checkSize()

		if (ax.getAllChildren() anyIn (bx?.getAllChildren() ?: emptyList())) {
			error("errrr")
		}

		var (rem, mov) = ax.takeLastN(a)
		rem?.checkSize()
		mov.checkSize()

		var merged = bx.mergeWith(mov)
		merged.checkSize()

		q[b - 1] = rem
		q[c - 1] = merged


//		q.map { it?.last() ?: '0' }.join().log()
	}

	q.map { it!!.last() }.join().log()


	run {
		val time = System.currentTimeMillis() - startLoad
		println("finished p2 at $time ms")
	}

}

fun createTree(items: List<Char>): BinTree? {
	if (items.isEmpty()) return null
	val mid = items.size / 2

	val tree = BinTree(items[mid])

	if (mid > 0) {
		tree.left = createTree(items.subList(0, mid))
		if (tree.left!!.size != mid) error("errrr")
		tree.left!!.checkSize()
	}

	if (mid < items.size - 1) {
		tree.right = createTree(items.subList(mid + 1, items.size))
		if (tree.right!!.size != items.size - mid - 1) error("errrr")
		tree.left!!.checkSize()
	}

	tree.size = items.size
	tree.updateDepth()
	tree.checkSize()

	return tree
}


fun main() {
	println("Day 5: ")
	part1()
	part2()
}

fun getInput(): List<String> {
	val path = Path.of("C:\\Users\\robbe\\Desktop\\day5.txt\\day5.txt")
	val lines = Files.readAllLines(path)
	val time = System.currentTimeMillis() - startLoad
	println("Loaded input at $time ms")
	return lines
//	return getLines(2022_05)
}

var inp = getInput()
var rrr = inp.splitOnEmpty()
var stack = rrr[0]
var steps = rrr[1]
val s = stack.e().asReversed().drop(1).map{it.chunked(4) { it[1] }}.transpose()
val ss = s.map { it.filter { it != ' ' } }.also {
	val time = System.currentTimeMillis() - startLoad
	println("Parsed input at $time ms")
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }