package me.kroppeb.aoc.helpers.context

import kotlin.math.sign

object DoubleOpps : InternalExtendedFinalMathOp<Double>, CanBeZeroTrait<Double> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	override operator fun Double.plus(other: Double): Double = this + other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("minusFinal")
	override operator fun Double.minus(other: Double): Double = this - other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("timesFinal")
	override operator fun Double.times(other: Double): Double = this * other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("divFinal")
	override operator fun Double.div(other: Double): Double = this / other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("remFinal")
	override operator fun Double.rem(other: Double): Double = this % other

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("incFinal")
	override /* operator */ fun Double.inc(): Double = this + 1
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("decFinal")
	override /* operator */ fun Double.dec(): Double = this - 1

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("unaryMinusFinal")
	override fun Double.unaryMinus(): Double = -this

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("signFinal")
	override fun Double.sign(): Double = this.sign
	override fun Double.isZero(): Boolean = this == 0.0

//	override fun gcd(a: Double, b: Double): Double = gcd(a, b)
//
//	override fun Double.abs(): Double = kotlin.math.abs(this)
}