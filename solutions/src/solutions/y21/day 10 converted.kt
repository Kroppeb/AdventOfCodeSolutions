@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d10c

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import collections.list.BracketStack
import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val points2 = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)


private fun part1() {
	var data = getLines(2021_10).e()


	var coo = 0
	for (datum in data) {

		val stack = BracketStack<Char> { a, b ->
			val x = when (b) {
				')' -> a == '('
				']' -> a == '['
				'}' -> a == '{'
				'>' -> a == '<'
				else -> return@BracketStack false
			}
			if (x) {
				return@BracketStack true
			}else{
				error("no")
			}
		}

		for (c in datum) {
			try{
				stack.add(c)
			} catch (e: Exception) {
				coo+=points[c]!!
				break
			}
		}
	}

	coo.log()
}

private fun part2() {
	var data = getLines(2021_10).e()


	var coo = mlol()
	l@ for (datum in data) {

		val stack = BracketStack<Char> { a, b ->
			val x = when (b) {
				')' -> a == '('
				']' -> a == '['
				'}' -> a == '{'
				'>' -> a == '<'
				else -> return@BracketStack false
			}
			if (x) {
				return@BracketStack true
			}else{
				error("no")
			}
		}

		for (c in datum) {
			try{
				stack.add(c)
			} catch (e: Exception) {
				continue@l
			}
		}

		var x = 0L
		for (i in stack.reversed()){
			x *= 5
			x += points2[i]!!
			x.log()
		}

		coo += x
	}

	coo.log().medianOdd().log()
}


fun main() {
	println("Day 10: ")
	part1()
	part2()
}


private fun <T> T.log(): T = also { println(this) }