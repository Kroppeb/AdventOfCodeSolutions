package solutions

import helpers.*
import kotlin.math.*

private fun part1(data:List<Int>){
	IntComputer(data,listOf(1)).run()
}

private fun part2(data:List<Int>){
	IntComputer(data,listOf(5)).run()
}

fun main(){
	val data = getInts(5)
	IntComputer(data).run()
	part1(data)
	part2(data)



















}