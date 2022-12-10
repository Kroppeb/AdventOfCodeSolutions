package helpers.context

import helpers.Point
import helpers.PointN
import helpers.PointNI

// add, sub, neg, sign
object PointNOpps :
	InternalIndirectAddOp<PointN<*, *>>,
	InternalIndirectSubOp<PointN<*, *>>,
	InternalIndirectNegOp<PointN<*, *>>,
	InternalIndirectSignOp<PointN<*, *>> {
	@Suppress("UPPER_BOUND_VIOLATED")
	override fun <P : PointN<*, *>> P.plus(other: P): P = this as PointN<P,*> + other

	@Suppress("UPPER_BOUND_VIOLATED")
	override fun <K : PointN<*, *>> K.minus(other: K): K = this as PointN<K,*> - other

	override fun <K : PointN<*, *>> K.unaryMinus(): K = -this as K

	override fun <K : PointN<*, *>> K.sign(): K = this.sign() as K
}
