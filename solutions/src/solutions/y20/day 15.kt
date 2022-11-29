@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d15

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val hist = mutableMapOf<Int,Int>()
	data.subList(0,data.lastIndex).forEachIndexed { index, i -> hist[i] = index }
	//println(hist)
	var c = data.size - 1;
	var n = data[data.size-1]
	while(c < 30000000 - 1){
		val last = hist[n]
		if(last == null){
			hist[n] = c
			n = 0
		} else {
			hist[n] = c
			n = c - last
		}
		c++
		//println(hist)
	}
	println(n)
}


private fun part2(data: Data) {

}


private typealias Data = Ints

fun main() {
	val data: Data = getInts(2020_15)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)