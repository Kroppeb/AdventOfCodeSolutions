package helpers.context

// add
interface InternalIndirectAddOp<T> {
	operator fun <K : T> K.plus(other: K): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalAddOp<T> : InternalIndirectAddOp<T> {
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
	operator fun T.rem(other: T): T

	override fun <K : T> K.rem(other: K): K {
		return (this % other) as K
	}
}

// inc
interface InternalIndirectIncOp<T> {
	/* operator */ fun <K : T> K.inc(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalIncOp<T> : InternalIndirectIncOp<T> {
	/* operator */ fun T.inc(): T

	override fun <K : T> K.inc(): K {
		return (this.inc()) as K
	}
}


// dec
interface InternalIndirectDecOp<T> {
	/* operator */ fun <K : T> K.dec(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalDecOp<T> : InternalIndirectDecOp<T> {
	/* operator */ fun T.dec(): T

	override fun <K : T> K.dec(): K {
		return (this.dec()) as K
	}
}

// neg
interface InternalIndirectNegOp<T> {
	fun <K : T> K.unaryMinus(): K
}

@Suppress("UNCHECKED_CAST")
interface InternalFinalNegOp<T> : InternalIndirectNegOp<T> {
	fun T.unaryMinus(): T

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
	InternalFinalNegOp<T>

interface InternalExtendedIndirectMathOp<T> : InternalIndirectMathOp<T>,
	InternalIndirectIncOp<T>, InternalIndirectDecOp<T>,
	InternalIndirectSignOp<T>

interface InternalExtendedFinalMathOp<T> : InternalFinalMathOp<T>,
	InternalFinalIncOp<T>, InternalFinalDecOp<T>,
	InternalFinalSignOp<T>

