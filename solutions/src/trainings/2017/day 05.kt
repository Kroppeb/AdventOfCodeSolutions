@file:Suppress("PackageDirectoryMismatch", "PackageName")
package trainings.`2017`.`05`
import helpers.*
import grid.*
import kotlinx.coroutines.*
import kotlin.math.abs

private val xxxxx = Clock(3,6);

private fun part1(data: Data) {
	val d = data.toMutableList()
	var i = 0
	var s = 0
	while(i < d.size){
		s++
		i+=d[i]++
	}
	println(s)
}

private fun part2(data: Data)  {
	val d = data.toMutableList()
	var i = 0
	var s = 0
	while(i < d.size){
		s++
		val p = d[i]
		if(p >= 3)
			d[i]--
		else
			d[i]++
		i+=p
	}
	println(s)
}

private typealias Data = List<Int>


fun main() {
	val data: Data = getIntLines(2017_05).map{it.first()}
	part1(data)
	part2(data)
}