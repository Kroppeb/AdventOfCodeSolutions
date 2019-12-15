package helpers

import kotlin.math.*

fun block(b:()->Unit) = b()

fun hammingDistance(a: Collection<*>, b: Collection<*>): Int =
		a.zip(b) { a1, b1 -> (a1 == b1) }.count { it } + abs(a.size - b.size)

fun editDistance(a: Iterable<*>, b: Iterable<*>): Int {
	val al = (a as? List<*>)?:a.toList()
	val bl = (b as? List<*>)?:b.toList()
	val n = al.size
	val m = bl.size
	val dp = List(n + 1) { arrayOfNulls<Int>(m + 1) }
	dp[n][m] = 0




	fun aux(i: Int, j: Int) :Int {

		dp[i][j]?.let{return it}

		when {
			i == n -> dp[i][j] = 1 + aux(i, j + 1)
			j == m -> dp[i][j] = 1 + aux(i + 1, j)
			else -> {
				dp[i][j] = min((al[i] != bl[j]).toInt() + aux(i + 1, j + 1), 1 + aux(i + 1, j), 1 + aux(i, j + 1))
			}
		}
		return dp[i][j]!!
	}

	return aux(0, 0)
}

private fun Boolean.toInt() = if(this) 1 else 0

fun <C:Comparable<C>> min(first:C, vararg elements:C) = elements.min()?.let{if(it < first) it else first}?:first
fun <C:Comparable<C>> max(first:C, vararg elements:C) = elements.max()?.let{if(it > first) it else first}?:first

fun len(lst: Collection<*>) = lst.size
