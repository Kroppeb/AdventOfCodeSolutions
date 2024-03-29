@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d11

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_11).digits().map{it.toMutableList()}.toMutableList()


    var count = 0L;
    repeat(100){
        var mutate = true;
        var points = mutableSetOf<Pair<Int,Int>>()
        for(l in data) for(i in data.indices)
            l[i]++

        while(mutate){
            mutate = false;

            for((x,l) in data.withIndex()){
                for((y, i) in l.withIndex()){
                    if(i > 9 && points.add(x to y)){
                        mutate = true;

                        for(dx in -1..1)
                            for(dy in -1..1)
                                if(x+dx in data.indices && y+dy in data[x+dx].indices && !(dx == 0 && dy == 0) ){
                                    data[x+dx][y+dy]++
                                }
                    }
                }
            }

            // data.map{it.log()}
            // "".log()
        }

        for((x,y) in points){
            data[x][y] = 0
        }

        count += points.size;
    }


    count.log()


}


private fun part2() {
    var data = getLines(2021_11).digits().map{it.toMutableList()}.toMutableList()


    var count = 0L;
    repeat(100000){
        var mutate = true;
        var points = mutableSetOf<Pair<Int,Int>>()
        for(l in data) for(i in data.indices)
            l[i]++

        while(mutate){
            mutate = false;

            for((x,l) in data.withIndex()){
                for((y, i) in l.withIndex()){
                    if(i > 9 && points.add(x to y)){
                        mutate = true;

                        for(dx in -1..1)
                            for(dy in -1..1)
                                if(x+dx in data.indices && y+dy in data[x+dx].indices && !(dx == 0 && dy == 0) ){
                                    data[x+dx][y+dy]++
                                }
                    }
                }
            }

            // data.map{it.log()}
            // "".log()
        }

        for((x,y) in points){
            data[x][y] = 0
        }

        count += points.size;

        if(points.size == 100) {
            // was `error` during aoc, instead of `print` and `return`
            print("" + (it + 1))
            return
        }
    }

    for (datum in data) {
        println(datum)
    }


    count.log()


}


fun main() {
    println("Day 11: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }