@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d19

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*
import java.util.ArrayDeque

val xxxxx = Clock(6, 3);

fun solve1(l:String){

}

private fun part1(data: Data) {
	var a = 0
	var b = 0

	val rules = data.takeWhile { it.isNotBlank() }.mapNotNull{line ->
		val (id, rem) = line.split(":")
		val i = id.toInt()
		val subs = rem.split("|").map{it.trim().split(" ")}.mapNotNull{
			if (it.size == 1){
				if("a" in it[0]){
					a = i
					return@mapNotNull null
				} else if("b" in it[0]){
					b = i
					return@mapNotNull null
				}
			}
			it.map{it.toInt()}
		}
		if(subs.isNotEmpty())
			i to subs
		else
			null
	}.toMap()

	val d = data.drop(rules.size + 3)


	println(rules)
	d.count{
		println(it)
		val x = it.map{if(it == 'a') a else b}
		println(x)

		// returns to where the next letter is or empty
		fun check(start:Int, d:Int): List<Int> {
			println("checking $d at $start")

			if(start >= x.size)
				return emptyList()
			if(x[start] == d)
				return listOf(start + 1)
			val l = rules[d]?: return emptyList()

			val s = listOf(start)
			val res = mutableListOf<Int>()
			println("s is $s")
			for(i in l){
				var ss = s
				for(step in i){
					ss = ss.flatMap{check(it, step)}
					if(ss.isEmpty())
						break
					println("ss is $ss")
				}
				res += ss
			}
			return res
		}

		val uu = check(0,0).count{it == x.size} > 0
		//error(" ")
		if(uu)
			println(it)
		uu
	}.log()
}


private fun part2(data: Data) {
	var a = 0
	var b = 0

	val rules = data.takeWhile { it.isNotBlank() }.map{it.split("//")[0]}.mapNotNull{line ->
		val (id, rem) = line.split(":")
		val i = id.toInt()
		val subs = rem.split("|").map{it.trim().split(" ")}.mapNotNull{
			if (it.size == 1){
				if("a" in it[0]){
					a = i
					return@mapNotNull null
				} else if("b" in it[0]){
					b = i
					return@mapNotNull null
				}
			}
			it.map{it.toInt()}
		}
		if(subs.isNotEmpty())
			i to subs
		else
			null
	}.toMap()

	val d = data.drop(rules.size + 3)


	println(rules)
	d.count{
		println(it)
		val x = it.map{if(it == 'a') a else b}
		println(x)

		// returns to where the next letter is or empty
		fun check(start:Int, d:Int): List<Int> {
			println("checking $d at $start")

			if(start >= x.size)
				return emptyList()
			if(x[start] == d)
				return listOf(start + 1)
			val l = rules[d]?: return emptyList()

			val s = listOf(start)
			val res = mutableListOf<Int>()
			println("s is $s")
			for(i in l){
				var ss = s
				for(step in i){
					ss = ss.flatMap{check(it, step)}
					if(ss.isEmpty())
						break
					println("ss is $ss")
				}
				res += ss
			}
			return res
		}

		fun find(): Boolean {
			val m = mutableListOf<List<Int>>(listOf(0))

			while(true){
				println("gonna")
				val r = m.last().flatMap{check(it, 42)}
				if(r.isEmpty())
					break
				else
					m += r
			}

			println("found starts: $m")

			for(i in m.indices){
				var mm = m[i].filter{it != x.size}
				println("trying at $mm")
				//if(mm.contains(x.size))

				//	return true
				for(aaaaa in 0 until i){
					mm = mm.flatMap{check(it, 31)}
					if(mm.contains(x.size))
						return true
				}
			}
			return false
		}

		val uu = find()
		//error(" ")
		if(uu)
			println(it)
		uu
	}.log()
}


private fun part22(data: Data) {
	var a = 0
	var b = 0

	val rules = data.takeWhile { it.isNotBlank() }.map{it.split("//")[0]}.mapNotNull{line ->
		val (id, rem) = line.split(":")
		val i = id.toInt()
		val subs = rem.split("|").map{it.trim().split(" ")}.mapNotNull{
			if (it.size == 1){
				if("a" in it[0]){
					a = i
					return@mapNotNull null
				} else if("b" in it[0]){
					b = i
					return@mapNotNull null
				}
			}
			it.map{it.toInt()}
		}
		if(subs.isNotEmpty())
			i to subs
		else
			null
	}.toMap()

	val d = data.drop(rules.size + 3)

	fun check(start:Int, d:Int, x:List<Int>): List<Int> {
		println("checking $d at $start")

		if(start >= x.size)
			return emptyList()
		if(x[start] == d)
			return listOf(start + 1)
		val l = rules[d]?: return emptyList()

		val s = listOf(start)
		val res = mutableListOf<Int>()
		println("s is $s")
		for(i in l){
			var ss = s
			for(step in i){
				ss = ss.flatMap{check(it, step, x)}
				if(ss.isEmpty())
					break
				println("ss is $ss")
			}
			res += ss
		}
		return res
	}

	val cache = mutableMapOf<Int,Set<List<Int>>>(a to setOf(listOf(a)), b to setOf(listOf(b)))
	fun gen(d:Int): Set<List<Int>> {
		if(d in cache)
			return cache[d]!!

		val l = rules[d]!!

		return l.flatMap { i -> i.map{gen(it)}.reduceRight{a, b -> a.cartesianProduct(b){x,y -> x + y}.toSet() } }.toSet()
	}

	fun find(id:Int) = listOf(a,b).cartesianPower(8).filter{
		check(0, id, it).contains(8)
	}

	val ft = gen(42)
	val to = gen(31)

	println(rules)
	d.count{
		println(it)
		val x = it.map{if(it == 'a') a else b}
		println(x)

		// returns to where the next letter is or empty




		val chunks = x.chunked(8)
		val ftC = chunks.takeWhile { it in ft }.size
		val toC = chunks.reversed().takeWhile { it in to }.size
		println("$ftC, $toC")
		ftC > 1 && toC > 0 && (ftC + min(ftC - 1, toC)) >= chunks.size
	}.log()
}




private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_19)
	//part1(data)
	part22(data)
}


fun <T>T.log():T = also{println(this)}