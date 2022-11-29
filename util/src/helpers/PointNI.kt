package helpers

interface PointNI<T : PointNI<T>> : PointN<T, Int> {

	operator fun times(other: Int): T
	operator fun div(other: Int): T
	operator fun rem(other: Int): T


	override fun sqrDist(): Int
	override fun dist(): Double
	override fun manDist(): Int

	override fun sqrDistTo(other: T): Int = (this - other).sqrDist()
	override fun distTo(other: T): Double = (this - other).dist()
	override fun manDistTo(other: T): Int = (this - other).manDist()

	fun gcd(): Int

	fun dot(other: T): Int
}