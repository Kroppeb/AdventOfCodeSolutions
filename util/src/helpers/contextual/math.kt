package helpers.contextual

import helpers.context.*
import helpers.max

context(CanBeZeroTrait<T>, InternalIndirectRemOp<T>)
infix fun <T, K:T> K.divBy(other: K): Boolean = (this % other).isZero()

context(InternalIndirectNegOp<T>)
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, K> K.abs() : K where K : Comparable<K>, K : T = max(this, -this)
// fun <T, K> K.abs() : K where K : T, K : Comparable<K> = max(this, -this)

context(InternalIndirectIncOp<T>)
operator fun <T, K:T> K.inc(): K = this.inc()

context(InternalIndirectDecOp<T>)
operator fun <T, K:T> K.dec(): K = this.dec()