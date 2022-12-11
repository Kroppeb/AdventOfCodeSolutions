package me.kroppeb.aoc.helpers.collections.list

class ExtendingList<T>() : ArrayList<T?>() {
	override fun set(index: Int, element: T?): T? {
		if (index >= size) {
			repeat(index - size) {
				add(null)
			}
			add(element)
			return null
		} else {
			return super.set(index, element)
		}
	}
}