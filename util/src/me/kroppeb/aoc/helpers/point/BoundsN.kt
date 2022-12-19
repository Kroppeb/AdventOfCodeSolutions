package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.context.InternalExtendedIndirectMathOp

interface BoundsN<B:BoundsN<B,P,C>, P:PointN<P,C>, C:Comparable<C>> : Collection<P> {
	val lower: P
	val higher: P
	fun intersect(other: B): B
	fun size(): P
	fun weight(): C

	fun doesIntersect(other: B): Boolean {
		return !intersect(other).isEmpty()
	}

	fun merge(other: B): B

	operator fun contains(other: B): Boolean {
		return other.isEmpty() || (other.lower in this && other.higher in this)
	}

	/**
	 * Fractures the bounds into a set of bounds that are not overlapping and each bound is either fully contained
	 * in these bounds or fully outside of these bounds. The fractures together cover the same area as these bounds.
	 */
	fun fracture(other: B): Collection<B>
}