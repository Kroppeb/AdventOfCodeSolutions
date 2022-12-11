@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d5

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1(data: Data) {
    var q  = data.map{it.split(" -> ")}.map{it.map{it.getInts()}.map{(a,b) -> a to b}}
    var points = mutableSetOf<Pair<Int,Int>>()
    var p2 = mutableSetOf<Pair<Int,Int>>()

    for ((s,e) in q) {
        if(s.first == e.first){
            for(i in min(s.second,e.second)..max(s.second,e.second)){
                if(!points.add(s.first to i))
                    p2.add(s.first to i)
            }
        }
        if(s.second == e.second){
            for(i in min(s.first,e.first)..max(s.first,e.first)){
                if(!points.add(i to s.second))
                    p2.add(i to s.second)
            }
        }
    }

    p2.size.log()
}

private fun part2(data: Data) {
    var q  = data.map{it.split(" -> ")}.map{it.map{it.getInts()}.map{(a,b) -> a to b}}
    var points = mutableSetOf<Pair<Int,Int>>()
    var p2 = mutableSetOf<Pair<Int,Int>>()

    for ((s,e) in q) {
        if(s.first == e.first){
            for(i in min(s.second,e.second)..max(s.second,e.second)){
                if(!points.add(s.first to i))
                    p2.add(s.first to i)
            }
        } else
            if(s.second == e.second){
                for(i in min(s.first,e.first)..max(s.first,e.first)){
                    if(!points.add(i to s.second))
                        p2.add(i to s.second)
                }
            } else {
                val dx = e.first - s.first
                val dy = e.second - s.second
                val d = abs(dx)
                val xx = dx/d
                val yy = dy/d
                for(i in 0..d){
                    val x = s.first + i*xx
                    val y = s.second + i*yy
                    if(!points.add(x to y))
                        p2.add(x to y)
                }
            }
    }

    p2.size.log()
}

private typealias Data = Lines

fun main() {
    println("Day 5: ")
    val data: Data = getLines(2021_05)
    part1(data)
    part2(data)
}


private fun <T> T.log(): T = also { println(this) }