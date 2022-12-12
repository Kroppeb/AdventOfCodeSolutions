package me.kroppeb.aoc.helpers.sint

import me.kroppeb.aoc.helpers.context.LongOpps.sign
import kotlin.jvm.JvmInline


@JvmInline
value class Sint(private val inner:Long) : Comparable<Sint> {
	val l: Long get() = inner
	val i: Int get() = Math.toIntExact(inner)

	operator fun plus(other: Sint) = Sint(Math.addExact(inner, other.inner))
	operator fun minus(other: Sint) = Sint(Math.subtractExact(inner, other.inner))
	operator fun times(other: Sint) = Sint(Math.multiplyExact(inner, other.inner))
	// TODO: consider whether I want floorDiv or div
	operator fun div(other: Sint) = Sint(inner / other.inner)
	operator fun rem(other: Sint) = Sint(inner % other.inner)

	operator fun unaryMinus() = Sint(Math.negateExact(inner))

	operator fun inc() = Sint(Math.incrementExact(inner))
	operator fun dec() = Sint(Math.decrementExact(inner))

	override operator fun compareTo(other: Sint) = inner.compareTo(other.inner)

	override fun toString() = inner.toString()

	fun sign() = Sint(inner.sign())

	operator fun rangeTo(other: Sint): SintRange = SintRange(this, other)
	operator fun rangeUntil(other: Sint): SintRange = SintRange(this, other - 1.s)
	infix fun until(other: Sint): SintRange = SintRange(this, other - 1.s)
	infix fun downTo(other: Sint): SintProgression = SintProgression(this, other, -1.s)

	infix fun shl(other: Sint) = Sint(inner shl other.inner.toInt())
	infix fun shr(other: Sint) = Sint(inner shr other.inner.toInt())
	infix fun ushr(other: Sint) = Sint(inner ushr other.inner.toInt())
	infix fun and(other: Sint) = Sint(inner and other.inner)
	infix fun or(other: Sint) = Sint(inner or other.inner)
	infix fun xor(other: Sint) = Sint(inner xor other.inner)
	fun inv() = Sint(inner.inv())

	companion object {
		val ZERO = Sint(0)
		val ONE = Sint(1)
	}
}