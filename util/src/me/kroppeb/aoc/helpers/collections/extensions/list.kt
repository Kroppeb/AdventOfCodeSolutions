package me.kroppeb.aoc.helpers.collections.extensions

import me.kroppeb.aoc.helpers.sint.*


fun <E> List<E>.repeat(length: Int): List<E> {
	if (isEmpty() || length == 0) return emptyList()
	val list = ArrayList<E>(size * length)
	repeat(length) {
		list.addAll(this)
	}
	return list
}

fun <E> List<E>.repeat(length: Sint): List<E> {
	if (isEmpty() || length == 0.s) return emptyList()
	val list = ArrayList<E>((size * length).i)
	repeat(length) {
		list.addAll(this)
	}
	return list
}


//region list operators
operator fun <T> List<T>.get(indexes: IntRange): List<T> = subList(indexes.first, indexes.last + 1)
operator fun <T> List<T>.get(indexes: SintRange): List<T> = subList(indexes.first.i, (indexes.last + 1).i)

operator fun <T> List<T>.get(indexes: IntProgression): List<T> = indexes.map { this[it] }
operator fun <T> List<T>.get(indexes: SintProgression): List<T> = indexes.map { this[it] }


operator fun <E> List<E>.times(count: Int) = repeat(count)
operator fun <E> List<E>.times(count: Sint) = repeat(count)

//region list components

operator fun <E> List<E>.component6(): E = this[5]
operator fun <E> List<E>.component7(): E = this[6]
operator fun <E> List<E>.component8(): E = this[7]
operator fun <E> List<E>.component9(): E = this[8]
operator fun <E> List<E>.component10(): E = this[9]
operator fun <E> List<E>.component11(): E = this[10]
operator fun <E> List<E>.component12(): E = this[11]
operator fun <E> List<E>.component13(): E = this[12]
operator fun <E> List<E>.component14(): E = this[13]
operator fun <E> List<E>.component15(): E = this[14]
operator fun <E> List<E>.component16(): E = this[15]
operator fun <E> List<E>.component17(): E = this[16]
operator fun <E> List<E>.component18(): E = this[17]
operator fun <E> List<E>.component19(): E = this[18]
operator fun <E> List<E>.component20(): E = this[19]
operator fun <E> List<E>.component21(): E = this[20]
operator fun <E> List<E>.component22(): E = this[21]
operator fun <E> List<E>.component23(): E = this[22]
operator fun <E> List<E>.component24(): E = this[23]
operator fun <E> List<E>.component25(): E = this[24]
operator fun <E> List<E>.component26(): E = this[25]
operator fun <E> List<E>.component27(): E = this[26]
operator fun <E> List<E>.component28(): E = this[27]
operator fun <E> List<E>.component29(): E = this[28]
operator fun <E> List<E>.component30(): E = this[29]
operator fun <E> List<E>.component31(): E = this[30]
operator fun <E> List<E>.component32(): E = this[31]
operator fun <E> List<E>.component33(): E = this[32]
operator fun <E> List<E>.component34(): E = this[33]
operator fun <E> List<E>.component35(): E = this[34]
operator fun <E> List<E>.component36(): E = this[35]
operator fun <E> List<E>.component37(): E = this[36]
operator fun <E> List<E>.component38(): E = this[37]
operator fun <E> List<E>.component39(): E = this[38]
operator fun <E> List<E>.component40(): E = this[39]
operator fun <E> List<E>.component41(): E = this[40]
operator fun <E> List<E>.component42(): E = this[41]
operator fun <E> List<E>.component43(): E = this[42]
operator fun <E> List<E>.component44(): E = this[43]
operator fun <E> List<E>.component45(): E = this[44]
operator fun <E> List<E>.component46(): E = this[45]
operator fun <E> List<E>.component47(): E = this[46]
operator fun <E> List<E>.component48(): E = this[47]
operator fun <E> List<E>.component49(): E = this[48]
operator fun <E> List<E>.component50(): E = this[49]
operator fun <E> List<E>.component51(): E = this[50]
operator fun <E> List<E>.component52(): E = this[51]
operator fun <E> List<E>.component53(): E = this[52]
operator fun <E> List<E>.component54(): E = this[53]
operator fun <E> List<E>.component55(): E = this[54]
operator fun <E> List<E>.component56(): E = this[55]
operator fun <E> List<E>.component57(): E = this[56]
operator fun <E> List<E>.component58(): E = this[57]
operator fun <E> List<E>.component59(): E = this[58]
operator fun <E> List<E>.component60(): E = this[59]
operator fun <E> List<E>.component61(): E = this[60]
operator fun <E> List<E>.component62(): E = this[61]
operator fun <E> List<E>.component63(): E = this[62]
operator fun <E> List<E>.component64(): E = this[63]
operator fun <E> List<E>.component65(): E = this[64]
operator fun <E> List<E>.component66(): E = this[65]
operator fun <E> List<E>.component67(): E = this[66]
operator fun <E> List<E>.component68(): E = this[67]
operator fun <E> List<E>.component69(): E = this[68]
operator fun <E> List<E>.component70(): E = this[69]
operator fun <E> List<E>.component71(): E = this[70]
operator fun <E> List<E>.component72(): E = this[71]
operator fun <E> List<E>.component73(): E = this[72]
operator fun <E> List<E>.component74(): E = this[73]
operator fun <E> List<E>.component75(): E = this[74]
operator fun <E> List<E>.component76(): E = this[75]
operator fun <E> List<E>.component77(): E = this[76]
operator fun <E> List<E>.component78(): E = this[77]
operator fun <E> List<E>.component79(): E = this[78]
operator fun <E> List<E>.component80(): E = this[79]
operator fun <E> List<E>.component81(): E = this[80]
operator fun <E> List<E>.component82(): E = this[81]
operator fun <E> List<E>.component83(): E = this[82]
operator fun <E> List<E>.component84(): E = this[83]
operator fun <E> List<E>.component85(): E = this[84]
operator fun <E> List<E>.component86(): E = this[85]
operator fun <E> List<E>.component87(): E = this[86]
operator fun <E> List<E>.component88(): E = this[87]
operator fun <E> List<E>.component89(): E = this[88]
operator fun <E> List<E>.component90(): E = this[89]
operator fun <E> List<E>.component91(): E = this[90]
operator fun <E> List<E>.component92(): E = this[91]
operator fun <E> List<E>.component93(): E = this[92]
operator fun <E> List<E>.component94(): E = this[93]
operator fun <E> List<E>.component95(): E = this[94]
operator fun <E> List<E>.component96(): E = this[95]
operator fun <E> List<E>.component97(): E = this[96]
operator fun <E> List<E>.component98(): E = this[97]
operator fun <E> List<E>.component99(): E = this[98]
operator fun <E> List<E>.component100(): E = this[99]
// endregion

//endregion
