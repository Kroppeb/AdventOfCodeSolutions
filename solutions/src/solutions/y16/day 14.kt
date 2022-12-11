@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d14


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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.security.MessageDigest
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_14).first()

	var c = 0
	loop {
		var h = md5h(data + it)

		val p = h.windowed(3).find { it.size == 3 && it[0] == it[1] && it[1] == it[2] } ?: return@loop
		repeat(1000) { x ->
			val h2 = md5h(data + (it + x + 1))

			if (h2
					.windowed(5)
					.any { it.size == 5 && p[0] == it[0] && it[0] == it[1] && it[1] == it[2] && it[2] == it[3] && it[3] == it[4] }
			) {
				c++
				if (c == 64) {
					it.log()
					return
				}
				return@loop
			}
		}
	}

}

private fun part2():Unit = runBlocking{
	var data = getLines(2016_14).first()

	var c = 0
	var cache = mutableMapOf<Int, Deferred<List<Char>>>()
	loop {
		var h = cache.getOrPut(it) { async{xHash(data + it, 2016) }}.await()
		if(it % 1000 == 0) {
			println(":$it")
			repeat(1000) { x ->
				cache.computeIfAbsent(it + x + 1) {  async(Dispatchers.Default){xHash(data + it, 2016) }}
			}
		}

		val p = h.windowed(3).find { it.size == 3 && it[0] == it[1] && it[1] == it[2] } ?: return@loop

		repeat(1000) { x ->
			cache.computeIfAbsent(it + x + 1) {  async(Dispatchers.Default){xHash(data + it , 2016) }}
		}
		repeat(1000) { x ->
			val h2 = cache[it + x + 1]!!.await()

			if (h2
					.windowed(5)
					.any { it.size == 5 && p[0] == it[0] && it[0] == it[1] && it[1] == it[2] && it[2] == it[3] && it[3] == it[4] }
			) {
				c++
				(c to it to p).log()
				if (c == 64) {
					it.log()
					return@runBlocking
				}
				return@loop
			}
		}
	}
}


fun md5(input: String): ByteArray {
	val md = MessageDigest.getInstance("MD5")
	return md.digest(input.toByteArray())
}

fun md5h(input: String) = md5(input)
	.map { if (it < 0) it.toInt() + 0x100 else it.toInt() }
	.flatMap { it.toString(16).e().let { if (it.size < 2) listOf('0', it[0]) else it } }

fun xHash(input: String, count: Int): List<Char> =
	if (count == 0) md5h(input) else xHash(md5h(input).joinToString(""), count - 1)


fun main() {
	println("Day 14: ")
	// part1()
	 part2()
//	xHash("abc0", 2016).log()
//	xHash("abc0", 1).log()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }