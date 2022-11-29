@file:Suppress("PackageDirectoryMismatch")

package solutions.y20.d20

import grid.Clock
import helpers.*

private val xxxxx = Clock(6, 3);

private fun part1(data: Data) {
	val tiles = data.splitOn { it.isBlank() }.filter { it.isNotEmpty() }.map {
		val id = it.first().getInt()
		val shape = it.drop(1)
		id to shape
	}.toMap()

	val edges = tiles.flatMap { (id, tile) ->
		listOf(
				tile.first(),
				tile.last(),
				tile.map { it.first() }.joinToString(separator = ""),
				tile.map { it.last() }.joinToString(separator = "")).map {
			val r = it.reversed()
			if (r < it)
				r
			else it
		}.map { it to id }
	}.groupBy { it.first }.filter { it.value.size == 1 }.flatMap { it.value }.map { it.second!!.toLong() }
		.groupBy { it }.filter { it.value.size == 2 }.map { it.value[0] }.toSet().log().reduce { a, b -> a * b }.log()
}


private fun part2(data: Data) {

	val tiles = data.splitOn { it.isBlank() }.filter { it.isNotEmpty() }.map {
		val id = it.first().getInt()!!
		val shape = it.drop(1)
		id to shape
	}.toMap()

	val edges = tiles.flatMap { (id, tile) ->
		listOf(
				tile.first(),
				tile.last(),
				tile.map { it.first() }.joinToString(separator = ""),
				tile.map { it.last() }.joinToString(separator = "")).map {
			val r = it.reversed()
			if (r < it)
				r
			else it
		}.map { it to id }
	}.groupBy { it.first }

	val sds = edges
		.flatMap { it.value.map { it.second to it.first } }
		.groupBy { it.first }
		.mapValues { it.value.map { it.second } }

	val corners = edges.filter { it.value.size == 1 }.flatMap { it.value }.map { it.second!! }
		.groupBy { it }.filter { it.value.size == 2 }.map { it.value[0] }

	val sides = edges.filter { it.value.size == 1 }.flatMap { it.value }.map { it.second!! }
		.groupBy { it }.filter { it.value.size == 1 }.map { it.value[0] }.toSet()

	val size = sides.size / 4 + 2

	val grid = MutableList(size) { MutableList(size) { null as Int? } }



	grid[0][0] = corners[0]
	var cur = corners[0]
	var used = mutableSetOf<Int>(cur)
	for (i in 1 until (size - 1)) {
		cur = sds[cur]!!.flatMap { edges[it]!!.map { it.second } }.first { it in sides && it !in used }
		grid[0][i] = cur
		used.add(cur)
	}
	cur = sds[cur]!!.flatMap { edges[it]!!.map { it.second } }.first { it in corners && it !in used }
	grid[0][size - 1] = cur
	used.add(cur)


	for (x in 1 until (size - 1)) {
		cur = sds[grid[x - 1][0]]!!.flatMap { edges[it]!!.map { it.second } }.first { it in sides && it !in used }
		grid[x][0] = cur
		used.add(cur)
		for (i in 1 until (size - 1)) {
			cur = sds[grid[x - 1][i]]!!.flatMap { edges[it]!!.map { it.second } }.first { it !in sides && it !in used }
			grid[x][i] = cur
			used.add(cur)
		}
		cur = sds[grid[x - 1][size - 1]]!!
			.flatMap { edges[it]!!.map { it.second } }.first { it in sides && it !in used }
		grid[x][size - 1] = cur
		used.add(cur)
	}


	cur = sds[grid[size - 2][0]]!!.flatMap { edges[it]!!.map { it.second } }.first { it in corners && it !in used }
	grid[size - 1][0] = cur
	used.add(cur)
	for (i in 1 until (size - 1)) {
		cur = sds[cur]!!.flatMap { edges[it]!!.map { it.second } }.first { it in sides && it !in used }
		grid[size - 1][i] = cur
		used.add(cur)
	}
	cur = sds[cur]!!.flatMap { edges[it]!!.map { it.second } }.first { it in corners && it !in used }
	grid[size - 1][size - 1] = cur
	used.add(cur)

	grid as List<List<Int>>

	println(grid.map { it.joinToString(postfix = "\n") })

	val rot = MutableList(size) { MutableList(size) { null as List<List<Char>>? } }


	var a = grid[0][0]
	var b = grid[0][1]
	val edg = sds[a]!!.intersect(sds[b]!!).first()

	val ag = tiles[a]!!.e()
	val at = List(ag[0].size){i -> ag.map{it[i]}}

	rot[0][0] = when (edg.e()) {
		ag.map { it.last() } -> ag
		ag.map { it.last() }.reversed() -> ag.reversed()
		ag.map { it.first() } -> ag.map { it.reversed() }
		ag.map { it.first() }.reversed() -> ag.reversed().map { it.reversed() }
		at.map { it.last() } -> at
		at.map { it.last() }.reversed() -> at.reversed()
		at.map { it.first() } -> at.map { it.reversed() }
		at.map { it.first() }.reversed() -> at.reversed().map { it.reversed() }
		else -> error("")
	}

	// add/ remove revesed manually
	if(edges[rot[0][0]!!.last().joinToString(separator = "").reversed()]!!.size == 1){
		rot[0][0] = rot[0][0]!!.reversed()
	}

	for(i in 1 until size){
		val edg = rot[i - 1][0]!!.last()
		val a = grid[i][0]
		val ag = tiles[a]!!.e()
		val at = List(ag[0].size){i -> ag.map{it[i]}}

		//println(edg)

		rot[i][0] = when (edg) {
			ag.first() -> ag
			ag.first().reversed() -> ag.map { it.reversed() }
			ag.last() -> ag.reversed()
			ag.last().reversed() -> ag.reversed().map { it.reversed() }
			at.first() -> at
			at.first().reversed() -> at.map { it.reversed() }
			at.last() -> at.reversed()
			at.last().reversed() -> at.reversed().map { it.reversed() }
			else -> error("")
		}
	}

	for(i in 1 until size){
		for(j in 0 until size) {
			//println("$i,$j")
			val edg = rot[j][i-1]!!.map{it.last()}
			val a = grid[j][i]
			val ag = tiles[a]!!.e()
			val at = List(ag[0].size) { i -> ag.map { it[i] } }

			//println(edg)

			rot[j][i] = when (edg) {
				ag.map { it.first() } -> ag
				ag.map { it.first() }.reversed() -> ag.reversed()
				ag.map { it.last() } -> ag.map { it.reversed() }
				ag.map { it.last() }.reversed() -> ag.reversed().map { it.reversed() }
				at.map { it.first() } -> at
				at.map { it.first() }.reversed() -> at.reversed()
				at.map { it.last() } -> at.map { it.reversed() }
				at.map { it.last() }.reversed() -> at.reversed().map { it.reversed() }
				else -> error("")
			}
		}
	}

	val p = rot.map{l->l.map{it!!.drop(1).dropLast(1).map{it.drop(1).dropLast(1)}}}
	val mg = List(p.size * p[0][0].size){i ->
		val a = i / p[0][0].size
		val b = i % p[0][0].size
		p[a].flatMap { it[b] }
	}

	val mt = List(mg[0].size) { i -> mg.map { it[i] } }


	val monster = listOf(
			"                  # ",
			"#    ##    ##    ###",
			" #  #  #  #  #  #   ")


	val ms = listOf(mg,mt).flatMap { listOf(it, it.reversed()) }.flatMap { listOf(it, it.map{it.reversed()}) }
	val m = ms[4] // gotta do this yourself
	//val m = monster.e()

	val mr = monster.map{it.e().map{
		if(it == ' ') "." else "(#|O)"
	}.joinToString(separator = "")}.joinToString(separator = ".".repeat(m[0].size - monster[0].length)).let{Regex(it)}


	println(m.map { it.joinToString(postfix = "\n",separator = "") })
	var u = m.map { it.joinToString(separator = "")}.joinToString(separator = "")

	println(mr.pattern)

	var uu = u
	do {
		u = uu
		uu = mr.replace(u){
			//println(it.value)
			it.value.mapIndexed { index: Int, c: Char ->

				val line = index / m[0].size
				val i = index % m[0].size
				//println("$line:$i")
				if(i >= monster[0].length)
					c
				else{
					val k = monster[line][i]
					if(k == '#')
						'O'
					else
						c
				}
			}.joinToString(separator = "")
		}

		println("O: ${uu.count{it == 'O'}}")
		println("#: ${uu.count{it == '#'}}")
	} while(uu != u)



	val rrr = Regex("\\(#\\|O\\)")
	for( i in 0..14) {
		var ii = 0
		val mrr =  Regex(rrr.replace(mr.pattern){
			if(ii++ == i){
				"#"
			} else it.value
		})

		do {


			u = uu
			uu = mrr.replace(u) {
				//println(it.value)
				it.value.mapIndexed { index: Int, c: Char ->

					val line = index / m[0].size
					val i = index % m[0].size
					//println("$line:$i")
					if (i >= monster[0].length)
						c
					else {
						val k = monster[line][i]
						if (k == '#')
							'O'
						else
							c
					}
				}.joinToString(separator = "")
			}

			println("O: ${uu.count { it == 'O' }}")
			println("#: ${uu.count { it == '#' }}")
		} while (uu != u)
	}
	//println(uu.chunked(m[0].size).joinToString(separator = "\n"))
	println("O: ${uu.count{it == 'O'}}")
	println("#: ${uu.count{it == '#'}}")
}

private typealias Data = Lines

fun main() {
	val data: Data = getLines(2020_20)
	part1(data)
	part2(data)
}


private fun <T> T.log(): T = also { println(this) }