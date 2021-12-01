@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d10

import helpers.*
import kotlin.math.*

private fun ggd(a:Int, b:Int):Int = if(a == 0) b else ggd(b % a, a)

private fun part1(data: List<List<Boolean>>) {
	(data.indices).map { x ->
		(data[x].withIndex().filter { it.value }).map { (y,_) ->
			val pp = data.mapIndexed { x2, l ->
				l.withIndex().filter { it.value && (x != x2 || y != it.index) }.count { (y2, _) ->
					val dx = x2 - x
					val dy = y2 - y
					val g = ggd(abs(dx), abs(dy))
					val sx = dx / g
					val sy = dy / g
					val q = (1 until g).all {
						!data[x + sx * it][y + sy * it]
					}
					q
				}
			}.sum() to y

			pp
		}.maxByOrNull{it.first}!! to x
	}.maxByOrNull{it.first.first}!!.let{println("${it.second}, ${it.first.second}: ${it.first.first}")}
}

private fun fix(i:Double) = if(i < 0) i +100 else i

private fun part2(data: List<List<Boolean>>) {
	val x = 22
	val y = 17
	data.mapIndexed { x2, l ->
		l.withIndex().filter { it.value && (x2 != x || it.index != y) }.map { (y2, _) ->
			val dx = x2 - x
			val dy = y2 - y
			val g = ggd(abs(dx), abs(dy))
			val sx = dx / g
			val sy = dy / g
			val q = (1..g).count {
				data[x + sx * it][y + sy * it]
			}

			(fix(atan2(sy.toDouble(), -sx.toDouble())) to q) to (x2 to y2)
		}
	}.flatten().sortedBy{it.first.first}.sortedBy { it.first.second }[199].let{println(it)}
}

fun main() {
	val data: List<List<Boolean>> = getLines(2019_10).map{it.map{it!='.'}}
	part1(data)
	part2(data)
}