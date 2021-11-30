@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d14

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val mem = mutableMapOf<Long, Long>()
	var mask = "".toList().reversed()
	for( i in data){
		if(i.startsWith("mask")){
			mask = i.split(" = ")[1].toList().reversed()
		} else {
			var (m, v) = i.getLongs()
			for(x in 0..35){
				when(mask[x] ) {
					'0' -> {
						//m = m and (- (1L shl x) - 1)
						 v = v and (- (1L shl x) - 1)
					}
					'1' -> {
						//ms = ms.map{it or (1L shl x)}
						 v = v or (1L shl x)
					}
				}
			}
			mem[m] = v

			//println(mem)
		}
	}
	println(mem.values.sum())
}


private fun part2(data: Data) {
	val mem = mutableMapOf<Long, Long>()
	var mask = "".toList().reversed()
	for( i in data){
		if(i.startsWith("mask")){
			mask = i.split(" = ")[1].toList().reversed()
		} else {
			var (m, v) = i.getLongs()
			var ms = listOf(m)
			for(x in 0..35){
				when(mask[x] ) {
					'0' -> {
						//m = m and (- (1L shl x) - 1)
						// v = v and (- (1L shl x) - 1)
					}
					'1' -> {
						ms = ms.map{it or (1L shl x)}
						// v = v or (1L shl x)
					}
					'X' -> {
						ms = ms.map{it or (1L shl x)} + ms.map{it and (- (1L shl x) - 1)}
					}
				}
			}
			for(m in ms)
				mem[m] = v

			//println(mem)
		}
	}
	println(mem.values.sum())
}


private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_14)
	part1(data)
	part2(data)
}


fun Any?.log() = println(this)