@file:Suppress("PackageDirectoryMismatch", "UnusedImport")

package solutions.y21.d11c

/*
import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.*
import itertools.*
import kotlin.math.*
 */

import me.kroppeb.aoc.helpers.Clock

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.grid.mutableGrid
import me.kroppeb.aoc.helpers.point.Point

private val xxxxx = Clock(6, 3);

/*

*/

private fun part1() {
    var data = getLines(2021_11).digits().mutableGrid()

    var count = 0L;
    repeat(100){
		data.bounds.forEach { data[it]++ }

		val flashed = msop()

		fun flash(point: Point) {
			if(!flashed.add(point)) return
			point.getOctNeighbours().forEach{p ->
				if(p in data.bounds) {
					data[p] += 1
					if (data[p] > 9) flash(p)
				}
			}
		}

		data.bounds.forEach { if(data[it] > 9) flash(it) }

		count += flashed.size
		flashed.forEach { data[it] = 0 }
    }



    count.log()


}


private fun part2() {
	var data = getLines(2021_11).digits().mutableGrid()

	loop{i->
		data.bounds.forEach { data[it]++ }

		val flashed = msop()

		fun flash(point: Point) {
			if(!flashed.add(point)) return
			point.getOctNeighbours().forEach{p ->
				if(p in data.bounds) {
					data[p] += 1
					if (data[p] > 9) flash(p)
				}
			}
		}

		data.bounds.forEach { if(data[it] > 9) flash(it) }

		flashed.forEach { data[it] = 0 }
		if(data.allItems().all{it == 0}) {
			(i + 1).log()
			return
		}
	}


}


fun main() {
    println("Day 11: ")
    part1()
    part2()
}


private fun <T> T.log(): T = also { println(this) }