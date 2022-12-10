package helpers.context

inline fun launch(
	block:
	context(IntOpps, LongOpps, PointNOpps) () -> Unit
) = block(IntOpps, LongOpps, PointNOpps)
