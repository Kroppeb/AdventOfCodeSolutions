package solutions

import helpers.*
import kotlin.math.*

private fun part1(){
	val paswords = 145852..616942
	println(paswords.map{it.toString().toList()}.filter{
		it.zip(it.drop(1)).any{(a,b) -> a==b}
				&&
				it.zip(it.drop(1)).all{(a,b)->a<=b}
	}.count())

}

private fun part2(){
	val paswords = 145852..616942
	println(paswords.map{it.toString()}.filter{
		Regex("(.)\\1+").findAll(it).any{it.value.length == 2}
				&&
				it.zip(it.drop(1)).all{(a,b)->a<=b}
	}.count())

}


fun main(){
	part1()
	part2()
}