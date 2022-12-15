@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d20

/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */


import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.Point3DL
import kotlin.math.*


private val xxxxx = Clock(6, 3);


data class Particle(var p: Point3DL, var v: Point3DL, val a: Point3DL, val id: Int) {
	fun update() {
		v += a
		p += v
	}

	fun isBallistic(): Boolean {
		return this.p.x.sign * this.v.x.sign >= 0 &&
				this.p.y.sign * this.v.y.sign >= 0 &&
				this.p.z.sign * this.v.z.sign >= 0 &&
				this.p.x.sign * this.a.x.sign >= 0 &&
				this.p.y.sign * this.a.y.sign >= 0 &&
				this.p.z.sign * this.a.z.sign >= 0
	}

	fun diff(other:Particle) : Boolean {
		return Particle(p - other.p, v - other.v, a - other.a, -1).isBallistic()
	}
}

private fun part1() {
	var data = getLines(2017_20).points3DI().mapIndexed() { id, (p, v, a) ->
		Particle(
			Point3DL(p.x.toLong(), p.y.toLong(), p.z.toLong()),
			Point3DL(v.x.toLong(), v.y.toLong(), v.z.toLong()),
			Point3DL(a.x.toLong(), a.y.toLong(), a.z.toLong()),
			id
		)
	}.toMutableSet()

	var x = -1
	while (data.size > 1) {
		x++
		if (x % 1000 == 0) {
			data.size.log()
		}
		for (p in data) {
			repeat(100) {
				p.update()
			}
		}

		val bal = data.filter { it.isBallistic() }
		if (x % 1000 == 0) {
			bal.size.log()
			data.first().log()
		}
		if (bal.isNotEmpty()) {
			val closest = bal.minBy { it.p.manDist() }
			if (x % 1000 == 0) {

				closest.log()
			}

			data.removeIf {
				it != closest &&
						it.p.manDist() >= closest.p.manDist() &&
						it.v.manDist() >= closest.v.manDist() &&
						it.a.manDist() >= closest.a.manDist()
			}
		}
	}
	data.toList().first().log()
}

private fun part2() {
	var data = getLines(2017_20).points3DI().mapIndexed() { id, (p, v, a) ->
		Particle(
			Point3DL(p.x.toLong(), p.y.toLong(), p.z.toLong()),
			Point3DL(v.x.toLong(), v.y.toLong(), v.z.toLong()),
			Point3DL(a.x.toLong(), a.y.toLong(), a.z.toLong()),
			id
		)
	}

	var x = -1
	while (data.size > 1) {
		x++
		for (p in data) {
			p.update()
		}

		data = data.groupBy { it.p }.entries.filter{it.value.size == 1}.map{it.value.first()}

		if (x % 100 == 0) {
			if (data.pairWise().all{(a,b) -> a.diff(b)}){
				break
			}
			data.size.log()
		}


	}
	data.size.log()

}


fun main() {
	println("Day 20: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }