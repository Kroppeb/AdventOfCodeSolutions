package helpers

interface PointNL<T : PointNL<T>> : PointN<T, Long> {
	operator fun times(other: Long): T
	operator fun div(other: Long): T
	operator fun rem(other: Long): T

	override fun sqrDist(): Long
	override fun dist(): Double
	override fun manDist(): Long

	override fun sqrDistTo(other: T): Long = (this - other).sqrDist()
	override fun distTo(other: T): Double = (this - other).dist()
	override fun manDistTo(other: T): Long = (this - other).manDist()

	fun gcd(): Long

	fun dot(other: T): Long
}