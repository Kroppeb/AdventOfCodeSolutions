package helpers

import java.math.BigInteger

class BigIntegerComputer(val data: MutableMap<BigInteger, BigInteger>, val inputs: Iterator<BigInteger>) {
	var ip = BigInteger.ZERO
	var finished = false


	fun run(): BigInteger {
		y = false
		while (!finished && !y) {
			step()
		}
		return this[BigInteger.ZERO]
	}

	fun step() {
		val i = this[ip].toInt()
		val opd = i % 100
		val op = ops[opd]!!
		// original
		//val modes = (1..op.size).scan(i / 10) { old, new -> old / 10 }.map { it % 10 }

		/*
		suggestions
		val modes = (i / 100).toString().map{"$it".toBigInteger()} + listOf(0,0,0,0,0,0,0)
		or
		val modes = (i / 100).toString().map{"$it".toBigInteger()}
		*/

		val modes = generateStateTimes(op.size, i.toInt() / 100) {
			it / 10 to it % 10
		}
		execute(op, modes)
	}

	operator fun get(inp: BigInteger): BigInteger {
		return data[inp]!!
	}

	operator fun get(inp: Param): BigInteger = when (inp.first) {
		0 -> data.getOrDefault(inp.second, BigInteger.ZERO) // enen though I made this a default dict ?
		1 -> inp.second
		2 -> data.getOrDefault(inp.second + rb, BigInteger.ZERO)
		else -> error("n")
	}

	operator fun set(inp: BigInteger, value: BigInteger) {
		data[inp] = value
	}

	operator fun set(inp: Param, value: BigInteger) {
		data[+inp] = value
	}

	operator fun Param.not() = this@BigIntegerComputer[this]

	operator fun Param.unaryPlus() = when(first){
		0 -> second
		2 -> second+rb
		else -> error("Fuck you")
	}

	fun execute(op: Op, modes: List<Int>) {
		val oldIp = ip
		ip += op.size.toBigInteger()
		op.action(this, modes.zip((1 until op.size).map {
			data[oldIp + it.toBigInteger()]!!
		}))
		if (y) {
			ip = oldIp
		}
	}

	fun halt() {
		finished = true
	}

	var y = false

	fun yield() {
		y = true
	}

	var last: BigInteger = BigInteger.ZERO

	var rb: BigInteger = BigInteger.ZERO

	companion object {
		operator fun invoke(data: List<BigInteger>, inputs: Iterable<BigInteger>? = null) =
				BigIntegerComputer(
						data.withIndex().associate { (i, v)-> i.toBigInteger() to v }.toMutableMap().withDefault { BigInteger.ZERO },
						inputs?.iterator()
								?: System.`in`.bufferedReader().lineSequence().map { it.toBigInteger() }.iterator()
				)

		val ops: Map<Int, Op> = opBuilder {
			1{ a, b, c -> this[c] = !a + !b }
			2{ a, b, c -> this[c] = !a * !b }
			3{ a ->
				inputs.getNext().let {
					this[a] = it
				}
			}
			4{ a -> last = !a;println(!a) }
			5{ a, b -> if (!a != BigInteger.ZERO) ip = !b }
			6{ a, b -> if (!a == BigInteger.ZERO) ip = !b }
			7{ a, b, c -> this[c] = if (!a < !b) BigInteger.ONE else BigInteger.ZERO }
			8{ a, b, c -> this[c] = if (!a == !b) BigInteger.ONE else BigInteger.ZERO }
			9{ a -> rb += !a }
			99{ -> halt() }
		}

		private fun opBuilder(builder: OpBuilder.() -> Unit): Map<Int, Op> {
			val o = OpBuilder()
			o.builder()
			return o.ops
		}

		class OpBuilder {
			val ops = mutableMapOf<Int, Op>()

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.() -> Unit) {
				ops[this] = Op(1) { preOp() }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param) -> Unit) {
				ops[this] = Op(2) { (a) -> preOp(a) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param) -> Unit) {
				ops[this] = Op(3) { (a, b) -> preOp(a, b) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param) -> Unit) {
				ops[this] = Op(4) { (a, b, c) -> preOp(a, b, c) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param, Param) -> Unit) {
				ops[this] = Op(5) { (a, b, c, d) -> preOp(a, b, c, d) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param, Param, Param) -> Unit) {
				ops[this] = Op(6) { (a, b, c, d, e) -> preOp(a, b, c, d, e) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param, Param, Param, Param) -> Unit) {
				ops[this] = Op(7) { (a, b, c, d, e, f) -> preOp(a, b, c, d, e, f) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param, Param, Param, Param, Param) -> Unit) {
				ops[this] = Op(8) { (a, b, c, d, e, f, g) -> preOp(a, b, c, d, e, f, g) }
			}

			inline operator fun Int.invoke(crossinline preOp: BigIntegerComputer.(Param, Param, Param, Param, Param, Param, Param, Param) -> Unit) {
				ops[this] = Op(9) { (a, b, c, d, e, f, g, h) -> preOp(a, b, c, d, e, f, g, h) }
			}

		}
	}
}

typealias Param = Pair<Int, BigInteger>

class Op(val size: Int, val action: BigIntegerComputer.(List<Param>) -> Unit)







