package me.kroppeb.aoc.helpers.context

object SequenceOpps : CanBeEmptyTrait<Sequence<*>>, HasSizeTrait<Sequence<*>> {
	override fun Sequence<*>.isEmpty(): Boolean = this.iterator().hasNext()
	override fun Sequence<*>.size(): Int = this.count()
}