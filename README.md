# AdventOfCodeSolutions

My Advent Of Code Solutions

NOTE: This code is cursed, I would never write this kind of code for anything
that has to actually be maintained. I have been trying (and sometimes succeeding)
to get points. I do not have time to waste when writing this code at 6am in the
morning. Read the code at your own risk.

## Layout

There are a few submodules. The `util` is for all the utility functions I use.
These are untested, (apart from there occasional use in the problem solving)
and have been incorrect in multiple occasions.

`solutions` is where is store my solutions longterm. These usually have a separate
code function for part 1 and part 2, and have most of their log functions commented out
so that the only 2 things being printed are the 2 solutions. These files are usually
recreated from my IDE's local history/the recording I have of that day.

`leaderboard` contains a calculator to track my place on the leaderboard. 
The parameters are configurable in the file itself.

Then there is often a single file in the main module that is mostly empty
as template for the next day, or contains the solution of the last day. 
This is where I make my actual solutions. As my solutions are written in kotlin
I do have to take compiler runtime into account. By only having a single file in 
the main module, all the `util` functions are cached, and other solutions will be
ignored. 

In the main module there are further a few reflection notes, these are used to
decided on new util functions, or help me refresh on important information
between days/years. The checklist file finally is used to make sure my work space
is in perfect condition to solve the daily problem. This includes stuff like making
sure the compiler daemon has started and can actually compile code. 

