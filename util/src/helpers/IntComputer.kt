package helpers

import coroutines.pipeTo
import coroutines.toChannel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

typealias DataType = Long
typealias IntCode = Map<Pointer, DataType>
typealias Data = MutableMap<Pointer, DataType>
typealias OpCode = Int
typealias ParameterMode = Int
typealias Param = Pair<ParameterMode, DataType>
typealias Pointer = DataType

fun Number.toDataType(): DataType = this.toLong()
fun List<Number>.toIntCode(): IntCode = this.withIndex().associate { (i, v) -> i.toPointer() to v.toDataType() }
fun List<Number>.toData(): Data = this.withIndex().associate { (i, v) -> i.toPointer() to v.toDataType() }.toMutableMap()
fun Number.toOpCode(): OpCode = this.toInt()
fun Number.toParameterMode(): ParameterMode = this.toInt()
fun Pair<Number, Number>.toParam(): Param = first.toParameterMode() to second.toDataType()
fun Number.toPointer(): Pointer = this.toDataType()

data class Msg(val x:Long, val y:Long)

class IntComputer(
		private val data: Data,
		private val inputChannel: ReceiveChannel<Msg>,
		scope: CoroutineScope
) : CoroutineScope by scope {
	private var ip: Pointer = 0.toPointer()
	private var sp: Pointer = 0.toPointer()
	private var halted = false

	suspend fun isHalted(): Boolean {
		yield()
		return halted
	}

	var input: SendChannel<Msg>? = null
		private set

	private val outputChannel: Channel<DataType> = Channel(Channel.UNLIMITED)
	val output: ReceiveChannel<DataType> get() = outputChannel

	private val job: Job

	suspend fun join() {
		job.join()
	}

	suspend fun send(item:Msg){
		input!!.send(item)
	}
/*
	suspend fun sendAll(items:Iterable<DataType>){
		items pipeTo input!!
	}

	suspend fun sendAll(items:ReceiveChannel<DataType>): ReceiveChannel<DataType> {
		return items pipeTo input!!
	}
*/
	constructor(
			data: Data,
			input: Channel<Msg> = Channel(Channel.UNLIMITED),
			scope: CoroutineScope) : this(data, input as ReceiveChannel<Msg>, scope){
		this.input = input
	}

	// region setting and getting data

	operator fun get(inp: Pointer): DataType = data.getOrDefault(inp, 0L)

	private operator fun Int.not(): DataType = when (getMode(this)) {
		0 -> get(get(ip + this))
		1 -> get(ip + this)
		2 -> get(get(ip + this) + sp)
		else -> error("n")
	}

	private fun getMode(i: Int): ParameterMode {
		var opd = get(ip).toOpCode() / 100
		repeat(i - 1) { opd /= 10 }
		return opd % 10
	}


	operator fun set(inp: Pointer, value: DataType) {
		data[inp] = value
	}

	private infix fun Long.at(i: Int) = when (getMode(i)) {
		0 -> set(get(ip + i), this)
		1 -> set(ip + i, this)                // yeah I know
		2 -> set(get(ip + i) + sp, this)
		else -> error("n")
	}

	private infix fun Unit.step(i: Int) {
		ip += i
	}

	private infix fun Unit.step(i: Long) {
		ip += i
	}

	// endregion

	fun halt() {
		halted = true
	}

	var prev : Long? = null

	private suspend fun step() {
		when (val opId = this[ip].toOpCode() % 100) {
			// ADD
			1 -> !1 + !2 at 3 step 4
			// MUL
			2 -> !1 * !2 at 3 step 4
			// INP
			3 ->if(prev == null){
				if(inputChannel.isEmpty) {
					idle++
					yield()
				}
				if(inputChannel.isEmpty){
					idle++
					-1
				}else{
					idle = 0
					val msg = inputChannel.poll()?:error("channel shouldn't have been empty")
					prev = msg.y
					msg.x
				}
			} else {
				prev!!.also{prev = null}
			} at 1 step 2
			// OUT
			4 -> outputChannel.send(!1) step 2
			// JNZ
			5 -> {
				if (!1 != 0L) {
					ip = !2
				} else {
					ip += 3
				}
			}
			// JZ
			6 -> {
				if (!1 == 0L) {
					ip = !2
				} else {
					ip += 3
				}
			}
			// LT
			7 -> (if (!1 < !2) 1L else 0L) at 3 step 4
			// EQ
			8 -> (if (!1 == !2) 1L else 0L) at 3 step 4
			9 -> { //AdjustBase
				sp += !1
				ip += 2
			}
			// HLT
			99 -> halt()
			else -> throw IllegalArgumentException("Unknown operation: $opId")
		}.let{}
	}

	var idle = 0

	init {
		this.job = launch(start = CoroutineStart.LAZY) {
			while (isActive && !halted) {
				step()
			}
			onhalts.forEach { it() }
		}
		job.invokeOnCompletion { outputChannel.close(if (it is CancellationException) null else it) }
	}

	private val onhalts = mutableListOf<IntComputer.() -> Unit>()
	fun onComplete(block: IntComputer.() -> Unit){
		onhalts.add(block)
	}
}

fun CoroutineScope.runComputer(data: IntCode) = IntComputer(data.toMutableMap(), scope = this)
fun CoroutineScope.runComputer(data: List<Number>) = runComputer(data.toIntCode())

fun CoroutineScope.runComputer(data: IntCode, input: Channel<Msg>) = IntComputer(data.toMutableMap(), input, scope = this)
fun CoroutineScope.runComputer(data: List<Number>, input: Channel<Msg>) = runComputer(data.toIntCode(), input)

fun CoroutineScope.runComputer(data: IntCode, input: ReceiveChannel<Msg>) = IntComputer(data.toMutableMap(), input, scope = this)
fun CoroutineScope.runComputer(data: List<Number>, input: ReceiveChannel<Msg>) = runComputer(data.toIntCode(), input)

/*
fun CoroutineScope.runComputer(data: IntCode, input: List<Number>) = runComputer(data, input.map(Number::toDataType).toChannel())
fun CoroutineScope.runComputer(data: List<Number>, input: List<Number>) = runComputer(data.toIntCode(), input)
*/