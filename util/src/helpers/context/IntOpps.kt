package helpers.context

import kotlin.math.sign

object IntOpps : InternalExtendedFinalMathOp<Int> {
	override operator fun Int.plus(other: Int): Int = this + other
	override operator fun Int.minus(other: Int): Int = this - other
	override operator fun Int.times(other: Int): Int = this * other
	override operator fun Int.div(other: Int): Int = this / other
	override operator fun Int.rem(other: Int): Int = this % other

	override /* operator */ fun Int.inc(): Int = this + 1
	override /* operator */ fun Int.dec(): Int = this - 1

	override fun Int.unaryMinus(): Int = -this
	override fun Int.sign(): Int = this.sign

//	override fun gcd(a: Int, b: Int): Int = gcd(a, b)
//
//	override fun Int.abs(): Int = kotlin.math.abs(this)
}