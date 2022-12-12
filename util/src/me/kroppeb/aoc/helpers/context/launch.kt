package me.kroppeb.aoc.helpers.context

inline fun start(
	block:
	context(IntOpps, LongOpps, PointNOpps, StringOpps, IterableOpps, SequenceOpps, SintOpps) () -> Unit
) = block(IntOpps, LongOpps, PointNOpps, StringOpps, IterableOpps, SequenceOpps, SintOpps)
