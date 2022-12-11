package me.kroppeb.aoc.helpers.context

object StringOpps : InternalFinalAddOp<String>, CanBeEmptyTrait<String> {
	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("plusFinal")
	override operator fun String.plus(other: String): String = this + other

	override fun String.isEmpty(): Boolean = (this as CharSequence).isEmpty()
}