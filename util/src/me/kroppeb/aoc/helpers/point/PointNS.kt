package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

interface PointNS<T : PointNS<T>> : PointN<T, Sint> {

	override operator fun times(other: Sint): T
	override operator fun div(other: Sint): T
	override operator fun rem(other: Sint): T
	operator fun times(other: Int): T = this.times(other.s)
	operator fun div(other: Int): T = this.div(other.s)
	operator fun rem(other: Int): T = this.rem(other.s)
	operator fun times(other: Long): T = this.times(other.s)
	operator fun div(other: Long): T = this.div(other.s)
	operator fun rem(other: Long): T = this.rem(other.s)


	override fun sqrDist(): Sint
	override fun dist(): Double
	override fun manDist(): Sint
	override fun chebyshevDist(): Sint

	override fun sqrDistTo(other: T): Sint = (this - other).sqrDist()
	override fun distTo(other: T): Double = (this - other).dist()
	override fun manDistTo(other: T): Sint = (this - other).manDist()
	override fun chebyshevDistTo(other: T): Sint = (this - other).chebyshevDist()

	override fun gcd(): Sint

	fun dot(other: T): Sint

	override fun isZero(): Boolean = this.manDist() == 0.s
}