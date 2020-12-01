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

### Day 2 conversion
* Used input of day 1
    * Make a file for the next day in advance with some imports and a start.
* Worksheet doesn't display exceptions
* Noticed debugger freezes if a function outside the worksheet is called
    * [x] Have a secondary file `help.kt` to temporary copy parts of the code into

Also made a tentative `IntComputer`

### Day3
* Input was csv style
    * [x] Make csv (comma (`,`) separated values), ssv (semicolon (`;`) separated values) and wsv (whitespace `/\s/` separated values)
* An integer-string had a `\r` at the end
    * [x] Make `String.getInt()`, `String.getInts()` and similar extension functions 
* Worksheets failed to execute `map` and `sumBy` properly in some cases?
    * => Make all future solutions in normal Kotlin code
* Didn't read problem statement well enough
    * => Listen to my gut when it says "huh, that's weird" instead of of just assuming it's a design decision to keep the questions easy
* Struggled a bit too much with set (and map) operations
    * [x] Make helper functions
    * [x] Make operator overloading for sets and maps
* Imports for `abs` don't list `abs(Int)`.
    * => Pick `abs(Double)` in  `kotlin.Math`. This will pick the right overload anyway
    * [x] Add `import kotlin.Math` to the template

#### Also add:
*[x] 2D point operations (`Point = Pair<Int,Int>`)
*[x] `RDLU` to point
*[x] Distance calculators
*[x] `Iterable<Point<T>>.getClosest()` and similar

#### Ideas:
*[x] Make an RLE encoder and decode
*[x] FoldMap: map but with previous value/folding value 
    * This would allow the input to be RLE decode and then foldmaped with the `+` operator to give all points
    * Realized this is `scan` so renamed  

### Some training
#### 2017/4
No looking up, just gut timing but question is too easy to really get results.
#### 2017/7
Looked up some timings to verify it would be a bit of a harder problem.

Initially I felt like I was gonna need topoSort but wasn't needed
* Missed the `,`'s in the input
    *[x] add a `getAlphaNum()` and similar
    *[x] maybe add `getIntsLines() = getLines().map{it.getInts()}` and similar
* Forgot to call the recursive formula .. (OOF) 

### Day 4
* Thought I couldn't use groupBy so for part 2 I even wrote a regex to grab continuous chunks of numbers and then test if any had length 2
    * I could cause the input would also need to sorted.
    *[x] Add a `blocks` method
*[x] add `isSorted`
*[x] add convolutions => `zipWithNext` and windowed exists

### Day 5
* Need more sleep
* Mental error about `scan` when trying to extract modes
    * [x] make a scan that updates state and returns separately
* "No i dOnT ThInK I NeEd aN ErRoR If iM TrYiNg tO OvErRiDe aN OpCoDe"
    * [ ] add an error
* Use typealiases in tentative things. 

### Day 6
* [ ] Graphs
* Run functions twice, first on test, then on actual data. Should show off by one errors faster
* [ ] Add associateIndex

### Day 7
* [ ] getPermutations
* [x] make the IntComputer io and yield more clean
* [ ] quick input naming

### Day 8
* Forgot reduce existed
* [ ] List.printLines maybe? errr .map{println(it)} is quite good

### Day 10
* Grids...
    * Point steps
* GGD and similar

### Day 11
* [x] Clean the template and make the functions runblocking by default
* [ ] Grids
* [ ] directional points
* [ ] list of points to Bounds
* [ ] Bound iteration
* [ ] text printer
* [ ] mod int (used for dirs) (how will this be used?)
* [ ] `Clock()`

### Day 12
* Unused `==` is hardly visible

### Day 13
* Shouldn't have used multiple threads, just ignore player input.
* Should have used a conflated channel and send updates each change in ball and paddle pos.
* [ ] grids
* [ ] better display for grids?
* [ ] `clock()`

### Day 14
* Int division rounds to 0, not down. WTF
* [x] binary search thingies
* Maybe start using Long as default

### Day 15
* NICE
* [ ] maybe even more clock functionality
    * [ ] a way to convert from point to neighbour id and back 

### Day ~~16~~ 17
* [ ] list of char/char array to string is hard
* for part 2 i think I got a more difficult input.

### Day 18
* [ ] Maze simplifiers
* [ ] Path algos

### Day 19
* 

### Day 20
* So IntelliJ just removed y trailing spaces, which were kinda needed. lost 5 minutes and 17 points due to that. Something to remember for next time
    * StrictEntityGrid gives me a bounds. I should have used that...
* [ ] PathFinding algorithms could be useful


### Potential ToDo's
* [x] Make Point an actual class
* [x] Convolutions, forEach(unordered/Ordered)Pair/
* [ ] Some fancy lists
    * [ ] cyclic list
    * [ ] bi list (list for pos and neg indexes)
    * [ ] py list? (wrapper? that allows neg index to count from back, like cyclic)
    * [ ] default list
* [ ] Grids
    * [ ] a lot of freedom wanted yet many quick build-ins. Maybe builder?
    * [ ] (quad)grid
    * [ ] door grid
    * [ ] gated-grid = grid + doors
    * [ ] hex-grid
    * [ ] triangle grid
    * [ ] Path calculation
    * [ ] closest, furthest
    * [ ] 3d grids?
    * [ ] sum query?
        * [ ] max query
        * [ ] binOp query
* [ ] Graphs (and grids are graphs)
* [ ] BracketStack
* [x] Bounds
* [ ] (stepped) Toposort
* [ ] eventqueue 
* [ ] ticking game system // for turnbased combat or similar
* [ ] add a lot of `...Indexed()`
* [ ] sequences
* [x] python itertools ish
* [ ] pure intcomputer