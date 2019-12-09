import helpers.*

val ints = getInts(2)

// part1
val comp = IntComputer(ints)
comp[1] = 12
comp[2] = 2
println(comp.runBlocked())

// part2
for (n in 0..99)
	for (v in 0..99) {
		val comp = IntComputer(ints)
		comp[1] = n
		comp[2] = v
		comp.runBlocked()

		if (comp[0] == 19690720) {
			println(n * 100 + v)

		}
	}
