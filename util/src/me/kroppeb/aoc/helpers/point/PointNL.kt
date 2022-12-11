package me.kroppeb.aoc.helpers.point

interface PointNL<T : PointNL<T>> : PointN<T, Long> {
	override operator fun times(other: Long): T
	override operator fun div(other: Long): T
	override operator fun rem(other: Long): T

	override fun sqrDist(): Long
	override fun dist(): Double
	override fun manDist(): Long
	override fun chebyshevDist(): Long

	override fun sqrDistTo(other: T): Long = (this - other).sqrDist()
	override fun distTo(other: T): Double = (this - other).dist()
	override fun manDistTo(other: T): Long = (this - other).manDist()
	override fun chebyshevDistTo(other: T): Long = (this - other).chebyshevDist()

	override fun gcd(): Long

	fun dot(other: T): Long
	override fun isZero(): Boolean = this.manDist() == 0L
}