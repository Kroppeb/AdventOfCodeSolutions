# Reflection

## Python

### Day 1
* Alarm problems
    * [x] Have someone call me to check if I have woken up

### Day 2
* Made a dumb typo which took long to find

## Kotlin
I decided to switch to Kotlin as I felt that Kotlin would be more powerful.
I went with making the solutions in Kotlin Worksheets mostly to get rid of the `fun main(){}` overhead and easy display of intermediate results

## Day 2 conversion
* Used input of day 1
    * Make a file for the next day in advance with some imports and a start.
* Worksheet doesn't display exceptions
* Noticed debugger freezes if a function outside the worksheet is called
    * [x] Have a secondary file `help.kt` to temporary copy parts of the code into

Also made a tentative `IntComputer`

## Day3
* Input was csv style
    * [ ] Make csv (comma (`,`) separated values), ssv (semicolon (`;`) separated values) and wsv (whitespace `/\s/` separated values)
* An integer-string had a `\r` at the end
    * [ ] Make `String.getInt()`, `String.getInts()` and similar extension functions 
* Worksheets failed to execute `map` and `sumBy` properly in some cases?
    * [ ] Make all solutions in normal Kotlin code
* Didn't read problem statement well enough
    * [ ] Listen to my gut when it says "huh, that's weird" instead of of just assuming it's a design decision to keep the questions easy
* Struggled a bit too much with set (and map) operations
    * [ ] Make helper functions
    * [ ] Make operator overloading for sets and maps
* Imports for `abs` don't list `abs(Int)`.
    * => Pick `abs(Double)` in  `kotlin.Math`. This will pick the right overload anyway
    * [ ] Add `import kotlin.Math` to the template

Also add:
*[ ] 2D point operations (`Point<T> = Pair<T,T> where T:Number`)
*[ ] `RDLU` to point
*[ ] Distance calculators
*[ ] `Iterable<Point<T>>.getClosest()` and similar

Ideas:
*[ ] Make an RLE encoder and decode
*[ ] FoldMap: map but with previous value/folding value 
    * This would allow the input to be RLE decode and then foldmaped with the `+` operator to give all points  
