@file:Suppress("PackageDirectoryMismatch")
package solutions.solutions.y19.d12

import me.kroppeb.aoc.helpers.*
import me.kroppeb.aoc.helpers.point.Point3DI
import me.kroppeb.aoc.helpers.point.toPI
import kotlinx.coroutines.runBlocking
import kotlin.math.*

data class Moon(var p: Point3DI, var v: Point3DI)

private fun part1(data: IntLines)= runBlocking {
	val moons = data.map{(x,y,z) -> Moon(x toPI y toPI z, 0 toPI 0 toPI 0) }
	repeat(1000){
		moons.forEach{ moon ->
			moons.forEach {
				val (dx,dy,dz) = it.p - moon.p
				moon.v += dx.sign toPI dy.sign toPI dz.sign
			}
		}

		moons.forEach{
			it.p += it.v
		}
	}
	println(
			moons.sumBy{
				it.p.manDist() * it.v.manDist()
			}
	)
}
private fun part2(data: IntLines) = runBlocking {
	val moons = data.map{(x,y,z) -> Moon(x toPI y toPI z, 0 toPI 0 toPI 0) }
	val orig = moons.map{it.copy()}
	var x = 0L
	var y = 0L
	var z = 0L
	var ite = 0L
	while(x == 0L || y == 0L || z == 0L){
		ite+=1
		moons.forEach{ moon ->
			moons.forEach {
				val (dx,dy,dz) = it.p - moon.p
				moon.v += dx.sign toPI dy.sign toPI dz.sign
			}
		}

		moons.forEach{
			it.p += it.v
		}

		if(x == 0L && moons.zip(orig).all{(it, o) -> it.p.x == o.p.x && it.v.x == 0})
			x = ite

		if(y == 0L && moons.zip(orig).all{(it, o) -> it.p.y == o.p.y && it.v.y == 0})
			y = ite

		if(z == 0L && moons.zip(orig).all{(it, o) -> it.p.z == o.p.z && it.v.z == 0})
			z = ite
	}

	val q = x / gcd(x, y) * y
	println(z / gcd(z, q) * q)
}

private fun gcd(a:Long, b:Long):Long = if(a == 0L) b else gcd(b % a, a)

fun main() {
	val data: IntLines = getIntLines(2019_12)
	part1(data)
	part2(data)
}