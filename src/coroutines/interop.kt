package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch



fun <T> Flow<T>.toSequence() = map{}

fun <T> Iterable<T>.toChannel(): ReceiveChannel<T> = GlobalScope.produce {
	for (i in this@toChannel)
		send(i)
}

