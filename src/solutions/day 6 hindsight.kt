package solutions

import helpers.*
import kotlin.math.*

private fun part1(data: List<List<String>>) {
	val parent = data.associate { (a, b) -> b to a }.toMap()

	val orbits = parent.keys + parent.values
	println(orbits.sumBy { generateSequence(it, parent::get).count()-1})
}

private fun part2(data: List<List<String>>) {
	val parent = data.map { (a, b) -> b to a }.toMap()


	val stepsFromMe = generateSequence("YOU", parent::get).withIndex().associate{ it.value to it.index}.toMap()
	for((i, l) in generateSequence( "SAN", parent::get).withIndex() ){
		stepsFromMe[l]?.let{
			println(it + i-2)
			return
		}
	}

}

fun main() {
	val data = getAlphaNumLines(6)
	part1(data)
	part2(data)
}