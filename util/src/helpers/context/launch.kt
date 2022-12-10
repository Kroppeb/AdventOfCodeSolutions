package helpers.context

inline fun launch(
	block:
	context(IntOpps, LongOpps, PointNOpps, StringOpps, IterableOpps, SequenceOpps) () -> Unit
) = block(IntOpps, LongOpps, PointNOpps, StringOpps, IterableOpps, SequenceOpps)
