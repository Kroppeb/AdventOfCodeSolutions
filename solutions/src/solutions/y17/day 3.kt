@file:Suppress("PackageDirectoryMismatch", "PackageName")
package solutions.y17.d03

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toPI
import kotlin.math.abs

private val xxxxx = Clock(3,6);

private fun part1(data: Data) {
	var d = 0
	var x = 1
	while(x * x < data){
		x += 2
		d += 1
	}

	val side = (data - (x - 2) * (x - 2)) % (x - 1)
	val rem = abs(side - d) + d
	println(rem)
}

private fun part2(data: Data)  {
	val g = mutableMapOf<PointI, Int>(0 toPI 0 to 1)
	var c = 0 toPI 0
	var r = 0
	while(true){
		r++
		c = c.right.down
		repeat(r*2){
			c = c.up
			println(c)
			g[c]=c.getMooreNeighbours().sumBy { g[it]?:0 }
		}
		repeat(r*2){
			c = c.left
			println(c)
			g[c]=c.getMooreNeighbours().sumBy { g[it]?:0 }
		}
		repeat(r*2){
			c = c.down
			println(c)
			g[c]=c.getMooreNeighbours().sumBy { g[it]?:0 }
		}
		repeat(r*2){
			c = c.right
			println(c)
			g[c]=c.getMooreNeighbours().sumBy { g[it]?:0 }
		}
		if(g[c]!! > data){
			println(g.entries.first{it.value > data}.value)
			return
		}
	}
}

private typealias Data = Int


fun main() {
	val data: Data = 361527
	part1(data)
	part2(data)
}