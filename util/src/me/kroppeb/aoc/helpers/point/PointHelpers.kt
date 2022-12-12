package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock.down
import me.kroppeb.aoc.helpers.Clock.left
import me.kroppeb.aoc.helpers.Clock.right
import me.kroppeb.aoc.helpers.Clock.up
import me.kroppeb.aoc.helpers.sint.Sint

infix fun Int.toP(y: Int): Point = Point(this, y)
infix fun Point.toP(z: Int): Point3D = Point3D(x, y, z)

// TODO: make Point use sint instead
infix fun Int.toP(y: Sint): Point = this toP y.i
infix fun Sint.toP(y: Int): Point = this.i toP y
infix fun Sint.toP(y: Sint): Point = this.i toP y.i
infix fun Point.toP(y: Sint): Point3D = this toP y.i



fun Char.toPoint(): Point = when (this.toUpperCase()) {
	'E' -> right
	'R' -> right

	'S' -> down
	'D' -> down

	'W' -> left
	'L' -> left

	'N' -> up
	'U' -> up
	else -> error("")
}


infix fun Long.toP(o: Long) = PointL(this, o)
infix fun PointL.toP (o: Long) = Point3DL(this.x, this.y, o)

fun <T : PointN<T, *>> abs(v: T) = v.abs()


fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosest(): T? = this.minByOrNull(PointN<T,C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestMan(): T? = this.minByOrNull(PointN<T,C>::manDist)

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestTo(other: T): T? = this.minByOrNull { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestManTo(other: T): T? = this.minByOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthest(): T? = this.maxByOrNull(PointN<T,C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestMan(): T? = this.maxByOrNull(PointN<T,C>::manDist)

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestTo(other: T): T? = this.maxByOrNull { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestManTo(other: T): T? = this.maxByOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestSqrDist(): C? = this.minOfOrNull(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestDist(): Double? = this.minOfOrNull(PointN<T, C>::dist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestManDist(): C? = this.minOfOrNull(PointN<T, C>::manDist)

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestSqrDistTo(other: T): C? =
	this.minOfOrNull { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestDistTo(other: T): Double? =
	this.minOfOrNull { it.distTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestManDistTo(other: T): C? =
	this.minOfOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestSqrDist(): C? = this.maxOfOrNull(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestDist(): Double? = this.maxOfOrNull(PointN<T, C>::dist)
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestManDist(): C? = this.maxOfOrNull(PointN<T, C>::manDist)

fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestSqrDistTo(other: T): C? =
	this.maxOfOrNull { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestDistTo(other: T): Double? =
	this.maxOfOrNull { it.distTo(other) }
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.getFurthestManDistTo(other: T): C? =
	this.maxOfOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByClosestTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByClosestManTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByFurthestTo(other: T) = sortedByDescending { it.manDistTo(other) }
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByFurthestManTo(other: T) = sortedByDescending { it.manDistTo(other) }

fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByClosest() = sortedBy(PointN<T,C>::sqrDist)
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByClosestMan() = sortedBy(PointN<T,C>::manDist)
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByFurthest() = sortedByDescending(PointN<T,C>::sqrDist)
fun <T : PointN<T, C>, C:Comparable<C>> Iterable<T>.sortByFurthestMan() = sortedByDescending(PointN<T,C>::manDist)

object PointOrdering {
	object XMayor : Comparator<Point> {
		override fun compare(o1: Point, o2: Point): Int {
			if (o1.x == o2.x)
				return o1.y.compareTo(o2.y)
			return o1.x.compareTo(o2.x)
		}
	}

	object YMayor : Comparator<Point> {
		override fun compare(o1: Point, o2: Point): Int {
			if (o1.y == o2.y)
				return o1.x.compareTo(o2.x)
			return o1.y.compareTo(o2.y)
		}
	}
}


fun PointL.toPoint() = this.x.toInt() toP this.y.toInt()
fun Point.toPointL() = this.x.toLong() toP this.y.toLong()
fun Point3DL.toPoint3D() = this.x.toInt() toP this.y.toInt() toP this.z.toInt()
fun Point3D.toPoint3DL() = this.x.toLong() toP this.y.toLong() toP this.z.toLong()

operator fun Point.plus(other: PointL) = this.toPointL() + other
operator fun Point.minus(other: PointL) = this.toPointL() - other
operator fun Point.times(other: PointL) = this.toPointL() * other
operator fun Point.div(other: PointL) = this.toPointL() / other
operator fun Point.rem(other: PointL) = this.toPointL() % other
fun Point.dot(other: PointL) = this.toPointL().dot(other)

operator fun PointL.plus(other: Point) = this + other.toPointL()
operator fun PointL.minus(other: Point) = this - other.toPointL()
operator fun PointL.times(other: Point) = this * other.toPointL()
operator fun PointL.div(other: Point) = this / other.toPointL()
operator fun PointL.rem(other: Point) = this % other.toPointL()
fun PointL.dot(other: Point) = this.dot(other.toPointL())

fun Pair<Int, Int>.toPoint() = first toP second
fun Pair<Long, Long>.toPointL() = first toP second
