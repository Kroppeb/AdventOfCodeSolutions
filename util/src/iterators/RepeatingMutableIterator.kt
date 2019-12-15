package iterators

class RepeatingMutableIterator<out T>(private val source: MutableIterable<T>) : MutableIterator<T> {
	private var current: MutableIterator<T> = source.iterator()
	private var last: MutableIterator<T> = current

	override fun hasNext(): Boolean =
			if (current.hasNext())
				true
			else {
				current = source.iterator()
				current.hasNext()
			}

	override fun next(): T = current.also{last=current}.next()

	override fun remove() = last.remove()
}

fun <T>MutableIterable<T>.repeatingMutableIterator() = RepeatingMutableIterator(this)