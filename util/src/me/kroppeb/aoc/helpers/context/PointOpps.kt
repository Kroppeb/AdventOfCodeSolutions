package me.kroppeb.aoc.helpers.context


import me.kroppeb.aoc.helpers.point.PointN


// add, sub, neg, sign
object PointNOpps :
	InternalIndirectAddOp<PointN<*, *>>,
	InternalIndirectSubOp<PointN<*, *>>,
	InternalIndirectNegOp<PointN<*, *>>,
	InternalIndirectSignOp<PointN<*, *>>,
	CanBeZeroTrait<PointN<*, *>> {
	@Suppress("UPPER_BOUND_VIOLATED")
	override fun <P : PointN<*, *>> P.plus(other: P): P = this as PointN<P, *> + other

	@Suppress("UPPER_BOUND_VIOLATED")
	override fun <K : PointN<*, *>> K.minus(other: K): K = this as PointN<K, *> - other

	override fun <K : PointN<*, *>> K.unaryMinus(): K = -this as K

	override fun <K : PointN<*, *>> K.sign(): K = this.sign() as K

	override fun PointN<*, *>.isZero(): Boolean = this.isZero()
}
