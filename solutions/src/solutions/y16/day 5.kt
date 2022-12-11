@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d05


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
import java.security.MessageDigest
import kotlin.math.*


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_05).first()
	var chars = mlot<Char>()
	loop{ i ->
		val h = md5(data + i)
		if (h[0] == 0.toByte() && h[1] == 0.toByte() && h[2].toInt() in 0..0x0f){
			chars += h[2].toString(16).also{require(it.length == 1)}[0]
		}

		if (chars.size == 8) {
			chars.joinToString(separator = "").log()
			return
		}
	}


}

private fun part2() {
	var data = getLines(2016_05).first()
	var chars = arrayOfNulls<Char>(8)
	loop{ i ->
		val h = md5(data + i)
		if (h[0] == 0.toByte() && h[1] == 0.toByte() && h[2].toInt() in 0..0x07){
			val indx = h[2].toInt()
			if (chars[indx] == null)
			chars[indx] = (h[3].let{if (it < 0.toByte()) (it.toInt() + 0x100) else it.toInt()} shr 4).toString(16).also{require(it.length == 1)}[0]
		}

		if (chars.all { it != null }) {
			chars.joinToString(separator = "").log()
			return
		}
	}
}

fun md5(input:String): ByteArray {
	val md = MessageDigest.getInstance("MD5")
	return md.digest(input.toByteArray())
}

fun main() {
	println("Day 5: ")
	//part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }