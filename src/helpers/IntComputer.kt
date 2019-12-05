package helpers

class IntComputer(val data: MutableList<Int>, val inputs:Iterator<Int>) {
	var ip = 0
	var finished = false



	fun run(): Int {
		while(!finished){
			step()
		}
		return data[0]
	}

	fun step() {
		val i = data[ip]
		val opd = i % 100
		val op = ops[opd]!!
		val modes = (1..op.size).scan(i/10){old, new -> old/10}.map{it%10}

		execute(op, modes)
	}

	operator fun set(inp: Int, value: Int) {
		data[inp] = value
	}

	operator fun Pair<Int,Int>.not() = when(this.first){
		0 -> data[this.second]
		1 -> this.second
		else -> error("n")
	}


	fun execute(op: Op, modes:List<Int>) {
		val oldIp =ip
		ip+=op.size
		op.action(this,modes.zip(data.subList(oldIp+1,oldIp+op.size)))
	}

	fun halt() {
		finished = true
	}

	companion object{
		operator fun invoke(data:List<Int>, inputs: Iterable<Int>) = IntComputer(data.toMutableList(), inputs.iterator())

		val ops: Map<Int, Op> = opBuilder {
			1{ a, b, c -> this[c.second] = !a + !b }
			2{ a, b, c -> this[c.second] = !a * !b }
			3{ a -> this[a.second] = inputs.also{it.hasNext()}.next() }
			4{ a -> println(!a) }
			5{ a, b -> if(!a != 0) ip = !b}
			6{ a, b -> if(!a == 0) ip = !b}
			7{ a, b ,c-> this[c.second] =  if(!a < !b) 1 else 0}
			8{ a, b ,c-> this[c.second] =  if(!a == !b) 1 else 0}
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
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Pair<Int,Int>) -> Unit) {
				ops[this] = Op(2){(a)->preOp(a)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Pair<Int,Int>, Pair<Int,Int>) -> Unit) {
				ops[this] = Op(3){(a,b)->preOp(a,b)}
			}
			inline operator fun Int.invoke(crossinline preOp: IntComputer.(Pair<Int,Int>, Pair<Int,Int>, Pair<Int,Int>) -> Unit) {
				ops[this] = Op(4){(a,b,c)->preOp(a,b,c)}
			}
			/*
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
			}*/

		}
	}
}



class Op(val size: Int, val action: IntComputer.(List<Pair<Int,Int>>) -> Unit)







