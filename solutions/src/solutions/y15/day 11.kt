@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d11

/*
import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.graph.*

import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*


private val xxxxx = Clock(6, 3);


private fun part1() {
	var data = getLines(2015_11).first().map{it - 'a'}.mut()

	fun valid(data: List<Int>): Boolean {
		if(data.any{it in listOf('l' - 'a', 'o' - 'a', 'i' - 'a')}){
			return false
		}

		if(data.windowed(3).none{(a,b,c) -> c-b == 1 && b-a == 1}){
			return false
		}

		var i = 0
		var c = 0
		while(i < data.size - 1){
			if(data[i] == data[i + 1]){
				c++
				i+=2
			} else {
				i+=1
			}
		}

		return c >= 2
	}

	while (true) {
		for (i in data.indices.reversed()) {
			if (data[i] == 25) {
				data[i] = 0
			} else {
				data[i]++
				break
			}
		}

		if (valid(data)) {
			data.map{it + 'a'.toInt()}.map{it.toChar()}.joinToString(separator = "").log()
			break
		}
	}

}

private fun part2() {
	var data = getLines(2015_11).first().map{it - 'a'}.mut()

	fun valid(data: List<Int>): Boolean {
		if(data.any{it in listOf('l' - 'a', 'o' - 'a', 'i' - 'a')}){
			return false
		}

		if(data.windowed(3).none{(a,b,c) -> c-b == 1 && b-a == 1}){
			return false
		}

		var i = 0
		var c = 0
		while(i < data.size - 1){
			if(data[i] == data[i + 1]){
				c++
				i+=2
			} else {
				i+=1
			}
		}

		return c >= 2
	}

	var f = false

	while (true) {
		for (i in data.indices.reversed()) {
			if (data[i] == 25) {
				data[i] = 0
			} else {
				data[i]++
				break
			}
		}

		if (valid(data)) {
			data.map{it + 'a'.toInt()}.map{it.toChar()}.joinToString(separator = "").log()
			if(f) break
			f = true
		}
	}
}


fun main() {
	println("Day 11: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }