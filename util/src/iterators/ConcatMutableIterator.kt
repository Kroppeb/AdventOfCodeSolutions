package iterators

import java.util.*

class ConcatMutableIterator<T>(iterator: MutableIterator<T>) : MutableIterator<T> {
	private val store = ArrayDeque<MutableIterator<T>>()
	private var last: MutableIterator<T>

	init {
		if (iterator.hasNext())
			store.add(iterator)
		last = iterator
	}

	override fun hasNext(): Boolean = when {
		store.isEmpty() -> false
		else -> store.first.hasNext()
	}

	override fun next(): T {
		last = store.first
		val t = last.next()

		if (!last.hasNext())
			store.removeFirst()

		return t
	}

	operator fun plus(iterator: MutableIterator<T>): ConcatMutableIterator<T> {
		if (iterator.hasNext())
			store.add(iterator)
		return this
	}

	override fun remove() {
		last.remove()
	}
}


operator fun <T> MutableIterator<T>.plus(iterator: MutableIterator<T>): ConcatMutableIterator<T>
		= ConcatMutableIterator(this) + iterator