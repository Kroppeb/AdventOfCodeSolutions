@file:Suppress("PackageDirectoryMismatch")

package solutions.y20

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.collections.*

import kotlinx.coroutines.*

private val xxxxx = Clock(3, 6);

private fun part1(data: Data) {
	data.windowed(26).find{
		it.subList(0,25).pairWise().find{(a,b) -> a + b == it.last()} == null
	}.let{println(it)}
}

private fun part2(data: Data) {
	val target = 88311122L
	var start = 0
	var end = 0
	var s = data[0]
	while(s != target){
		if(s < target){
			s += data[++end]
		} else {
			s -= data[start++]
		}
	}
	val p = data.subList(start, end +1)
	println(p.min() + p.max())
}

private typealias Data = Longs

fun main() {
	val data: Data = getLongs(2020_09)
	part1(data)
	part2(data)

}