package me.kroppeb.aoc.helpers.point

import me.kroppeb.aoc.helpers.Clock
import me.kroppeb.aoc.helpers.sint.Sint
import me.kroppeb.aoc.helpers.sint.s

infix fun Int.toPI(y: Int): PointI = PointI(this, y)
infix fun PointI.toPI(z: Int): Point3DI = Point3DI(x, y, z)


infix fun Long.toPL(o: Long) = PointL(this, o)
infix fun PointL.toPL(o: Long) = Point3DL(this.x, this.y, o)

infix fun Sint.toP(y: Sint): Point = Point(this, y)
infix fun Sint.toP(y: Int): Point = Point(this, y.s)
infix fun Int.toP(y: Sint): Point = Point(this.s, y)
infix fun Int.toP(y: Int): Point = Point(this.s, y.s)
infix fun Sint.toP(y: Long): Point = Point(this, y.s)
infix fun Long.toP(y: Sint): Point = Point(this.s, y)
infix fun Long.toP(y: Int): Point = Point(this.s, y.s)
infix fun Int.toP(y: Long): Point = Point(this.s, y.s)
infix fun Long.toP(y: Long): Point = Point(this.s, y.s)

infix fun Point.toP(z: Sint): Point3D = Point3D(x, y, z)
infix fun Point.toP(z: Int): Point3D = Point3D(x, y, z.s)
infix fun Point.toP(z: Long): Point3D = Point3D(x, y, z.s)

infix fun PointI.toP(z: Sint): Point3D = Point3D(x.s, y.s, z)
infix fun PointI.toP(z: Int): Point3D = Point3D(x.s, y.s, z.s)
infix fun PointI.toP(z: Long): Point3D = Point3D(x.s, y.s, z.s)

infix fun PointL.toP(z: Sint): Point3D = Point3D(x.s, y.s, z)
infix fun PointL.toP(z: Int): Point3D = Point3D(x.s, y.s, z.s)
infix fun PointL.toP(z: Long): Point3D = Point3D(x.s, y.s, z.s)

fun Char.toPoint(): Point = when (this.toUpperCase()) {
	'E' -> Clock.right
	'R' -> Clock.right

	'S' -> Clock.down
	'D' -> Clock.down

	'W' -> Clock.left
	'L' -> Clock.left

	'N' -> Clock.up
	'U' -> Clock.up
	else -> error("")
}

fun Char.toPointI() = toPoint().int

fun <T : PointN<T, *>> abs(v: T) = v.abs()


fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosest(): T? = this.minByOrNull(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestMan(): T? = this.minByOrNull(PointN<T, C>::manDist)

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestTo(other: T): T? = this.minByOrNull { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getClosestManTo(other: T): T? = this.minByOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthest(): T? = this.maxByOrNull(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestMan(): T? = this.maxByOrNull(PointN<T, C>::manDist)

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

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestSqrDist(): C? = this.maxOfOrNull(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestDist(): Double? = this.maxOfOrNull(PointN<T, C>::dist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestManDist(): C? = this.maxOfOrNull(PointN<T, C>::manDist)

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestSqrDistTo(other: T): C? =
	this.maxOfOrNull { it.sqrDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestDistTo(other: T): Double? =
	this.maxOfOrNull { it.distTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.getFurthestManDistTo(other: T): C? =
	this.maxOfOrNull { it.manDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByClosestTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByClosestManTo(other: T) = sortedBy { it.sqrDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByFurthestTo(other: T) = sortedByDescending { it.manDistTo(other) }
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByFurthestManTo(other: T) = sortedByDescending { it.manDistTo(other) }

fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByClosest() = sortedBy(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByClosestMan() = sortedBy(PointN<T, C>::manDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByFurthest() = sortedByDescending(PointN<T, C>::sqrDist)
fun <T : PointN<T, C>, C : Comparable<C>> Iterable<T>.sortByFurthestMan() = sortedByDescending(PointN<T, C>::manDist)

object PointOrdering {
	object XMayor : Comparator<PointI> {
		override fun compare(o1: PointI, o2: PointI): Int {
			if (o1.x == o2.x)
				return o1.y.compareTo(o2.y)
			return o1.x.compareTo(o2.x)
		}
	}

	object YMayor : Comparator<PointI> {
		override fun compare(o1: PointI, o2: PointI): Int {
			if (o1.y == o2.y)
				return o1.x.compareTo(o2.x)
			return o1.y.compareTo(o2.y)
		}
	}
}


fun PointL.toPoint() = this.x.toInt() toPI this.y.toInt()
fun PointI.toPointL() = this.x.toLong() toPL this.y.toLong()
fun Point3DL.toPoint3D() = this.x.toInt() toPI this.y.toInt() toPI this.z.toInt()
fun Point3DI.toPoint3DL() = this.x.toLong() toPL this.y.toLong() toPL this.z.toLong()

operator fun PointI.plus(other: PointL) = this.toPointL() + other
operator fun PointI.minus(other: PointL) = this.toPointL() - other
operator fun PointI.times(other: PointL) = this.toPointL() * other
operator fun PointI.div(other: PointL) = this.toPointL() / other
operator fun PointI.rem(other: PointL) = this.toPointL() % other
fun PointI.dot(other: PointL) = this.toPointL().dot(other)

operator fun PointL.plus(other: PointI) = this + other.toPointL()
operator fun PointL.minus(other: PointI) = this - other.toPointL()
operator fun PointL.times(other: PointI) = this * other.toPointL()
operator fun PointL.div(other: PointI) = this / other.toPointL()
operator fun PointL.rem(other: PointI) = this % other.toPointL()
fun PointL.dot(other: PointI) = this.dot(other.toPointL())

fun Pair<Int, Int>.toPoint() = first toPI second
fun Pair<Long, Long>.toPointL() = first toPL second


val PointI.sint get() = this.x.s toP this.y.s
fun PointI.dot(other: Point) = this.sint.dot(other)
operator fun Point.plus(other: PointI) = this.plus(other.sint)
operator fun PointI.plus(other: Point) = this.sint.plus(other)
operator fun Point.minus(other: PointI) = this.minus(other.sint)
operator fun PointI.minus(other: Point) = this.sint.minus(other)
operator fun Point.times(other: PointI) = this.times(other.sint)
operator fun PointI.times(other: Point) = this.sint.times(other)
operator fun Point.div(other: PointI) = this.div(other.sint)
operator fun PointI.div(other: Point) = this.sint.div(other)
operator fun Point.rem(other: PointI) = this.rem(other.sint)
operator fun PointI.rem(other: Point) = this.sint.rem(other)

operator fun PointI.times(other: Sint) = this.sint.times(other)
operator fun PointI.div(other: Sint) = this.sint.div(other)
operator fun PointI.rem(other: Sint) = this.sint.rem(other)

val Point3DI.sint get() = this.x.s toP this.y.s toP this.z.s


val PointL.sint get() = this.x.s toP this.y.s
fun PointL.dot(other: Point) = this.sint.dot(other)
operator fun Point.plus(other: PointL) = this.plus(other.sint)
operator fun PointL.plus(other: Point) = this.sint.plus(other)
operator fun Point.minus(other: PointL) = this.minus(other.sint)
operator fun PointL.minus(other: Point) = this.sint.minus(other)
operator fun Point.times(other: PointL) = this.times(other.sint)
operator fun PointL.times(other: Point) = this.sint.times(other)
operator fun Point.div(other: PointL) = this.div(other.sint)
operator fun PointL.div(other: Point) = this.sint.div(other)
operator fun Point.rem(other: PointL) = this.rem(other.sint)
operator fun PointL.rem(other: Point) = this.sint.rem(other)

operator fun PointL.times(other: Sint) = this.sint.times(other)
operator fun PointL.div(other: Sint) = this.sint.div(other)
operator fun PointL.rem(other: Sint) = this.sint.rem(other)

val Point3DL.sint get() = this.x.s toP this.y.s toP this.z.s