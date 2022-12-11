@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d22


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

data class State(val isPlayer:Boolean, val bossHp:Int, val playerHp:Int, val effects:Map<Effect, Int>, val mana:Int)

private fun part1() {
	var (bossHp, bossD) = getLines(2015_22).int()

	dijkstra(State(true, bossHp, 50, mapOf(), 500), {state -> state.bossHp <= 0}){state ->
		if (state.playerHp <= 0) return@dijkstra listOf()
		var bossHp = state.bossHp
		var mana = state.mana
		if (Effect.POISON in state.effects) {
			bossHp -= 3
		}
		if (Effect.RECHARGE in state.effects) {
			mana += 101
		}
		var nextEffects = state.effects.mapValues { (_,v) -> v - 1 }.filterValues { it > 0 }

		if (state.isPlayer) {
			spells.filter{it.mana <= mana}.filter{it.effect?.let{it !in nextEffects}?:true}.map{spell ->
				State(false,
					bossHp - spell.damage,
					state.playerHp + spell.health,
					nextEffects.toMutableMap().also{it[spell.effect?:return@also] = spell.effect.duration},
					mana - spell.mana
				) to spell.mana
			}
		} else {
			val playerHp = state.playerHp - if (Effect.SHIELD in state.effects) {
				(bossD - 7).coerceAtLeast(1)
			} else {
				bossD
			}

			if (playerHp <= 0) {
				listOf()
			} else {
				listOf(State(true, bossHp, playerHp, nextEffects, mana) to 0)
			}
		}
	}?.also{it.path.log()}?.cost.log()
}

private fun part2() {
	var (bossHp, bossD) = getLines(2015_22).int()

	dijkstra(State(true, bossHp, 50, mapOf(), 500), {state -> state.bossHp <= 0}){state ->
		if (state.playerHp <= 0) return@dijkstra listOf()
		var bossHp = state.bossHp
		var mana = state.mana
		if (Effect.POISON in state.effects) {
			bossHp -= 3
		}
		if (Effect.RECHARGE in state.effects) {
			mana += 101
		}
		var nextEffects = state.effects.mapValues { (_,v) -> v - 1 }.filterValues { it > 0 }

		if (state.isPlayer) {
			if (state.playerHp <= 1) return@dijkstra listOf()
			spells.filter{it.mana <= mana}.filter{it.effect?.let{it !in nextEffects}?:true}.map{spell ->
				State(false,
					bossHp - spell.damage,
					state.playerHp + spell.health - 1,
					nextEffects.toMutableMap().also{it[spell.effect?:return@also] = spell.effect.duration},
					mana - spell.mana
				) to spell.mana
			}
		} else {
			val playerHp = if (bossHp <= 0) {
				1000
			} else state.playerHp - if (Effect.SHIELD in state.effects) {
				(bossD - 7).coerceAtLeast(1)
			} else {
				bossD
			}

			if (playerHp <= 0) {
				listOf()
			} else {
				listOf(State(true, bossHp, playerHp, nextEffects, mana) to 0)
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