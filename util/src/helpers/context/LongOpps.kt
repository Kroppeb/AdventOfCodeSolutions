package helpers.context

import kotlin.math.sign

object LongOpps : InternalExtendedFinalMathOp<Long> {
	override operator fun Long.plus(other: Long): Long = this + other
	override operator fun Long.minus(other: Long): Long = this - other
	override operator fun Long.times(other: Long): Long = this * other
	override operator fun Long.div(other: Long): Long = this / other
	override operator fun Long.rem(other: Long): Long = this % other

	override /* operator */ fun Long.inc(): Long = this + 1
	override /* operator */ fun Long.dec(): Long = this - 1

	override fun Long.unaryMinus(): Long = -this
	override fun Long.sign(): Long = this.sign.toLong()

//	override fun gcd(a: Long, b: Long): Long = gcd(a, b)
//
//	override fun Long.abs(): Long = kotlin.math.abs(this)
}