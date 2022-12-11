@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d4

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*

private val xxxxx = Clock(6, 3);

/*

*/

private fun finish(grid: List<List<Int>>, seen: Set<Int>, i: Int){
    (grid.flatten().filter { it !in seen }.sum() * i).log()
    grid.log()
    seen.log()
}

private fun part1(data: Data) {
    data.splitOn { it.isEmpty() }
    var dd = data.splitOn { it.isEmpty() }
    val items = dd.first().first().split(',').map{it.toInt()}
    dd = dd.subList(1, dd.size);
    var grids = dd.map{it.map{it.split(" ").filter{!it.isEmpty()}.map{it.toInt()}}}


    val seen = mutableSetOf<Int>()
    for(i in items){
        seen.add(i)

        // check bingo
        for (grid in grids) {
            for (list in grid) {
                if (list.all { it in seen }){
                    finish(grid, seen, i)
                    return;
                }
            }

            for (list in grid.transpose()) {
                if (list.all { it in seen }){
                    finish(grid, seen, i)
                    return;
                }
            }
        }
    }
}

private fun part2(data: Data) {
    var dd = data.splitOn { it.isEmpty() }
    val items = dd.first().first().split(',').map{it.toInt()}
    dd = dd.subList(1, dd.size);
    var grids = dd.map{it.map{it.split(" ").filter{!it.isEmpty()}.map{it.toInt()}}}


    val seen = mutableSetOf<Int>()
    val won = mutableSetOf<List<List<Int>>>()
    for(i in items){
        won.size.log()
        seen.add(i)

        // check bingo
        for (grid in grids) {
            if(grid in won)
                continue;

            for (list in grid) {
                if (list.all { it in seen }){
                    won.add(grid)
                    if(won.size == grids.size){
                    finish(grid, seen, i)
                    return;}
                }
            }

            for (list in grid.transpose()) {
                if (list.all { it in seen }){
                    won.add(grid)
                    if(won.size == grids.size) {
                        finish(grid, seen, i)
                        return;
                    }
                }
            }
        }
    }
}

private typealias Data = Lines

fun main() {
    println("Day 4: ")
    val data: Data = getLines(2021_04)
    part1(data)
    part2(data)
}


private fun <T> T.log(): T = also { println(this) }