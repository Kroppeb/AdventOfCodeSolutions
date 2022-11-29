package helpers

interface PointN<T : PointN<T, C>, C:Comparable<C>> {

	fun getVonNeumannNeighbours(): List<T>
	fun getMooreNeighbours(): List<T>

	operator fun unaryMinus(): T

	operator fun minus(other: T): T
	operator fun plus(other: T): T

	operator fun times(other: T): T
	operator fun div(other: T): T
	operator fun rem(other: T): T

	fun abs(): T

	fun discreteAngle(): T
	fun min(other: T): T
	fun max(other: T): T

	fun sqrDist(): C
	fun dist(): Double
	fun manDist(): C

	fun sqrDistTo(other: T): C = (this - other).sqrDist()
	fun distTo(other: T): Double = (this - other).dist()
	fun manDistTo(other: T): C = (this - other).manDist()
}