@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y16.d22


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.bounds
import me.kroppeb.aoc.helpers.point.toPI


private val xxxxx = Clock(6, 3);

private fun part1() {
	var data = getLines(2016_22).drop(2).ints()

	data.orderedPairWise().count{(a,b) ->
		(a[3] > 0 && a[3] <= b[4])
	}.log()


}

private fun part2() {
	var data = getLines(2016_22).drop(2).ints().map{(x,y, _, u,a) -> x toPI y to (u to a)}.toMap()

	var bounds = data.keys.bounds()




	var goalX = data.keys.maxOf{(a) -> a}
	val empty = data.mapValues { it.value.second }.maxByValue()

//	bfs(data to (goalX toP 0), {(_, p) -> p.x == 0 && p.y == 0}) {(data, p) -> buildList {
//		for (sp in bounds.filter{data[it]!!.first > 0}) {
//			for (tp in sp.getQuadNeighbours().filter{it in bounds}) {
//				val (tpu, tpa) = data[tp]!!
//				val (spu, spa) = data[sp]!!
//				if (tpa >= spu){
//					add(data.toMutableMap().also{
//						it[tp] = tpu + spu to tpa - spu
//						it[sp] = 0 to spu + spa
//					} to if (sp == p) tp else p)
//				}
//			}
//		}
//	}}.log()

	bfs((empty) to (goalX toPI 0), { (_,s) -> s == 0 toPI 0}) { (empty, p) ->
		empty.getQuadNeighbours().filter{it in bounds}.filter{data[it]!!.first < 100}.map{x ->
			x to if (x == p) empty else p
		}
	}.log()

}


fun main() {
	println("Day 22: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }