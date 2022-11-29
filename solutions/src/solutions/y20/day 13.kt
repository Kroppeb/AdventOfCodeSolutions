@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d13

import grid.Clock
import helpers.Lines
import helpers.gcd
import helpers.getInts
import helpers.getLines

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val start = data[0].toInt()
	val ll = data[1].getInts()
	ll.map {
		it to start + it - (start % it) - start
	}.minBy { it.second }!!.let {
		it.log();
		println(it.first * it.second)
	}
}

private fun part2(data: Data) {
	val ll = data[1].split(',')
	var off: Long = 0
	var mul: Long = 1
	ll.forEachIndexed { i, l ->
		if (l != "x") {
			val lo = l.toLong()
			var j = 0
			while(true){
				if((off + mul * j) % lo == (lo * 100 - i.toLong()) % lo){
					off += mul * j
					mul *= lo
					break
				}
				j++
			}
			println("$off, $mul")
		}
	}
	println(off)
}

fun egcd(a:Long, b:Long, aa:Long = 1L, ab:Long = 0L, ba:Long = 0L, bb:Long = 1L): Pair<Long, Long> = if(a == 0L) (ba to bb) else{
	println(" $a, $b")
	val rem = b % a
	val div = b / a

	egcd(rem, a, ba - div * aa, bb - div * ab, aa, ab)
}


private typealias Data = Lines

fun main() {
	val data: Data = getLines(13)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)