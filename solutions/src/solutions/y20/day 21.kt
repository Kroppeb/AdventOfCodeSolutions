@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d21

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val mmm = data.map{line ->
		val p = line.split('(')
		val items = p[0].split(" ").filter { it.isNotBlank() }.toSet()
		val alerg = if(p.size < 2) emptyList() else {
			p[1].substring(9).dropLast(1).split(", ")
		}
		alerg to items
	}
	val uuu = mmm.flatMap { it.first.map{i->i to it.second} }
	var ppp = mutableMapOf<String,Set<String>>()
	for((k,v) in uuu){
		if(k !in ppp){
			ppp[k] = v
		}else{
			ppp[k] = ppp[k]!!.intersect(v)
		}
	}
	println(ppp)
	val maybe = ppp.values.reduce { acc, set -> acc.union(set) }
	val never = mmm.flatMap{it.second}.toSet().filter{it !in maybe}.toSet()

	val count = mmm.flatMap { it.second }.count{it in never}
	count.log()
}


private fun part2(data: Data) {

	val mmm = data.map{line ->
		val p = line.split('(')
		val items = p[0].split(" ").filter { it.isNotBlank() }.toSet()
		val alerg = if(p.size < 2) emptyList() else {
			p[1].substring(9).dropLast(1).split(", ")
		}
		alerg to items
	}
	val uuu = mmm.flatMap { it.first.map{i->i to it.second} }
	var ppp = mutableMapOf<String,Set<String>>()
	for((k,v) in uuu){
		if(k !in ppp){
			ppp[k] = v
		}else{
			ppp[k] = ppp[k]!!.intersect(v)
		}
	}
	println(ppp)
	val maybe = ppp.values.reduce { acc, set -> acc.union(set) }
	val never = mmm.flatMap{it.second}.toSet().filter{it !in maybe}.toSet()


	val found = mutableMapOf<String,String>()
	val ff = mutableSetOf<String>()
	repeat(100){
		for((k,v) in ppp.entries){
			if(k in found)
				continue
			val u = v.filter { it !in ff }
			if(u.size == 1){
				found[k] = u[0]
				ff.add(u[0])
			}
			if(u.size < v.size)
				ppp[k] = u.toSet()
		}
	}

	println(ppp)
	println(found)
	println(ppp["sesame"]!!.filter{it !in found})
	println(found.entries.sortedBy { it.key }.map{it.value}.joinToString(separator = ","))
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_21)
	part1(data)
	part2(data)
}


fun <T> T.log(): T = also { println(this) }