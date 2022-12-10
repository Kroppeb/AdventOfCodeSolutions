package helpers.context

@Suppress("INCONSISTENT_TYPE_PARAMETER_VALUES")
object IterableOpps : Kind1<IterableOpps, Iterable<*>>, CanBeEmptyTrait<Iterable<*>>, HasSizeTrait<Iterable<*>> {
	override fun Iterable<*>.isEmpty(): Boolean = this.iterator().hasNext()
	override fun Iterable<*>.size(): Int = this.count()
}