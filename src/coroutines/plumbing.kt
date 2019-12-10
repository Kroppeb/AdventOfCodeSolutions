package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch



infix fun <T> ReceiveChannel<T>.pipeTo(to: SendChannel<T>) {
	GlobalScope.launch {
		for (i in this@pipeTo)
			to.send(i)
		to.close()
	}
}

infix fun <T> ReceiveChannel<T>.pipeToTemp(to: SendChannel<T>) {
	GlobalScope.launch {
		for (i in this@pipeToTemp)
			to.send(i)
	}
}

fun ReceiveChannel<*>.print() {
	GlobalScope.launch { this@print.consumeEach { println(it) } }
}

fun <T> SendChannel<T>.overFlowTo(to: SendChannel<T>) {
	val receiveChannel = Channel<T>()
}

operator fun <T> ReceiveChannel<T>.plus(b: ReceiveChannel<T>) =
		GlobalScope.produce<T> {
			for (i in this@plus)
				send(i)
			for (i in b)
				send(i)
		}

operator fun <T> Iterable<T>.plus(b: ReceiveChannel<T>) =
		GlobalScope.produce<T> {
			for (i in this@plus)
				send(i)
			for (i in b)
				send(i)
		}

operator fun <T> ReceiveChannel<T>.plus(b: Iterable<T>) =
		GlobalScope.produce<T> {
			for (i in this@plus)
				send(i)
			for (i in b)
				send(i)
		}


