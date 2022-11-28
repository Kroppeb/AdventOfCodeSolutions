@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y17.d09

/*
import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*
 */

import collections.*
import graph.*
import grid.*
import helpers.*
import itertools.*
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.*


private val xxxxx = Clock(6, 3);


sealed class Data
class Group(val items:  List<Data>) : Data()
class Garbage(val garbage: List<Char>): Data()

private fun part1() {
	var data = getLines(2017_09).first().e()

	var i = 0
	fun parse(): Data =
		when (data[i++]) {
			'{' -> {
//				println("start")

				if(data[i] == '}') {
					i++
					Group(listOf())
				} else {

					val items = mutableListOf<Data>()
					do {
						items += parse()
					} while (data[i++] == ',')
					if (data[i - 1] != '}') {
						println(data.drop((i - 5).coerceAtLeast(0)).take(10))
						println(i)
						println(data[i])
						error("i")
					}
//					println("end")
					Group(items)
				}
			}
			'<' -> {
				val items = mutableListOf<Char>()
				while (data[i] != '>') {
					if (data[i] == '!') {
						items += data[i + 1]
						i += 2
					} else {
						items += data[i]
						i++
					}
				}
				i++

				Garbage(items)
			}
			else -> {
				println(data.drop((i - 5).coerceAtLeast(0) ).take(10))
				println(i)
				println(data[i])
				error("j")
			}
		}

	val res = parse()

	fun score(d:Data, i : Int) : Int  = when(d) {
		is Group -> d.items.sumBy { score(it, i + 1) } + i
		is Garbage -> 0
	}

	score(res, 1).log()
}

private fun part2() {
	var data = getLines(2017_09).first().e()


	var i = 0
	fun parse(): Data =
		when (data[i++]) {
			'{' -> {
//				println("start")

				if(data[i] == '}') {
					i++
					Group(listOf())
				} else {

					val items = mutableListOf<Data>()
					do {
						items += parse()
					} while (data[i++] == ',')
					if (data[i - 1] != '}') {
						println(data.drop((i - 5).coerceAtLeast(0)).take(10))
						println(i)
						println(data[i])
						error("i")
					}
//					println("end")
					Group(items)
				}
			}
			'<' -> {
				val items = mutableListOf<Char>()
				while (data[i] != '>') {
					if (data[i] == '!') {
//						items += data[i + 1]
						i += 2
					} else {
						items += data[i]
						i++
					}
				}
				i++

				Garbage(items)
			}
			else -> {
				println(data.drop((i - 5).coerceAtLeast(0) ).take(10))
				println(i)
				println(data[i])
				error("j")
			}
		}

	val res = parse()

	fun score(d:Data, i : Int) : Int  = when(d) {
		is Group -> d.items.sumBy { score(it, i + 1) }
		is Garbage -> d.garbage.size
	}

	score(res, 1).log()
}


fun main() {
	println("Day 9: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }