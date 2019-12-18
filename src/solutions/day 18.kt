package solutions

import com.sun.jmx.remote.internal.ArrayQueue
import coroutines.parallelMap
import grid.entityGrid
import helpers.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.*

data class Step(val pos: Robots, val distance: Int, val keys: Int)
data class Robots(val a: Point, val b: Point, val c: Point, val d: Point) {
	fun mod(id: Int, r: Point) = when (id) {
		0 -> copy(a = r)
		1 -> copy(b = r)
		2 -> copy(c = r)
		3 -> copy(d = r)
		else -> error("no")
	}

	operator fun get(id: Int) = when (id) {
		0 -> a
		1 -> b
		2 -> c
		3 -> d
		else -> error("no")
	}
}

private fun part1(data: List<List<Char>>) {
	val passable = data.entityGrid { it != '#' }
	val doors = passable.points.filter { (x, y) -> data[x][y].let { it.isLetter() && it.isUpperCase() } }.associateWith { (x, y) -> data[x][y].toLowerCase() }
	val keys = passable.points.filter { (x, y) -> data[x][y].let { it.isLetter() && it.isLowerCase() } }.associateWith { (x, y) -> data[x][y] }

	val a = keys.values.sorted().toString()
	val kk = mutableMapOf<String, Int>(a to -1, emptyList<Point>().toString() to 0)
	val kr = mutableMapOf(-1 to keys.values.toSet(), 0 to emptySet())

	val red = passable.points.toMutableSet()

	var cont = true
	while (cont) {
		cont = false
		for (i in red.toList()) {
			if (data[i.x][i.y] != '.')
				continue
			if (i.getQuadNeighbours().count { it in red } < 2) {
				cont = true
				red.remove(i)
			}
		}
	}

	println("reduced from ${passable.points.size} to ${red.size}")

	val nei = red.associateWith { it.getQuadNeighbours().filter{it in red}.associateWith { 1 }.toMutableMap() }.filterValues { it.isNotEmpty() }.toMutableMap()

	cont = true
	while (cont) {
		cont = false
		for (p in nei.keys.toList()) {
			val l = nei[p]?:continue
			if (data[p.x][p.y] != '.' || l.size>2)
				continue
			val (a,b) = l.entries.toList()
			nei[a.key]!!.remove(p)
			nei[b.key]!!.remove(p)
			val s = a.value + b.value
			nei[a.key]!![b.key] = s
			nei[b.key]!![a.key] = s
			nei.remove(p)
			cont = true
		}
	}

	println("reduced from ${red.size} to ${nei.size}")

	val queue = ArrayDeque<Step>()
	queue.add(Step(red.filter { (x, y) -> data[x][y] == '@' }.let { (a, b, c, d) -> Robots(a, b, c, d) }, 0, 0))

	val seen = mutableMapOf<Int, MutableSet<Robots>>()

	while (queue.isNotEmpty()) {
		val cur = queue.pop()
		if (cur.keys == -1) {
			println(cur.distance)
			return
		}
		for (robot in 0..3)
			for ((n,l) in nei[cur.pos[robot]]!!) {
				if (n !in red)
					continue
				val newPos = cur.pos.mod(robot, n)
				if (newPos in seen[cur.keys] ?: emptySet<Robots>())
					continue
				seen.getOrPut(cur.keys) { mutableSetOf() }.add(newPos)
				val c = kr[cur.keys] ?: emptySet()
				if (n in doors && doors[n]!! !in c)
					continue

				if (n in keys && keys[n]!! !in c) {
					val nextKeys = c.toMutableSet()
					nextKeys.add(keys[n]!!)
					val r = nextKeys.sorted().toString()
					val q = if (r in kk) {
						kk[r]!!
					} else {
						val qq = kk.size
						kr[qq] = nextKeys
						kk[r] = qq
						qq
					}
					queue.add(Step(newPos, cur.distance + l, q))
				} else {
					queue.add(Step(newPos, cur.distance + l, cur.keys))
				}
			}
	}
}

private fun part2(data: List<List<Char>>) = runBlocking {

}

fun main() {
	val data: List<List<Char>> = getLines(18).map { it.map { it } }
	part1(data)
	part2(data)
}