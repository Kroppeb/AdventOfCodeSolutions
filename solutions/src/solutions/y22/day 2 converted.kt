@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y22.d02c


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


// Not an actual suggestion to improve, but to see what it would take to do this "not hardcoded"
private fun part1() {
	getLines(2022_02).e().sumOf { (a, _, x) -> (x - 'X' + 1) + 3 * ((x - 'X' - (a - 'A') + 1 + 999) % 3) }.log()
	getLines(2022_02).e().sumOf { (a, _, x) -> (x - 'X' + 1) + 3 * ((x - 'X' - (a - 'A') + 1) % (0..2)) }.log()
	getLines(2022_02).e().sumOf { (a, _, x) -> (x - 'X' + 1) + 3 * ((x - 'X' - (a - 'A') + 1) mod 3) }.log()
	getLines(2022_02).e()
		.map { (a, _, x) -> a - 'A' to x - 'X' }
		.sumOf { (a, x) -> (x + 1) + 3 * (x - a + 1 mod 3) }.log()
	getLines(2022_02).map { (a, _, x) -> a - 'A' to x - 'X' }.sumOf { (a, x) -> (x + 1) + 3 * (x - a + 1 mod 3) }.log()
	getLines(2022_02).map { it.zip("A X", Char::minus) }.sumOf { (a, _, x) -> (x + 1) + 3 * (x - a + 1 mod 3) }.log()
	getLines(2022_02).map { (a, _, x) -> a - 'A' to x - 'X' }.sumOf { (a, x) -> 4 + x + 3 * (x - a mod -1..1) }.log()
	getLines(2022_02).map { it.zip("A X", Char::minus) }.sumOf { (a, _, x) -> 4 + x + 3 * (x - a mod -1..1) }.log()
	getLines(2022_02).map { it.zip("A\u001CX", Char::minus) }.sumOf { (a, u, x) -> u + x + 3 * (x - a mod -1..1) }.log()
}


private fun part2() {
	getLines(2022_02).e().sumOf { (a, _, x) -> (((a - 'A') + (x - 'X') - 1 + 999) % 3) + 1 + 3 * (x - 'X') }.log()
	getLines(2022_02).e().sumOf { (a, _, x) -> (((a - 'A') + (x - 'X') - 1) % (0..2)) + 1 + 3 * (x - 'X') }.log()
	getLines(2022_02).e().sumOf { (a, _, x) -> (((a - 'A') + (x - 'X') - 1) mod 3) + 1 + 3 * (x - 'X') }.log()
	getLines(2022_02).e()
		.map { (a, _, x) -> a - 'A' to x - 'X' }
		.sumOf { (a, x) -> (a + x - 1 mod 3) + 1 + 3 * x }.log()
	getLines(2022_02).e().map { (a, _, x) -> a - 'A' to x - 'X' }.sumOf { (a, x) -> (a + x mod 1..3) + 3 * x }.log()
	getLines(2022_02).map { (a, _, x) -> a - 'A' to x - 'X' }.sumOf { (a, x) -> (a + x mod 1..3) + 3 * x }.log()
	getLines(2022_02).map { it.zip("A X", Char::minus) }.sumOf { (a, _, x) -> (a + x mod 1..3) + 3 * x }.log()
}


fun main() {
	println("Day 2: ")
	part1()
	part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }