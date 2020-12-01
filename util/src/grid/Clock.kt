package grid

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
	}
}