@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d16

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val rules = data.takeWhile { it.isNotBlank() }.map{
		it.split(": ")[1].split(" or ").map{it.getPosInts()}.map{(a,b)->a..b}
	}
	val rem = data.drop(rules.size + 5).map{it.getInts()}
	val mine = data[rules.size + 2]
	val r = rules.flatten()
	val err = rem.flatten().filter{
		!r.any{i -> it in i}
	}
	err.sum().log()
}


private fun part2(data: Data) {
	val rules = data.takeWhile { it.isNotBlank() }.map{
		val (x,y) = it.split(": ")
		x to y.split(" or ").map{it.getPosInts()}.map{(a,b)->a..b}
	}.toMap().toMutableMap()
	val rem = data.drop(rules.size + 5).map{it.getInts()}
	val mine = data[rules.size + 2].getInts()
	val r = rules.values.flatten()
	val good = rem.filter{
		it.all{r.any{i -> it in i}}
	}
	println(good)

	val pos = MutableList(rules.size){rules.keys.toMutableSet()}

	for (card in good) {
		for (idx in pos.indices) {
			val bad = mutableSetOf<String>()
			val cur = pos[idx]
			for (re in cur) {
				if (rules[re]!!.all { card[idx] !in it }) {
					bad.add(re)
				}
			}
			cur.removeAll(bad)
		}
	}

	val found = mutableMapOf<String,Int>()

	repeat(500) {
		for (idx in pos.indices) {
			val bad = mutableSetOf<String>()
			val cur = pos[idx]
			for (re in cur) {
				if (rules[re]?.any{ good.any{card -> card[idx] !in it}  } != true) {
					bad.add(re)
				}
			}
			cur.removeAll(bad)
		}

		for(i in pos.indices){
			if(pos[i].size == 1){
				val n = pos[i].toList()[0]
				found[n] = i
				rules.remove(n)
			}
		}

		println(pos.sumBy { it.count() })
	}

	var ans = 1L
	for(k in found.keys){
		if(k.startsWith("departure")){
			ans *= mine[found[k]!!]
		}
	}
	println(found)
	ans.log()

}


private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_16)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)