package me.kroppeb.aoc.helpers

import me.kroppeb.aoc.helpers.sint.*

fun SintRange.intersect(other: SintRange): SintRange {
	val start = maxOf(this.first, other.first)
	val endInclusive = minOf(this.last, other.last)
	return start..endInclusive
}

fun SintRange.fracture(other: SintRange): List<SintRange> {
	if (other.last < first) return listOf(this)
	if (other.first > last) return listOf(this)

	val intersect = intersect(other)

	return if (other.first < first) {
		if (other.last > last) {
			listOf(
				other.first until intersect.first,
				intersect,
				intersect.last + 1.. other.last
			)
		} else {
			listOf(
				other.first until intersect.first,
				intersect
			)
		}
	} else {
		if (other.last > last) {
			listOf(
				intersect,
				intersect.last + 1..last
			)
		} else {
			listOf(intersect) // the regions are equal
		}
	}
}