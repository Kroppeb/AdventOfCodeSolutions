package helpers

class IntComputer(val data: MutableList<Int>) {
	var ip = 0
	var finished = false



	fun run(): Int {
		while(!finished){
			step()
		}
		return !0
	}

	fun step() {
		val op = ops[!ip]!!
		execute(op)
	}

	operator fun set(inp: Int, value: Int) {
		data[inp] = value
	}

	operator fun Int.not() = data[this]

	fun execute(op: Op) {
		op.action(this,data.subList(ip+1,ip+op.size))
		ip+=op.size
	}

	fun halt() {
		finished = true
	}

	companion object{
		operator fun invoke(data:List<Int>) = IntComputer(data.toMutableList())

		val ops: Map<Int, Op> = opBuilder {
			1{ a, b, c -> this[c] = !a + !b }
			2{ a, b, c -> this[c] = !a * !b }
			99{-> halt()}
		}

		private fun opBuilder(builder: OpBuilder.() -> Unit): Map<Int, Op> {
			val o = OpBuilder()
			o.builder()
			return o.ops
		}

		class OpBuilder {
			val ops = mutableMapOf<Int, Op>()

			inline operator fun Int.invoke(crossinline preOp: IntComputer.() -> Unit) {
				ops[this] = Op(1){preOp()}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int) -> Unit) {
				ops[this] = Op(2){(a)->preOp(a)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int) -> Unit) {
				ops[this] = Op(3){(a,b)->preOp(a,b)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int) -> Unit) {
				ops[this] = Op(4){(a,b,c)->preOp(a,b,c)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int, Int) -> Unit) {
				ops[this] = Op(5){(a,b,c,d)->preOp(a,b,c,d)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int, Int, Int) -> Unit) {
				ops[this] = Op(6){(a,b,c,d,e)->preOp(a,b,c,d,e)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int, Int, Int, Int) -> Unit) {
				ops[this] = Op(7){(a,b,c,d,e,f)->preOp(a,b,c,d,e,f)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int, Int, Int, Int, Int) -> Unit) {
				ops[this] = Op(8){(a,b,c,d,e,f,g)->preOp(a,b,c,d,e,f,g)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Int, Int, Int, Int, Int, Int, Int, Int) -> Unit) {
				ops[this] = Op(9){(a,b,c,d,e,f,g,h)->preOp(a,b,c,d,e,f,g,h)}
			}

		}
	}
}



class Op(val size: Int, val action: IntComputer.(List<Int>) -> Unit)







