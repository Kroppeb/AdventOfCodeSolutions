package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.cartesianPower
import me.kroppeb.aoc.helpers.context.*
import me.kroppeb.aoc.helpers.contextual.*
import me.kroppeb.aoc.helpers.max
import java.math.BigInteger

typealias PointVI = PointV<Int>
typealias PointVL = PointV<Int>
typealias PointVD = PointV<Double>
typealias PointVBI = PointV<BigInteger>

context(opp@InternalExtendedIndirectMathOp<C>)
class PointV<C : Comparable<C>> internal constructor(
	val values: List<C>
) : PointN<PointV<C>, C> {
	private inline fun mapEach(op: context(InternalExtendedIndirectMathOp<C>) (C) -> C): PointV<C> =
		PointV(values.map { op(this@opp, it) })

	private inline fun zipEach(
		other: PointV<C>, op: context(InternalExtendedIndirectMathOp<C>) (C, C) -> C
	): PointV<C> {
		require(other.values.size == this.values.size)

		return PointV(this.values.zip(other.values) { a, b -> op(this@opp, a, b) })
	}

	override fun unaryMinus() = mapEach { -it }

	override fun abs() = mapEach { it.abs() }

	override fun sqrDist(): C = values.map { it * it }.reduce { a, b -> a + b }
	override fun dist(): Double = error("Not supported")
	override fun manDist(): C = values.map { it.abs() }.reduce { a, b -> a + b }
	override fun chebyshevDist(): C = values.map { it.abs() }.max()

	override fun max(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> me.kroppeb.aoc.helpers.max(a, b) }
	override fun min(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> me.kroppeb.aoc.helpers.min(a, b) }


	override fun plus(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> a + b }
	override fun minus(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> a - b }
	override fun times(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> a * b }
	override fun div(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> a / b }
	override fun rem(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> a % b }


	override fun times(other: C): PointV<C> = this.mapEach { it * other }
	override fun div(other: C): PointV<C> = this.mapEach { it / other }
	override fun rem(other: C): PointV<C> = this.mapEach { it % other }

	override fun getVonNeumannNeighbours(): List<PointV<C>> = buildList(values.size * 2) {
		for (i in values.indices) {
			add(PointV(values.toMutableList().also { it[i]++ }))
			add(PointV(values.toMutableList().also { it[i]-- }))
		}
	}


	override fun getMooreNeighbours(): List<PointV<C>> = listOf<(C) -> C>({ it.inc() }, { it }, { it.dec() })
		.cartesianPower(values.size)
		.let { it.subList(0, it.size / 2) + it.subList(it.size / 2 + 1, it.size) }
		.map { ops -> ops.zip(values) { op, x -> op(x) } }
		.map { PointV(it) }


	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is PointV<*>) return false

		return this.values == other.values
	}

	override fun gcd(): C {
		TODO("Not yet implemented")
	}

	override fun hashCode(): Int {
		return values.hashCode()
	}

	override fun toString(): String {
		return values.joinToString(prefix = "<", postfix = ">")
	}

	override fun sign(): PointV<C> = mapEach { it.sign() }

	override fun isZero(): Boolean = values.all { it.isZero() }

	fun getOpp() = this@opp
}


infix fun Int.toPV(other: Int) = with(IntOpps) { PointV(listOf(this@toPV, other)) }

infix fun Long.toPV(other: Long) = with(LongOpps) { PointV(listOf(this@toPV, other)) }
infix fun Double.toPV(other: Double) = with(DoubleOpps) { PointV(listOf(this@toPV, other)) }

infix fun <C : Comparable<C>> PointV<C>.toPV(other: C) = with(this.getOpp()) { PointV(values + listOf(other)) }

@JvmName("ListIntToPointV")
fun List<Int>.toPointV() = with (IntOpps) { PointV(this@toPointV) }

@JvmName("ListLongToPointV")
fun List<Long>.toPointV() = with (LongOpps) { PointV(this@toPointV) }

@JvmName("ListDoubleToPointV")
fun List<Double>.toPointV() = with (DoubleOpps) { PointV(this@toPointV) }