package me.kroppeb.aoc.helpers.context

import kotlin.math.sign

object LongOpps : InternalExtendedFinalMathOp<Long>, CanBeZeroTrait<Long> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	override operator fun Long.plus(other: Long): Long = this + other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("minusFinal")
	override operator fun Long.minus(other: Long): Long = this - other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("timesFinal")
	override operator fun Long.times(other: Long): Long = this * other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("divFinal")
	override operator fun Long.div(other: Long): Long = this / other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("remFinal")
	override operator fun Long.rem(other: Long): Long = this % other

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("incFinal")
	override /* operator */ fun Long.inc(): Long = this + 1
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("decFinal")
	override /* operator */ fun Long.dec(): Long = this - 1

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("unaryMinusFinal")
	override fun Long.unaryMinus(): Long = -this

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("signFinal")
	override fun Long.sign(): Long = this.sign.toLong()

	override fun Long.isZero(): Boolean = this == 0L
//	override fun gcd(a: Long, b: Long): Long = gcd(a, b)
//
//	override fun Long.abs(): Long = kotlin.math.abs(this)
}