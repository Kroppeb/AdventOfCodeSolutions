@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d22

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.*
import grid.*
import helpers.*
import itertools.*
import solutions.solutions.y19.d20c.p
import solutions.y15.d16.target
import kotlin.math.*


private val xxxxx = Clock(6, 3);


val geos = mmopl()


private fun part1() {
	var data = getLines(2018_22)
	val depth = data.first().int()
	val target = data.last().ints().let{(a,b) -> a toP b}


	fun getGeo(p: Point): Long {
		if(p == target || p == 0 toP 0){
			return 0
		}

		if(p.x == 0) {
			return (p.y * 48271L) % 20183L
		} else if (p.y == 0) {
			return (p.x * 16807L) % 20183L
		}

		if (p in geos) {
			return geos[p]!!
		}


		fun getEro(p: Point): Long {
			return (getGeo(p) + depth)  % 20183L
		}

		return (((getEro(p.up) * getEro(p.left)) % 20183L)).also{geos[p] = it}
	}

	fun getEro(p: Point): Long {
		return (getGeo(p) + depth)  % 20183L
	}

	fun getType(point: Point): Int {
		return getEro(point).toInt() % 3
	}

	listOf(0 toP 0, target).bounds().sumBy { getType(it) }.log()
}

private fun part2() {
	var data = getLines(2018_22)
	val depth = data.first().int()
	val target = data.last().ints().let{(a,b) -> a toP b}


	fun getGeo(p: Point): Long {
		if(p == target || p == 0 toP 0){
			return 0
		}

		if(p.x == 0) {
			return (p.y * 48271L) % 20183L
		} else if (p.y == 0) {
			return (p.x * 16807L) % 20183L
		}

		if (p in geos) {
			return geos[p]!!
		}


		fun getEro(p: Point): Long {
			return (getGeo(p) + depth)  % 20183L
		}

		return (((getEro(p.up) * getEro(p.left)) % 20183L)).also{geos[p] = it}
	}

	fun getEro(p: Point): Long {
		return (getGeo(p) + depth)  % 20183L
	}

	fun getType(point: Point): Int {
		return getEro(point).toInt() % 3
	}

	dijkstra(0 toP 0 to "torch", {(p, i) -> p == target && i == "torch"}) {(p, i) ->
		listOf("torch", "gear", "").map{p to it to 7} +
				p.getQuadNeighbours().filter{it.x >= 0 && it.y >= 0}.filter{when(i) {
					"torch" -> getType(p) in listOf(0,2)
					"gear" -> getType(p) in listOf(0,1)
					"" -> getType(p) in listOf(1,2)
					else -> error(i)
				} }.map{it to i to 1}
	}!!.cost.log()

}


fun main() {
	println("Day 22: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }