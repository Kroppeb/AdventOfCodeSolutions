import helpers.*
import kotlin.system.exitProcess

val ints = getInts(2)

// part1
val lints = ints.toMutableList()
lints[1] = 12
lints[2] = 2

loop@ for(i in 0..lints.size step 4){
	when(lints[i]){
		99 -> break@loop
		1 -> lints[lints[i+3]] = lints[lints[i+1]] + lints[lints[i+2]]
		2 -> lints[lints[i+3]] = lints[lints[i+1]] * lints[lints[i+2]]
	}
}
lints[0]



// part2
for(n in 0..99)
	for(v in 0..99){
		val lints = ints.toMutableList()
		lints[1] = n
		lints[2] = v

		loop@ for(i in 0..lints.size step 4){
			when(lints[i]){
				99 -> break@loop
				1 -> lints[lints[i+3]] = lints[lints[i+1]] + lints[lints[i+2]]
				2 -> lints[lints[i+3]] = lints[lints[i+1]] * lints[lints[i+2]]
			}
		}
		if (lints[0] == 19690720)
		{
			println(n*100+v)
			break
		}
	}
