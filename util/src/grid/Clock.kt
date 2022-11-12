package grid

import grid.Clock.down
import grid.Clock.right
import helpers.Point
import helpers.toP

object Clock {
	lateinit var left: Point
		private set;
	lateinit var right: Point
		private set;
	lateinit var up: Point
		private set;
	lateinit var down: Point
		private set;

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
		if ((x + y) % 6 != 3)
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
		return if (down.x + down.y > 0) {
			0 until size
		} else {
			size - 1 downTo 0
		}
	}

	fun secondRange(size: Int): IntProgression {
		return if (right.x + right.y > 0) {
			0 until size
		} else {
			size - 1 downTo 0
		}
	}

	fun fromIndices(first: Int, second: Int): Point {
		return down * first + right * second
	}
}