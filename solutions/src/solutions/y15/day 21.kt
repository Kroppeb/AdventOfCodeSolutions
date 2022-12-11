@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d21


/*
import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.collections.*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);

data class Item(val gold: Int, val damage: Int, val Armor: Int)

val swords = listOf(
	Item(8, 4, 0),
	Item(10, 5, 0),
	Item(25, 6, 0),
	Item(40, 7, 0),
	Item(74, 8, 0),
)

val armor = listOf(
	Item(0, 0, 0),
	Item(13, 0, 1),
	Item(31, 0, 2),
	Item(53, 0, 3),
	Item(75, 0, 4),
	Item(102, 0, 5),
)

val rings = listOf(
	Item(0, 0, 0),
	Item(0, 0, 0),
	Item(25, 1, 0),
	Item(50, 2, 0),
	Item(100, 3, 0),
	Item(20, 0, 1),
	Item(40, 0, 2),
	Item(80, 0, 3),
)

private fun part1() {
	val (bossHp, bossD, bossA) = getLines(2015_21).int()

	fun eval(items: List<Item>):Boolean {
		val d = items.sumBy { it.damage }
		val a = items.sumBy{it.Armor}

		var bhp = bossHp
		var php = 100

		while(true) {
			bhp -= (d - bossA).coerceAtLeast(1)
			if (bhp <= 0) {
				return true;
			}

			php -= (bossD - a).coerceAtLeast(1)
			if (php <= 0) {
				return false
			}
		}
	}

	swords.cartesianProduct(armor).cartesianProduct(rings.pairWise()).map{(a,b) ->
		a.toList() + b.toList()
	}.filter{eval(it)}.map{it.sumBy { it.gold }}.min().log()
}

private fun part2() {
	val (bossHp, bossD, bossA) = getLines(2015_21).int()

	fun eval(items: List<Item>):Boolean {
		val d = items.sumBy { it.damage }
		val a = items.sumBy{it.Armor}

		var bhp = bossHp
		var php = 100

		while(true) {
			bhp -= (d - bossA).coerceAtLeast(1)
			if (bhp <= 0) {
				return true;
			}

			php -= (bossD - a).coerceAtLeast(1)
			if (php <= 0) {
				return false
			}
		}
	}

	swords.cartesianProduct(armor).cartesianProduct(rings.pairWise()).map{(a,b) ->
		a.toList() + b.toList()
	}.filter{!eval(it)}.map{it.sumBy { it.gold }}.max().log()
}

fun main() {
	println("Day 21: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }