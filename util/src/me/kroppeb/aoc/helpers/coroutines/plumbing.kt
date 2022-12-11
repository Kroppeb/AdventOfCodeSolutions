package me.kroppeb.aoc.helpers.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*

suspend infix fun <T> ReceiveChannel<T>.pipeTo(to: SendChannel<T>): ReceiveChannel<T> {
	val overflow = Channel<T>(Channel.UNLIMITED)
	for (i in this) {
		try {
			to.send(i)
		}catch (e: ClosedSendChannelException){
			overflow.send(i)
			for(j in this)
				overflow.send(j)
		}
	}
	return overflow
}

suspend infix fun <T> Iterable<T>.pipeTo(to: SendChannel<T>) {
	for (i in this) to.send(i)
}

suspend fun ReceiveChannel<*>.print() {
	this@print.consumeEach { println(it) }
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

fun <T>Iterable<T>.toChannel():ReceiveChannel<T>{
	val channel = Channel<T>(Channel.UNLIMITED)
	for (i in this)
		channel.offer(i)
	return channel
}


