package solutions

import helpers.*
import kotlin.math.*

private fun part1(data:List<List<String>>){
	val parent = data.map { (a,b) -> b to a}.toMap()
	var orbits = parent.keys.toList()
	var count = 0
	while(orbits.isNotEmpty()){
		count += orbits.size
		orbits = orbits.mapNotNull{parent[it]}.filter{it in parent}
	}
	println(count)
}

private fun part2(data:List<List<String>>){
	val parent = data.map { (a,b) -> b to a}.toMap()

	fun steps(location: MutableMap<String,Int>, steps:Int, self:String){
		location[self] = steps
		parent[self]?.let{steps(location, steps +1,it)}
	}

	val stepsFromMe = mutableMapOf<String,Int>()
	steps(stepsFromMe, -1, "YOU")
	fun stepsS(steps:Int, self:String){
		stepsFromMe[self]?.let{error(it + steps)}
		parent[self]?.let{stepsS(steps +1,it)}
	}
	stepsS(-1,"SAN")



}

fun main(){
	val data = getLines(6).map{it.split(")")}
	part1(data)
	part2(data)
}