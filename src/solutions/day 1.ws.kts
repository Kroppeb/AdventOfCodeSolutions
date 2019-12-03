import helpers.*
import java.lang.Exception

val ints = getInts(1)
ints.map{it/3-2}.sum()

fun getFuel(n:Int): Int {
	val f = n/3-2
	return if (f > 0)
		f + getFuel(f)
	else
		f
}

ints.map(::getFuel).sum()