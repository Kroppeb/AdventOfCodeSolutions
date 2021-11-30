/*@file:Suppress("PackageDirectoryMismatch")

package fast.y20.d09d

import helpers.*
import collections.*
import grid.*
import graph.BFS
import itertools.count
import kotlinx.coroutines.*
import kotlin.math.pow
import it.unimi.dsi.fastutil.longs.*

val xxxxx = Clock(3, 6);

private fun part1(data: Data): Long {
	val m = Long2IntOpenHashMap(60000,0.2f)
	//m.defaultReturnValue(0)
	for(i in 0..24){
		for(j in 0..24){
			m.merge( data[i] + data[j],1){a,b->a+b}
		}
	}
	var idx = 25
	while(idx < data.size){
		if(data[idx] !in m || m[data[idx]] == 0){
			return data[idx]
		}
		for(j in (idx - 24) until idx){
			m.merge(data[idx-25] + data[j], -1){a,b->a+b}
			m.merge(data[idx] + data[j], +1){a,b->a+b}
		}
		idx++
	}
	error("")
}

private fun part2(data: Data, target:Long) {
	var start = 0
	var end = 0
	var s = data[0]
	while(s != target){
		if(s < target){
			s += data[++end]
		} else {
			s -= data[start++]
		}
	}
	//val p = data.subList(start, end +1)
	//println(p.min()!! + p.max()!!)
}

private typealias Data = LongArray

fun main() {
	val start = System.currentTimeMillis()
	val data: Data = getLongs(2020_09).toLongArray()
	val io = System.currentTimeMillis()

	val target = part1(data)
	println(target)
	val part1 = System.currentTimeMillis()
	part2(data, target)
	val part2 = System.currentTimeMillis()
	println("io: ${io - start} ms")
	println("part1: ${part1 - io} ms")
	println("part2: ${part2 - part1} ms")

}*/