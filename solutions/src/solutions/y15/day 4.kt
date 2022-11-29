@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y15.d04

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*

import java.math.BigInteger
import java.security.MessageDigest


private val xxxxx = Clock(6, 3);

/*

*/
fun md5(input:String): ByteArray {
    val md = MessageDigest.getInstance("MD5")
    return md.digest(input.toByteArray())
}

private fun part1() {
    var data = getLines(2015_04).first()

    var x = 0;

    while(true) {
        x++

        val hash = md5(data + x)
        if (hash[0] == 0x0.toByte() && hash[1] == 0.toByte() && hash[2] <= 0x0f && hash[2] >= 0) {
            x.log()
            return
        }

    }
}

private fun part2() {
    var data = getLines(2015_04).first()
    var x = 0;

    while(true) {
        x++

        val hash = md5(data + x)
        if (hash[0] == 0x0.toByte() && hash[1] == 0.toByte() && hash[2] == 0.toByte()) {
            x.log()
            return
        }

    }
}


fun main() {
    println("Day 4: ")
    part1()
    part2()
}


private var _logIndex = 0
private fun <T> T.log(): T = also { println("%03d %03d:\t\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }