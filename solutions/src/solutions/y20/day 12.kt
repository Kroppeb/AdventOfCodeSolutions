@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d12

import collections.*
import grid.*
import helpers.*
import kotlinx.coroutines.*

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	var ours = 0 toP 0
	var pos = 0 toP 0
	var rot = pos.right
	pos += rot * 10
	pos = pos.up
	val Z = 0 toP 0
	for(i in data){
		val c = i[0]
		val m = i.substring(1).toInt()
		when(c.toLowerCase()){
			's' -> pos += Z.down * m
			'e' -> pos += Z.right * m
			'w' -> pos += Z.left * m
			'n' -> pos += Z.up * m
			'r' -> when(m){
				// these rotates had to be reversed on the day itself due to a bug.
				90 -> pos = pos.rotateClock()
				270 -> pos = -pos.rotateClock()
				180 -> pos = -pos
				else -> error(m)
			}
			'l' -> when(m){
				90 -> pos = -pos.rotateClock()
				270 -> pos = pos.rotateClock()
				180 -> pos = -pos
				else -> error(m)
			}
			'f' -> {
				val n = ours + pos * m
				//pos = (n - pos * (m - 1))
				ours = n
			}
		}
		println("${pos} ${ours}")
	}
	println(ours
.manDist())
}

private fun part2(data: Data) {
}

private typealias Data = List<String>

fun main() {
	val data: Data = getLines(12)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)