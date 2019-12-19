package iterators

class RepeatingIterator<out T>(private val source: Iterable<T>) : Iterator<T> {
	private var current: Iterator<T> = source.iterator()

	override fun hasNext(): Boolean =
			if (current.hasNext())
				true
			else {
				current = source.iterator()
				current.hasNext()
			}

	override fun next(): T = current.next()
}

fun <T>Iterable<T>.repeatingIterator() = RepeatingIterator(this)
