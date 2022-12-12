package me.kroppeb.aoc.helpers.context

import me.kroppeb.aoc.helpers.sint.Sint

object SintOpps : InternalExtendedFinalMathOp<Sint>, CanBeZeroTrait<Sint> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	override operator fun Sint.plus(other: Sint): Sint = this + other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("minusFinal")
	override operator fun Sint.minus(other: Sint): Sint = this - other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("timesFinal")
	override operator fun Sint.times(other: Sint): Sint = this * other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("divFinal")
	override operator fun Sint.div(other: Sint): Sint = this / other
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("remFinal")
	override operator fun Sint.rem(other: Sint): Sint = this % other

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("incFinal")
	override /* operator */ fun Sint.inc(): Sint = this + Sint.ONE
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("decFinal")
	override /* operator */ fun Sint.dec(): Sint = this - Sint.ONE

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("unaryMinusFinal")
	override fun Sint.unaryMinus(): Sint = -this

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("signFinal")
	override fun Sint.sign(): Sint = this.sign()

	override fun Sint.isZero(): Boolean = this == Sint.ZERO
//	override fun gcd(a: Sint, b: Sint): Sint = gcd(a, b)
//
//	override fun Sint.abs(): Sint = kotlin.math.abs(this)
}