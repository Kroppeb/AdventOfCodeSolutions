package me.kroppeb.aoc.helpers.context

import kotlin.math.sign

object IntOpps : InternalExtendedFinalMathOp<Int>, CanBeZeroTrait<Int> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	override operator fun Int.plus(other: Int): Int = this + other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("minusFinal")
	override operator fun Int.minus(other: Int): Int = this - other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("timesFinal")
	override operator fun Int.times(other: Int): Int = this * other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("divFinal")
	override operator fun Int.div(other: Int): Int = this / other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("remFinal")
	override operator fun Int.rem(other: Int): Int = this % other

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("incFinal")
	override /* operator */ fun Int.inc(): Int = this + 1
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("decFinal")
	override /* operator */ fun Int.dec(): Int = this - 1

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("unaryMinusFinal")
	override fun Int.unaryMinus(): Int = -this

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("signFinal")
	override fun Int.sign(): Int = this.sign
	override fun Int.isZero(): Boolean = this == 0

//	override fun gcd(a: Int, b: Int): Int = gcd(a, b)
//
//	override fun Int.abs(): Int = kotlin.math.abs(this)
}