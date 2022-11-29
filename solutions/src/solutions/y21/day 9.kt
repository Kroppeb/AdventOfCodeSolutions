@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d9

/*
import grid.Clock
import helpers.*
import itertools.*
import kotlin.math.*
 */

import grid.Clock
import helpers.digits
import helpers.getLines
import helpers.map2
import helpers.transpose

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_09).digits()//.log()

    var count = 0

    var pre = data.first().map { 9 }
    data = listOf(listOf(pre), data, listOf(pre)).flatten().transpose()
    pre = data.first().map { 10 }
    data = listOf(listOf(pre), data, listOf(pre)).flatten().transpose()

    data.windowed(3) {
        it.transpose().windowed(3) { (a, b, c) ->
            if (a[1] > b[1] && b[1] < c[1] && b[0] > b[1] && b[1] < b[2]) count += b[1] + 1;
        }
    }


    count.log()
}


private fun part2() {
    var data = getLines(2021_09).digits()

    var count = 0

    var pre = data.first().map { 9 }
    data = listOf(listOf(pre), data, listOf(pre)).flatten().transpose()
    pre = data.first().map { 10 }
    data = listOf(listOf(pre), data, listOf(pre)).flatten().transpose()

    var x = true;
    var uuu = data.map2 { mutableListOf(it, (if (it < 9) 1 else 0)) }
    while (x) {
        x = false
        //uuu.log()
        uuu.forEach {
            it.windowed(2) { (a, b) ->
                if(a[1] != 0 || b[1] != 0){
                    if(a[0] < b[0]){
                        x = x || (b[1] != 0)
                        a[1] += b[1]
                        b[1] = 0
                    } else {
                        x = x || (a[1] != 0)
                        b[1] += a[1]
                        a[1] = 0
                    }
                }
            }
        }
        uuu.transpose().forEach {
            it.windowed(2) { (a, b) ->
                if(a[1] != 0 || b[1] != 0){
                    if(a[0] < b[0]){
                        x = x || (b[1] != 0)
                        a[1] += b[1]
                        b[1] = 0
                    } else {
                        x = x || (a[1] != 0)
                        b[1] += a[1]
                        a[1] = 0
                    }
                }
            }
        }
    }

    //uuu.map2 { it[1] }.flatten().filter { it != 0 }.sorted().log()
    var (a,b,c) = uuu.map2 { it[1] }.flatten().filter { it != 0 }.sorted().reversed()
    println(a * b * c)
}


fun main() {
    println("Day  9: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }