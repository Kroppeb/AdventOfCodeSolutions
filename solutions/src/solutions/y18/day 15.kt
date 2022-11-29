@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y18.d15

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import graph.bfs
import grid.*
import helpers.*
import itertools.*
import solutions.solutions.y19.d20c.p
import java.util.Comparator.comparing
import java.util.stream.Collectors.toSet
import kotlin.math.*


private val xxxxx = Clock(6, 3);

/*

*/

class Unit(var pos: Point, var health: Int, val isElf: Boolean) {
	override fun toString(): String {
		return if (isElf) "E($health)" else "G($health)"
	}
}

private fun part1() {
	var data: SimpleGrid<Char> = getLines(2018_15).e().grid()
	val walls = data.map { it == '#' }

	val elfs = mutableSetOf<Unit>()
	val goblins = mutableSetOf<Unit>()

	data.mapIndexed { point, c ->
		when (c) {
			'E' -> elfs.add(Unit(point, 200, true))
			'G' -> goblins.add(Unit(point, 200, false))
			else -> {}
		}
	}

	val readOrder = Comparator<Point> { a, b ->
		when {
			a.isAbove(b) -> -1
			a.isBelow(b) -> 1
			a.isLeftOf(b) -> -1
			a.isRightOf(b) -> 1
			else -> 0
		}
	};

	fun isFree(pos: Point) =
		pos in walls.bounds && !walls[pos] && elfs.none { it.pos == pos } && goblins.none { it.pos == pos }

	loop { count ->
		val units = elfs + goblins
		val sortedUnits = units.sortedWith(comparing({ it.pos }, readOrder))


//		if (count == 1) {
//			walls.mapIndexed { point, b ->
//				if (b) '#'
//				else if (elfs.any { it.pos == point }) 'E'
//				else if (goblins.any { it.pos == point }) 'G'
//				else '.'
//			}.forEach { it.log() }
//
//			sortedUnits.log()
//			return
//		}

		for (unit in sortedUnits) {
			if (unit.health <= 0) continue

			// move
			val enemies = if (unit.isElf) goblins else elfs
			if (enemies.isEmpty()) {
				val score = units.sumBy { max(it.health, 0) } * count


				walls.mapIndexed { point, b ->
					if (b) '#'
					else if (elfs.any { it.pos == point }) 'E'
					else if (goblins.any { it.pos == point }) 'G'
					else '.'
				}.forEach { it.log() }

				sortedUnits.log()
				score.log()
				return
			}

			assert(enemies.all { it.health > 0 })

			val enemyPositions = enemies.map { it.pos }.flatMap { it.getQuadNeighbours() }

			if (unit.pos !in enemyPositions) {
				val paths = enemyPositions
					.filter { isFree(it) }
					.toSet()
					.map { it to bfs(unit.pos, { p -> p == it }) { p -> p.getQuadNeighbours().filter { isFree(it) } } }
					.filter { it.second.first != null }

				if (paths.isEmpty()) {
					continue
				}

				val shortestDistance = paths.map { it.second.second }.min()
				val closest = paths
					.filter { it.second.second == shortestDistance }
					.sortedWith(comparing({ it.second.first!! }, readOrder))
					.first()

				val starts = unit.pos
					.getQuadNeighbours()
					.filter { isFree(it) }
					.map {
						it to bfs(it, { p -> p == closest.second.first }) { p ->
							p
								.getQuadNeighbours()
								.filter { isFree(it) }
						}
					}
					.filter { it.second.first != null }

				if (starts.isEmpty())
					continue

				val clos = starts.map { it.second.second }.min()
				val move =
					starts.filter { it.second.second == clos }.sortedWith(comparing({ it.first }, readOrder)).first()

				// "$unit: ${unit.pos} -> ${move.first}".log()

				unit.pos = move.first
			}

			// attack
			val attackable = enemies.filter { it.pos.manDistTo(unit.pos) == 1 }
			if (attackable.isNotEmpty()) {
				val health = attackable.map { it.health }.min()
				val weakest =
					attackable.filter { it.health == health }.sortedWith(comparing({ it.pos }, readOrder)).first()
				weakest.health -= 3
				if (weakest.health <= 0) {
					if (weakest.isElf) {
						elfs.remove(weakest)
					} else {
						goblins.remove(weakest)
					}
				}
			}
		}
	}
}

private fun part2() {
	var data: SimpleGrid<Char> = getLines(2018_15).e().grid()
	val walls = data.map { it == '#' }


	b@ for (power in 4..200) {
		"trying power $power".log()

		val elfs = mutableSetOf<Unit>()
		val goblins = mutableSetOf<Unit>()

		data.mapIndexed { point, c ->
			when (c) {
				'E' -> elfs.add(Unit(point, 200, true))
				'G' -> goblins.add(Unit(point, 200, false))
				else -> {}
			}
		}

		val readOrder = Comparator<Point> { a, b ->
			when {
				a.isAbove(b) -> -1
				a.isBelow(b) -> 1
				a.isLeftOf(b) -> -1
				a.isRightOf(b) -> 1
				else -> 0
			}
		};

		fun isFree(pos: Point) =
			pos in walls.bounds && !walls[pos] && elfs.none { it.pos == pos } && goblins.none { it.pos == pos }

		var count = -1
		while (true) {
			count++
			val units = elfs + goblins
			val sortedUnits = units.sortedWith(comparing({ it.pos }, readOrder))


//		if (count == 1) {
//			walls.mapIndexed { point, b ->
//				if (b) '#'
//				else if (elfs.any { it.pos == point }) 'E'
//				else if (goblins.any { it.pos == point }) 'G'
//				else '.'
//			}.forEach { it.log() }
//
//			sortedUnits.log()
//			return
//		}

			for (unit in sortedUnits) {
				if (unit.health <= 0) continue

				// move
				val enemies = if (unit.isElf) goblins else elfs
				if (enemies.isEmpty()) {
					val score = elfs.sumBy { it.health } * count


					walls.mapIndexed { point, b ->
						if (b) '#'
						else if (elfs.any { it.pos == point }) 'E'
						else if (goblins.any { it.pos == point }) 'G'
						else '.'
					}.forEach { it.log() }

					sortedUnits.log()
					score.log()
					return
				}

				assert(enemies.all { it.health > 0 })

				val enemyPositions = enemies.map { it.pos }.flatMap { it.getQuadNeighbours() }

				if (unit.pos !in enemyPositions) {
					val paths = enemyPositions
						.filter { isFree(it) }
						.toSet()
						.map {
							it to bfs(unit.pos, { p -> p == it }) { p ->
								p
									.getQuadNeighbours()
									.filter { isFree(it) }
							}
						}
						.filter { it.second.first != null }

					if (paths.isEmpty()) {
						continue
					}

					val shortestDistance = paths.map { it.second.second }.min()
					val closest = paths
						.filter { it.second.second == shortestDistance }
						.sortedWith(comparing({ it.second.first!! }, readOrder))
						.first()

					val starts = unit.pos
						.getQuadNeighbours()
						.filter { isFree(it) }
						.map {
							it to bfs(it, { p -> p == closest.second.first }) { p ->
								p
									.getQuadNeighbours()
									.filter { isFree(it) }
							}
						}
						.filter { it.second.first != null }

					if (starts.isEmpty())
						continue

					val clos = starts.map { it.second.second }.min()
					val move =
						starts
							.filter { it.second.second == clos }
							.sortedWith(comparing({ it.first }, readOrder))
							.first()

					// "$unit: ${unit.pos} -> ${move.first}".log()

					unit.pos = move.first
				}

				// attack
				val attackable = enemies.filter { it.pos.manDistTo(unit.pos) == 1 }
				if (attackable.isNotEmpty()) {
					val health = attackable.map { it.health }.min()
					val weakest =
						attackable.filter { it.health == health }.sortedWith(comparing({ it.pos }, readOrder)).first()
					weakest.health -= if (unit.isElf) power else 3
					if (weakest.health <= 0) {
						if (weakest.isElf) {
							continue@b
							// elfs.remove(weakest)
						} else {
							goblins.remove(weakest)
						}
					}
				}
			}
		}
	}

}


fun main() {
	println("Day 15: ")
	// part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }