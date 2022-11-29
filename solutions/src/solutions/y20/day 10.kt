@file:Suppress("PackageDirectoryMismatch")
package solutions.y20.d10

import helpers.*
import collections.*
import grid.*
import kotlinx.coroutines.*

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	val d = (data + (data.max() + 3)) .sorted()
	var u = 0
	var u1 = 0
	var u2 = 0
	var i = 0
	while(i < d.size){
		if(d[i] - u in 1..3){
			if(d[i] == u + 1)
				u1++
			if(d[i] == u + 3)
				u2++
			u = d[i]
		}


		i++
	}
	println(u1 * u2)
}

private fun part2(data: Data) {
	val end = data.max() + 3
	val d = (data + end) .sorted()
	var u = 0
	var u1 = 0
	var u2 = 0
	var u3 = 0
	var i = 0
	val mm = mutableMapOf<Int, Long>(0 to 1)
	while(i < d.size){
		val y = d[i]
		val x = y - u
		for(dif in 1..3)
			mm[y] = (mm[y]?:0) + (mm[y-dif]?:0)
		u = y
		i++
	}
	println(mm[end])
}

private typealias Data = Ints

fun main() {
	val data: Data = getInts(2020_10)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)