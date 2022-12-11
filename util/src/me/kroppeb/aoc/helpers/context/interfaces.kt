package me.kroppeb.aoc.helpers.context

// add
interface InternalIndirectAddOp<T> {
	operator fun <K : T> K.plus(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalAddOp<T> : InternalIndirectAddOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	operator fun T.plus(other: T): T

	override fun <K : T> K.plus(other: K): K {
		return (this + other) as K
	}
}

// sub
interface InternalIndirectSubOp<T> {
	operator fun <K : T> K.minus(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalSubOp<T> : InternalIndirectSubOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("minusFinal")
	operator fun T.minus(other: T): T

	override fun <K : T> K.minus(other: K): K {
		return (this - other) as K
	}
}

// mul
interface InternalIndirectMulOp<T> {
	operator fun <K : T> K.times(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalMulOp<T> : InternalIndirectMulOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("timesFinal")
	operator fun T.times(other: T): T

	override fun <K : T> K.times(other: K): K {
		return (this * other) as K
	}
}

// div
interface InternalIndirectDivOp<T> {
	operator fun <K : T> K.div(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalDivOp<T> : InternalIndirectDivOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("divFinal")
	operator fun T.div(other: T): T

	override fun <K : T> K.div(other: K): K {
		return (this / other) as K
	}
}

// rem
interface InternalIndirectRemOp<T> {
	operator fun <K : T> K.rem(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalRemOp<T> : InternalIndirectRemOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("remFinal")
	operator fun T.rem(other: T): T

	override fun <K : T> K.rem(other: K): K {
		return (this % other) as K
	}
}

// inc
interface InternalIndirectIncOp<T> {
	fun <K : T> K.inc(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalIncOp<T> : InternalIndirectIncOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("incFinal")
	fun T.inc(): T

	override fun <K : T> K.inc(): K {
		return (this.inc()) as K
	}
}

context (InternalIndirectIncOp<T>)
fun <K : T, T> K.inc(): K = this.inc()


// dec
interface InternalIndirectDecOp<T> {
	fun <K : T> K.dec(): K
}

context (InternalIndirectDecOp<T>)
fun <K : T, T> K.dec(): K = this.dec()

@Suppress("UNCHECKED_CAST")
interface InternalFinalDecOp<T> : InternalIndirectDecOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("decFinal")
	fun T.dec(): T

	override fun <K : T> K.dec(): K {
		return (this.dec()) as K
	}
}

// neg
interface InternalIndirectNegOp<T> {
	operator fun <K : T> K.unaryMinus(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalNegOp<T> : InternalIndirectNegOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("negFinal")
	operator fun T.unaryMinus(): T

	override fun <K : T> K.unaryMinus(): K {
		return (this.unaryMinus()) as K
	}
}

// sign
interface InternalIndirectSignOp<T> {
	fun <K : T> K.sign(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalSignOp<T> : InternalIndirectSignOp<T> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("signFinal")
	fun T.sign(): T

	override fun <K : T> K.sign(): K {
		return (this.sign()) as K
	}
}


interface InternalIndirectMathOp<T> : InternalIndirectAddOp<T>, InternalIndirectSubOp<T>, InternalIndirectMulOp<T>,
	InternalIndirectDivOp<T>, InternalIndirectRemOp<T>,
	InternalIndirectNegOp<T>

interface InternalFinalMathOp<T> : InternalFinalAddOp<T>, InternalFinalSubOp<T>, InternalFinalMulOp<T>,
	InternalFinalDivOp<T>, InternalFinalRemOp<T>,
	InternalFinalNegOp<T>, InternalIndirectMathOp<T>

interface InternalExtendedIndirectMathOp<T> : InternalIndirectMathOp<T>,
	InternalIndirectIncOp<T>, InternalIndirectDecOp<T>,
	InternalIndirectSignOp<T>, CanBeZeroTrait<T>

interface InternalExtendedFinalMathOp<T> : InternalFinalMathOp<T>,
	InternalFinalIncOp<T>, InternalFinalDecOp<T>,
	InternalFinalSignOp<T>, InternalExtendedIndirectMathOp<T>, CanBeZeroTrait<T>


interface CanBeEmptyTrait<T>{
	fun T.isEmpty(): Boolean
	fun T.isNotEmpty(): Boolean = !isEmpty()
}

interface HasSizeTrait<T> {
	fun T.size(): Int
}

interface CanBeZeroTrait<T> {
	fun T.isZero(): Boolean
}