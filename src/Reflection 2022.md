# Reflection

## Day 01
24/15; points: 163, 17th place
* OMFG, awesome start
* [x] add max(n) and all related functions

## Day 02
59/12; points: 131, 5th place
* WHU??
* Hardcoded stuff, seems like I wasn't the only one
* Lost quite some time rereading stuff
* [x] maybe add a `mod` infix function to do mod stuff?

## Day 03
39/21; points: 142, 4th place
* ????
* Lost a bunch of time checking assumptions I made by reading the problem text
* [x] `splitIn`
* [x] `Iterable<Set<T>>.union` and `intersect`

## Day 04
27/15; points: 160, 5th place
* separated by minus signs => use posInts()

note: I want to add these helpers
* [x] HetN for n in 2-9
* [x] DFS

## Day 05
64/73; points: 65, 4th place
* input parsing took long
* Don't know why I went with Deques  
* Note from 07/06/2023: can I index with ranges/progressions? if not, add that

## Day 06
114/108; points: 0, 15th place
* lost a lot of time trying to understand which value to return
* also `firstIndexOf` indeed existed, should have trusted myself
* nim-ka has some interesting utils I should steal
  * [x] rotate left and right


note: I want to add thes helpers
* [x] Line3D (maybe LineG?)
* [ ] maybe Bounds3D? (already have BoundsG)
* [x] splitAt
* [ ] grid.findPoints / findSinglePoint (throws if multiple match) 
  * update y17d22
  * => `grid.find`
* [x] pure state loop
  * update y18d18 and others (look for maps)
* [x] allMaxByKey/value

## Day 07
388/320; points: 0, 20th place


## Day 08
32/1871; points: 69, 24th place
* My morning routine was un acceptable. Lost multiple points in part 1 because of that
* Also please no sleep revenge, it's not helping
* ... I have a 13 minutes warning and 3 minute warning
  * at 13 is the moment I should start making sure 
    the early part of the checklist gets done
  * at 3 minutes the middle part should be FULLY DONE


* [x] Bound edges
* [x] norths/easts/souths/wests
* [x] Make grid iterable of BoundedGridPoints
  * `p` -> point
  * `v` -> value
  * `b` -> bounds
  * `g` -> grid
  * mirror neighbours
    * only those in the bounds, map the coordinates to BoundedGridPoints too

## Day 09
53/171; points: 48, 26th place
* I can't believe I choked part 2 again
* forgot the repeating part of part 1 somehow
* and an off by 1 for part 2
* [ ] update pointHelpers
* [x] applyNTimes
* [ ] point + pointL exists, but not for 3d (maybe also make if for nd?)

## Day 10
176/110; points: 0, 27th place
* Missed the fact x had to start at 1
* had 2 off by one errors that cancelled each other during part 1


## Day 11
67/162; points: 34, 32th place
* why did I not realize I'd need to use modulo
* [x] Sint (Safe integer)

## Day 12
7/17; points: 178, 25th place
* =| why is the difference between part 1 and 2 only 1 :whyy:
* [x] make bfs and family versions that take in a list of start nodes :|
* [ ] dijkstra needs sint overloads

also:
* [ ] move points to sint
* [ ] make BoundedGridPoint + Point and all those things?

## Day 13
95/67; points: 40, 30h place
* JSON? JSON??? since when are we doing JSON???

## Day 14
154/78; points: 23, 29th place
* Made a wrong assumption =/

## Day 15
195/795; points: 0, 33th place
* I knew something was off with my last step but /shrug

## Day 16
66/354; points: 35, 32th place
* Lost a bit of time on part 1 due to not having a good vision of a solution
* Caved in after 1.5 hours for part 2 ad checked the subreddit
* [ ] add a way to reduce graphs

## Day 17
60/399; points: 41, 31th place
* I made an off by one typo? for part 1
* I copied the wrong fucking value for part 2

## Day 18
5/5; points: 192, 28th place
* WHOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
* [ ] make sint range a collection

## Day 19
591/576; points: 0, 28th place
* Pfffft

## Day 20
266/247; points: 0, 31th place
* didn't see I had to count from the value 0, had a bunch of 
  code just to handle counting from the start.  
* while trying to find the bug, I just introduced more bugs -_- 

## Day 21
107/503; points: 0, 35th place

## Day 22
68/279; points: 33, 36th place
* [x] make grids use Bounds and Points ...

## Day 23
22/32; points: 148, 33th place
* [x] `allIn` and family did a conversion to set even if the input was a set -_-

## Day 24
129/113; points: 0, 36th place
* spend too much time on making the updates on grid, that I had to rush starting, 
  and apparently I stopped the recording instead of downloading my input 
* could just have done a bounds.retract

## Day 25
32/28; points: 142, 32th place
* Whooo, some balanced based