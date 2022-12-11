@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d22c


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
import me.kroppeb.aoc.helpers.collections.list.toH
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


data class Spell(val mana:Int, val damage:Int, val health:Int, val effect: Effect?)
enum class Effect(val duration:Int) {
	SHIELD(6), POISON(6), RECHARGE(5)
}

val spells = listOf(
	Spell(53, 4, 0, null),
	Spell(73, 2, 2, null),
	Spell(113, 0, 0, Effect.SHIELD),
	Spell(173, 0, 0, Effect.POISON),
	Spell(229, 0, 0, Effect.RECHARGE),
)

// data class State(val isPlayer:Boolean, val bossHp:Int, val playerHp:Int, val effects:Map<Effect, Int>, val mana:Int)

private fun part1() {
	var (bossHp, bossD) = getLines(2015_22).int()

	dijkstra(true toH bossHp toH 50 toH mapOf<Effect, Int>() toH 500, {(_, bossHp) -> bossHp <= 0}){ state ->
		var (isPlayer, bossHp, playerHp, effects, mana) = state

		if (playerHp <= 0) return@dijkstra listOf()
		if (Effect.POISON in effects) {
			bossHp -= 3
		}
		if (Effect.RECHARGE in effects) {
			mana += 101
		}
		var nextEffects = effects.mapValues { (_,v) -> v - 1 }.filterValues { it > 0 }

		if (isPlayer) {
			spells.filter{it.mana <= mana}.filter{it.effect?.let{it !in nextEffects}?:true}.map{spell ->
				false toH
					bossHp - spell.damage toH
					playerHp + spell.health toH
					nextEffects.toMutableMap().also{it[spell.effect?:return@also] = spell.effect.duration} toH
					mana - spell.mana to
					spell.mana
			}
		} else {
			playerHp -= if (Effect.SHIELD in effects) {
				(bossD - 7).coerceAtLeast(1)
			} else {
				bossD
			}

			if (playerHp <= 0) {
				listOf()
			} else {
				listOf(true toH bossHp toH playerHp toH nextEffects toH mana to 0)
			}
		}
	}?.also{it.path.log()}?.cost.log()
}

private fun part2() {
	var (bossHp, bossD) = getLines(2015_22).int()

	dijkstra(true toH bossHp toH 50 toH mapOf<Effect, Int>() toH 500, {(_, bossHp) -> bossHp <= 0}){state ->
		var (isPlayer, bossHp, playerHp, effects, mana) = state

		if (playerHp <= 0) return@dijkstra listOf()
		if (Effect.POISON in effects) {
			bossHp -= 3
		}
		if (Effect.RECHARGE in effects) {
			mana += 101
		}
		var nextEffects = effects.mapValues { (_,v) -> v - 1 }.filterValues { it > 0 }

		if (isPlayer) {
			if (playerHp <= 1) return@dijkstra listOf()
			spells.filter{it.mana <= mana}.filter{it.effect?.let{it !in nextEffects}?:true}.map{spell ->
				false toH
					bossHp - spell.damage toH
					playerHp + spell.health - 1 toH
					nextEffects.toMutableMap().also{it[spell.effect?:return@also] = spell.effect.duration} toH
					mana - spell.mana to
					spell.mana
			}
		} else {
			playerHp = if (bossHp <= 0) {
				1000
			} else playerHp - if (Effect.SHIELD in effects) {
				(bossD - 7).coerceAtLeast(1)
			} else {
				bossD
			}

			if (playerHp <= 0) {
				listOf()
			} else {
				listOf(true toH bossHp toH playerHp toH nextEffects toH mana to 0)
			}
		}
	}?.cost.log()
}

fun main() {
	println("Day 22: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }