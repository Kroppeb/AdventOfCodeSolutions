@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d24

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
import solutions.solutions.y19.d22.m
import solutions.y18.d24.Unit
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);

class Unit(
	val good: Boolean,
	var count: Int,
	val hp: Int,
	val type: String,
	val strength: Int,
	val initiative: Int,
	val immune: Set<String>,
	val weak: Set<String>
) {
	override fun toString(): String {
		return "Unit(good=$good, count=$count, hp=$hp, type='$type', strength=$strength, initiative=$initiative, immune=$immune, weak=$weak)"
	}

	fun getEffectivePower(): Int {
		return this.count * this.strength
	}

	fun getEffectivePower(other: Unit): Int {

		return when (type) {
			in other.immune -> 0
			in other.weak -> 2 * getEffectivePower()
			else -> getEffectivePower()
		}
	}
}

fun parseUnit(line: String, good: Boolean, boost:Int = 0): Unit {
	val (count, hp, strength, initiative) = line.ints()

	val type = line.split(" ").reversed()[4]


	var immune = setOf<String>()
	var weak = setOf<String>()

	if ('(' in line) {
		val m = line.dropWhile { it != '(' }.drop(1).takeWhile { it != ')' }.split("; ")
		for (x in m) {
			val s = x.split(" ").drop(2).map { it.takeWhile { it != ',' } }.toSet()
			if (x.startsWith("immune")) {
				immune = s
			} else if (x.startsWith("weak")) {
				weak = s
			} else {
				error(line)
			}
		}
	}

	return Unit(good, count, hp, type, strength + boost, initiative, immune, weak)
}


private fun part1() {
	var data = getLines(2018_24).splitOnEmpty()

	val goods = data.first().drop(1).map { parseUnit(it, true) }.toMutableSet()
	val bads = data.last().drop(1).map { parseUnit(it, false) }.toMutableSet()

	while (goods.isNotEmpty() && bads.isNotEmpty()) {
		val choose = (goods + bads).sortedWith(Comparator.comparingInt<Unit> {-it.getEffectivePower()}.thenComparingInt { -it.initiative })

		val selected = msoa()
		val sel = mutableMapOf<Unit, Unit>()

		for (t in choose) {
			val enemies = (if(t.good) bads else goods).filter{it !in selected}
			if (enemies.isEmpty()) continue
			val (target, dmg) = enemies.map{it to t.getEffectivePower(it)}.sortedWith(Comparator.comparingInt<Pair<Unit, Int>?> {-it.second}.thenComparingInt { -it.first.getEffectivePower() }.thenComparingInt { -it.first.initiative }).first()
			if (dmg > 0){
				selected.add(target)
				sel[t] = target
			}
		}

		val attackers = (goods + bads).sortedWith(Comparator.comparingInt<Unit> {-it.initiative })
		for (a in attackers){
			if (a.count <= 0) continue
			val target = sel[a] ?: continue
			val dmg = a.getEffectivePower(target)
			val death = dmg / target.hp
			if (death >= target.count) {
				target.count = 0
				(if (target.good)goods else bads).remove(target)
			} else {
				target.count -= death
			}
		}
	}

	(goods + bads).sumBy { it.count }.log()
}

private fun part2() {
	var data = getLines(2018_24).splitOnEmpty()

	var boost = 1
	while(true) {

		val goods = data.first().drop(1).map { parseUnit(it, true, boost) }.toMutableSet()
		val bads = data.last().drop(1).map { parseUnit(it, false) }.toMutableSet()

		while (goods.isNotEmpty() && bads.isNotEmpty()) {
			val choose = (goods + bads).sortedWith(
				Comparator
					.comparingInt<Unit> { -it.getEffectivePower() }
					.thenComparingInt { -it.initiative })

			val selected = msoa()
			val sel = mutableMapOf<Unit, Unit>()

			var skip = true
			for (t in choose) {

				val enemies = (if (t.good) bads else goods).filter { it !in selected }
				if (enemies.isEmpty()) continue
				val (target, dmg) = enemies
					.map { it to t.getEffectivePower(it) }
					.sortedWith(
						Comparator
							.comparingInt<Pair<Unit, Int>?> { -it.second }
							.thenComparingInt { -it.first.getEffectivePower() }
							.thenComparingInt { -it.first.initiative })
					.first()
				if (dmg > 0) {
					selected.add(target)
					sel[t] = target
					skip = false
				}
			}

			if (skip) break

			skip = true

			val attackers = (goods + bads).sortedWith(Comparator.comparingInt<Unit> { -it.initiative })
			for (a in attackers) {
				if (a.count <= 0) continue
				val target = sel[a] ?: continue
				val dmg = a.getEffectivePower(target)
				val death = dmg / target.hp
				if (death > 0) skip = false
				if (death >= target.count) {
					target.count = 0
					(if (target.good) goods else bads).remove(target)
				} else {
					target.count -= death
				}
			}


			if (skip) break
		}

		if (bads.isNotEmpty()){
			boost++.log()
			continue
		}
		goods.sumBy { it.count }.log()
		break
	}
}


fun main() {
	println("Day 24: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }