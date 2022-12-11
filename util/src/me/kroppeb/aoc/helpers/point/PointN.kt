package me.kroppeb.aoc.helpers.point

interface PointN<T : PointN<T, C>, C : Comparable<C>> {

	fun getVonNeumannNeighbours(): List<T>
	fun getMooreNeighbours(): List<T>

	operator fun unaryMinus(): T

	operator fun minus(other: T): T
	operator fun plus(other: T): T

	operator fun times(other: T): T
	operator fun div(other: T): T
	operator fun rem(other: T): T

	operator fun times(other: C): T
	operator fun div(other: C): T
	operator fun rem(other: C): T

	fun abs(): T

	fun discreteAngle(): T {
		val g = gcd()
		return this / g
	}

	fun min(other: T): T
	fun max(other: T): T

	fun sqrDist(): C
	fun dist(): Double
	fun manDist(): C
	fun chebyshevDist(): C

	fun sqrDistTo(other: T): C = (this - other).sqrDist()
	fun distTo(other: T): Double = (this - other).dist()
	fun manDistTo(other: T): C = (this - other).manDist()
	fun chebyshevDistTo(other: T): C = (this - other).chebyshevDist()

	fun gcd(): C

	/**
	 * includes self
	 */
	fun sequence(step: T): Sequence<T> = generateSequence(this as T) { it + step }
	fun sign(): T
	fun isZero(): Boolean
}