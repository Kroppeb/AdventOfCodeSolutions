package me.kroppeb.aoc.helpers.sint

import me.kroppeb.aoc.helpers.context.LongOpps.sign
import kotlin.jvm.JvmInline

private val SAFE_INT_MAX = (1 shl 32) - 1
private val SAFE_INT_MIN = -SAFE_INT_MAX
private var has_warned_negative_division_round = false
private var has_warned_negative_rem = false

@JvmInline
value class Sint(private val inner: Long) : Comparable<Sint> {
	val l: Long get() = inner
	val i: Int get() = Math.toIntExact(inner)
	val d: Double
		get() {
			if (l in SAFE_INT_MIN..SAFE_INT_MAX) {
				return i.toDouble()
			}
			throw ArithmeticException("Sint overflow: $this")
		}

	fun canBeExactInt() = inner in Int.MIN_VALUE..Int.MAX_VALUE

	operator fun plus(other: Sint) = Sint(Math.addExact(inner, other.inner))
	operator fun minus(other: Sint) = Sint(Math.subtractExact(inner, other.inner))
	operator fun times(other: Sint) = Sint(Math.multiplyExact(inner, other.inner))

	// TODO: consider whether I want floorDiv or div
	operator fun div(other: Sint): Sint {
		if (!has_warned_negative_division_round && inner < 0 && inner % other.inner != 0L) {
			// warn
			has_warned_negative_division_round = true
			System.err.println("Warning: negative division rounding")
		}
		return Sint(inner / other.inner)
	}

	operator fun rem(other: Sint): Sint {
		if (has_warned_negative_rem && inner < 0 && inner % other.inner != 0L) {
			// warn
			has_warned_negative_rem = true
			System.err.println("Warning: negative remainder")
		}
		return Sint(inner % other.inner)
	}

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

		val MAX_VALUE = Sint(Long.MAX_VALUE)
		val MIN_VALUE = Sint(Long.MIN_VALUE)

		// can be used as "infinities" but with a lower chance of overflowing
		// max and min are about 9.2 times larger than mega
		val NEG_MEGA = Sint(-1_000_000_000_000_000_000L)
		val POS_MEGA = Sint(1_000_000_000_000_000_000L)
	}
}