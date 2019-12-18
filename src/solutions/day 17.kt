package solutions

import coroutines.parallelMap
import grid.entityGrid
import helpers.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.flow.asFlow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.*

val mov = listOf("A,B,A,B,A,C,B,C,A,C","L,6,R,12,L,6","R,12,L,10,L,4,L,6","L,10,L,10,L,4,L,6","n","").joinToString(separator = "\n")

private fun part1(data: IntCode) = runBlocking {
	runComputer(data).run{
		val o = output.toList().joinToString(separator = "") { it.toChar().toString() }
		println(o)
				val grid = o.split("\n").map{it.map{it}}.entityGrid { it == '#' }

		println(grid.points.filter{it.getQuadNeighbours().all{it in grid.points}}.sumBy { (a,b) -> a*b })
	}
}

private fun part2(data: IntCode) = runBlocking {
	val d = data.toMutableMap()
	d[0L] = 2L
	runComputer(d, mov.map{it.toInt()}).run{
		val o = output.toList()
		o.dropLast(1).forEach{print(it.toChar())}
		println(o.last())
	}
}

/*
.split("\n").map{it.map{it}}.entityGrid { it=='#' }
		println(grid.points.filter{it.getQuadNeighbours().all{it in grid.points}}.sumBy { (a,b) -> a*b })
 */

fun main() {
	val data: IntCode = getIntCode(17)
	part1(data)
	part2(data)
}