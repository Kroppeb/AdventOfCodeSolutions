package me.kroppeb.aoc.helpers.context

object IterableOpps : CanBeEmptyTrait<Iterable<*>>, HasSizeTrait<Iterable<*>> {
	override fun Iterable<*>.isEmpty(): Boolean = !this.iterator().hasNext()
	override fun Iterable<*>.size(): Int = this.count()
}