package solutions

import helpers.*
import kotlin.math.*

private fun part1(data:List<Int>){
	IntComputer(data,listOf(1)).runBlocked().print()
}

private fun part2(data:List<Int>){
	IntComputer(data,listOf(5)).runBlocked().print()
}

fun main(){
	val data = getInts(5)
	IntComputer(data).runBlocked().print()
	part1(data)
	part2(data)



















}