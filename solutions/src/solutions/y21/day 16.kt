@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d16

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*

val xxxxx = Clock(6, 3);

/*

*/

data class Parsed<T>(val t: T, val remaining: List<Int>) {
    fun <R> map(p: (T) -> R): Parsed<R> {
        return Parsed(p(t), remaining)
    }
}

var versions = 0L

fun parseInt(data: List<Int>, length: Int): Long {
    var x = 0L;
    repeat(length) {
        x = x * 2 + data[it]
    }
    return x;
}

fun parse(data: List<Int>): Parsed<Long> {
    data.map { it.toString() }.joinToString("")/*.log()*/
    val version = data[0] * 4 + data[1] * 2 + data[2]
    versions += version/*.log()*/
    val opcode = data[3] * 4 + data[4] * 2 + data[5]
    when (opcode/*.log()*/) {
        4 -> {
            var i = 6
            var res = 0L
            while (data[i] != 0) {
                var section = data[i + 1] * 8 + data[i + 2] * 4 + data[i + 3] * 2 + data[i + 4]
                i += 5
                res = res * 16L + section
            }
            var section = data[i + 1] * 8 + data[i + 2] * 4 + data[i + 3] * 2 + data[i + 4]
            i += 5
            res = res * 16L + section
            //res.log()
            return Parsed(res, data.subList(i, data.size))
        }
        0 ->
            return parseSub(data.subList(6, data.size)).map { it.sum() }
        1 ->
            return parseSub(data.subList(6, data.size)).map { it.fold(1L, Long::times) }
        2 ->
            return parseSub(data.subList(6, data.size)).map { it.min() }
        3 ->
            return parseSub(data.subList(6, data.size)).map { it.max() }
        5 ->
            return parseSub(data.subList(6, data.size)).map { (a, b) -> if (a > b) 1 else 0 }
        6 ->
            return parseSub(data.subList(6, data.size)).map { (a, b) -> if (a < b) 1 else 0 }
        7 ->
            return parseSub(data.subList(6, data.size)).map { (a, b) -> if (a == b) 1 else 0 }
        else -> error("Unknown opcode $opcode")
    }
}

fun parseSub(data: List<Int>): Parsed<List<Long>> {
    if (data[0] == 0) {
        val size = parseInt(data.subList(1, data.size), 15)
        var d = data.subList(16, data.size)
        var remainingSize = d.size - size
        val list = mutableListOf<Long>()
        while (d.size > remainingSize) {
            var (t, remaining) = parse(d)
            d = remaining;
            list.add(t)
        }
        return Parsed(list, d)
    } else {
        var count = parseInt(data.subList(1, data.size), 11)
        var d = data.subList(12, data.size)
        val list = mutableListOf<Long>()
        repeat(count.toInt()) {
            var (t, remaining) = parse(d)
            d = remaining
            list.add(t)
        }
        return Parsed(list, d)
    }
}

private fun parts() {
    var data = getLines(2021_16).first().flatMap {
        when (it) {
            '0' -> listOf(0, 0, 0, 0)
            '1' -> listOf(0, 0, 0, 1)
            '2' -> listOf(0, 0, 1, 0)
            '3' -> listOf(0, 0, 1, 1)
            '4' -> listOf(0, 1, 0, 0)
            '5' -> listOf(0, 1, 0, 1)
            '6' -> listOf(0, 1, 1, 0)
            '7' -> listOf(0, 1, 1, 1)
            '8' -> listOf(1, 0, 0, 0)
            '9' -> listOf(1, 0, 0, 1)
            'A' -> listOf(1, 0, 1, 0)
            'B' -> listOf(1, 0, 1, 1)
            'C' -> listOf(1, 1, 0, 0)
            'D' -> listOf(1, 1, 0, 1)
            'E' -> listOf(1, 1, 1, 0)
            'F' -> listOf(1, 1, 1, 1)
            else -> error(it)
        }
    }

    //data.map{ it.toString() }.joinToString("").log()
    var parsed = parse(data)

    println(versions)
    println(parsed.t)
}


fun main() {
    println("Day 16: ")
    parts()
}


fun <T> T.log(): T = also { println(this) }