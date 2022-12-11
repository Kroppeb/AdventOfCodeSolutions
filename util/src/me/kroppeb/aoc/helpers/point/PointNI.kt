package me.kroppeb.aoc.helpers.point

interface PointNI<T : PointNI<T>> : PointN<T, Int> {

	override operator fun times(other: Int): T
	override operator fun div(other: Int): T
	override operator fun rem(other: Int): T


	override fun sqrDist(): Int
	override fun dist(): Double
	override fun manDist(): Int
	override fun chebyshevDist(): Int

	override fun sqrDistTo(other: T): Int = (this - other).sqrDist()
	override fun distTo(other: T): Double = (this - other).dist()
	override fun manDistTo(other: T): Int = (this - other).manDist()
	override fun chebyshevDistTo(other: T): Int = (this - other).chebyshevDist()

	override fun gcd(): Int

	fun dot(other: T): Int

	override fun isZero(): Boolean = this.manDist() == 0
}