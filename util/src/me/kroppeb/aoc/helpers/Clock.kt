package me.kroppeb.aoc.helpers


import me.kroppeb.aoc.helpers.point.Point
import me.kroppeb.aoc.helpers.point.PointI
import me.kroppeb.aoc.helpers.point.toP
import me.kroppeb.aoc.helpers.point.toPI

object Clock {
	lateinit var left: Point
		private set;
	lateinit var right: Point
		private set;
	lateinit var up: Point
		private set;
	lateinit var down: Point
		private set;

	val north get() = up
	val east get() = right
	val south get() = down
	val west get() = left


	val leftI get() = left.int
	val rightI get() = right.int
	val upI get() = up.int
	val downI get() = down.int

	// bounds calculation
	var nX: Int = 0
		private set;
	var eX: Int = 0
		private set;
	var nY: Int = 0
		private set;
	var eY: Int = 0
		private set;


	operator fun invoke(x: Int, y: Int) {
		if (x + y mod 6 != 3)
			error("invalid clock")
		when (x) {
			3 -> eX = 1
			6 -> nX = -1
			9 -> eX = -1
			12 -> nX = 1
			else -> error("invalid clock x")
		}
		when (y) {
			3 -> eY = 1
			6 -> nY = -1
			9 -> eY = -1
			12 -> nY = 1
			else -> error("invalid clock y")
		}
		up = nX toP nY
		right = eX toP eY
		left = -right
		down = -up

		this.print()
	}

	fun print() {
		// eg 6,3: (nX = -1, eY = 1
		//      -----
		//     /     \
		//    /       \
		//   |    o->  |
		//    \   |   /
		//     \  v  /
		//      -----
		println("    -----    ")

		if (nX == 1) {
			println("   /  ^  \\   ")
			println("  /   |   \\  ")
		} else {
			println("   /     \\   ")
			if (nY == 1)
				println("  /   ^   \\  ")
			else
				println("  /       \\  ")
		}

		if (eX == 1) {
			println(" |    o--->| ")
		} else if (eX == -1) {
			println(" |<---o    | ")
		} else if (eY == 1) {
			println(" |    o->  | ")
		} else {
			println(" |  <-o    | ")
		}

		if (nX == -1) {
			println("  \\   |   /  ")
			println("   \\  v  /   ")
		} else {
			if (nY == -1)
				println("  \\   v   /  ")
			else
				println("  \\       /  ")
			println("   \\     /   ")
		}

		println("    -----    ")
	}

	fun firstRange(size: Int): IntProgression {
		return if (downI.x + downI.y > 0) {
			0 until size
		} else {
			size - 1 downTo 0
		}
	}

	fun secondRange(size: Int): IntProgression {
		return if (rightI.x + rightI.y > 0) {
			0 until size
		} else {
			size - 1 downTo 0
		}
	}

	fun fromIndices(first: Int, second: Int): PointI {
		return downI * first + rightI * second
	}
}