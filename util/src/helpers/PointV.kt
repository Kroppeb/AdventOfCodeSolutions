package helpers

import java.math.BigInteger
import kotlin.math.pow

typealias PointVI = PointV<Int>
typealias PointVL = PointV<Int>
typealias PointVD = PointV<Double>
typealias PointVBI = PointV<BigInteger>

class PointV<C : Comparable<C>> internal constructor(
	val values: List<C>,
	internal val opps: Opps<C>
) : PointN<PointV<C>, C> {
	private inline fun mapEach(op: (C) -> C): PointV<C> = PointV(values.map(op), opps)

	private inline fun zipEach(other: PointV<C>, op: (C, C) -> C): PointV<C> {
		require(other.opps == this.opps)
		require(other.values.size == this.values.size)

		return PointV(this.values.zip(other.values, op), this.opps)
	}

	override fun unaryMinus() = mapEach(opps::unaryMinus)

	override fun abs() = mapEach(opps::unaryMinus)

	override fun discreteAngle(): PointV<C> {
		TODO("Not yet implemented")
	}

	override fun sqrDist(): C = this.values.map(opps::square).reduce(opps::plus)

	override fun dist(): Double {
		TODO("Not yet implemented")
	}

	override fun manDist(): C = this.values.map(opps::abs).reduce(opps::plus)

	override fun max(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> max(a, b) }
	override fun min(other: PointV<C>): PointV<C> = this.zipEach(other) { a, b -> min(a, b) }


	override fun plus(other: PointV<C>): PointV<C> = this.zipEach(other, opps::plus)
	override fun minus(other: PointV<C>): PointV<C> = this.zipEach(other, opps::minus)
	override fun times(other: PointV<C>): PointV<C> = this.zipEach(other, opps::times)
	override fun div(other: PointV<C>): PointV<C> = this.zipEach(other, opps::div)
	override fun rem(other: PointV<C>): PointV<C> = this.zipEach(other, opps::rem)


	override fun times(other: C): PointV<C> = this.mapEach{ opps.times(it, other)}
	override fun div(other: C): PointV<C> = this.mapEach{ opps.div(it, other)}
	override fun rem(other: C): PointV<C> = this.mapEach{ opps.rem(it, other)}

	override fun getVonNeumannNeighbours(): List<PointV<C>> = buildList(this.values.size * 2) {
		for (i in values.indices) {
			add(PointV(values.toMutableList().also { it[i] = opps.inc(it[i]) }, opps))
			add(PointV(values.toMutableList().also { it[i] = opps.dec(it[i]) }, opps))
		}
	}

	override fun getMooreNeighbours(): List<PointV<C>> =
		listOf(opps::inc, { it }, opps::dec).cartesianPower(this.values.size)
			.let { it.subList(0, it.size / 2) + it.subList(it.size / 2 + 1, it.size) }
			.map { ops -> ops.zip(this.values) { op, x -> op(x) } }
			.map { PointV(it, opps) }

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is PointV<*>) return false

		return this.values == other.values && this.opps == other.opps
	}

	override fun gcd(): C {
		TODO("Not yet implemented")
	}

	override fun hashCode(): Int {
		return values.hashCode() * 31 + opps.hashCode()
	}

	override fun toString(): String {
		return values.joinToString(prefix = "<", postfix = ">")
	}
}

internal interface Opps<T : Comparable<T>> {
	fun plus(a: T, b: T): T
	fun minus(a: T, b: T): T
	fun times(a: T, b: T): T
	fun div(a: T, b: T): T
	fun rem(a: T, b: T): T

	fun inc(a: T): T
	fun dec(a: T): T

	fun unaryMinus(a: T): T

	fun gcd(a: T, b: T): T

	fun abs(a: T): T = max(a, unaryMinus(a))


	fun square(a: T) = times(a, a)
}

private val IntOpps = object : Opps<Int> {
	override fun plus(a: Int, b: Int): Int = a + b
	override fun minus(a: Int, b: Int): Int = a - b
	override fun times(a: Int, b: Int): Int = a * b
	override fun div(a: Int, b: Int): Int = a / b
	override fun rem(a: Int, b: Int): Int = a % b

	override fun inc(a: Int): Int = a + 1
	override fun dec(a: Int): Int = a - 1

	override fun unaryMinus(a: Int): Int = -a

	override fun gcd(a: Int, b: Int): Int = gcd(a, b)
}

private val LongOpps = object : Opps<Long> {
	override fun plus(a: Long, b: Long): Long = a + b
	override fun minus(a: Long, b: Long): Long = a - b
	override fun times(a: Long, b: Long): Long = a * b
	override fun div(a: Long, b: Long): Long = a / b
	override fun rem(a: Long, b: Long): Long = a % b

	override fun inc(a: Long): Long = a + 1
	override fun dec(a: Long): Long = a - 1

	override fun unaryMinus(a: Long): Long = -a

	override fun gcd(a: Long, b: Long): Long = gcd(a, b)
}

private val DoubleOpps = object : Opps<Double> {
	override fun plus(a: Double, b: Double): Double = a + b
	override fun minus(a: Double, b: Double): Double = a - b
	override fun times(a: Double, b: Double): Double = a * b
	override fun div(a: Double, b: Double): Double = a / b
	override fun rem(a: Double, b: Double): Double = a % b

	override fun inc(a: Double): Double = a + 1
	override fun dec(a: Double): Double = a - 1

	override fun unaryMinus(a: Double): Double = -a

	override fun gcd(a: Double, b: Double): Double = error("GCD is not defined for doubles")
}

private val BigIntegerOpps = object : Opps<BigInteger> {
	override fun plus(a: BigInteger, b: BigInteger): BigInteger = a + b
	override fun minus(a: BigInteger, b: BigInteger): BigInteger = a - b
	override fun times(a: BigInteger, b: BigInteger): BigInteger = a * b
	override fun div(a: BigInteger, b: BigInteger): BigInteger = a / b
	override fun rem(a: BigInteger, b: BigInteger): BigInteger = a % b

	override fun inc(a: BigInteger): BigInteger = a + BigInteger.ONE
	override fun dec(a: BigInteger): BigInteger = a - BigInteger.ONE

	override fun unaryMinus(a: BigInteger): BigInteger = -a

	override fun gcd(a: BigInteger, b: BigInteger): BigInteger = a.gcd(b)
}

infix fun Int.toPV(other: Int) = PointV(listOf(this, other), IntOpps)
infix fun Long.toPV(other: Long) = PointV(listOf(this, other), LongOpps)
infix fun Double.toPV(other: Double) = PointV(listOf(this, other), DoubleOpps)
infix fun BigInteger.toPV(other: BigInteger) = PointV(listOf(this, other), BigIntegerOpps)

infix fun <C:Comparable<C>> PointV<C>.toPV(other: C) = PointV(this.values + listOf(other), this.opps)

@JvmName("ListIntToPointV")
fun List<Int>.toPointV() = PointV(this, IntOpps)
@JvmName("ListLongToPointV")
fun List<Long>.toPointV() = PointV(this, LongOpps)
@JvmName("ListDoubleToPointV")
fun List<Double>.toPointV() = PointV(this, DoubleOpps)
@JvmName("ListBigIntegerToPointV")
fun List<BigInteger>.toPointV() = PointV(this, BigIntegerOpps)