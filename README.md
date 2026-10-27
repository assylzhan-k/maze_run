# Assignment 3. Maze Escape

Student ID: 253941
Name: Assylzhan Kishibayeva
Group: SE-2512
Maze size (rows x columns):

## Program output

The 13 lines your program prints on your data (`java -cp out Main`):
``
Broken: 109
Kept: 90
Last broken wall: 154
MST cost: 31167
Heaviest broken wall: 880
Path length: 27
DFS visited: 83
Dead ends: 34
Zones: 12
Largest zone: 16
Is DAG: 0
Flow stages: 6
Fastest route cost: 15
``

My maze:

![maze](../output/maze.png)

## Questions

Answer in your own words, two or three sentences each.

Mission 0. What do find and union do, and what makes them fast in your code?

Mission 1. Why is Broken always R*C - 1, and Kept always (R-1)*(C-1)?

Mission 2. My MST cost from Kruskal and from Prim, and why they are equal:

Mission 3. Why is there exactly one path between two cells? How did you avoid a stack overflow on a huge maze?

Mission 4. Why does the second pass of Kosaraju go on the reversed graph? Why do the slides have no cycle exactly when Zones equals the number of cells?

Mission 5. Why is it enough to go through the zones in the order of their numbers? What does this pass cost?

## AI use
i used chatgpt for it to explain me code lines

## Timing (Mission 7)

Milliseconds, 5 runs each, from `java -cp out Bench`.

| maze | Kruskal | Prim |
|---|---|---|
| 100 x 100 | | |
| 300 x 300 | | |
| 1000 x 1000 | | |


## Complexity

n is the number of cells, m the number of walls (or slides). Fill in every algorithm you wrote.

| algorithm | time | space | assumptions |
|---|---|---|---|
| DSU find and union | | | |
| Kruskal | | | |
| Prim | | | |
| DFS | | | |
| Kosaraju | | | |
| Route in the zone graph | | | |
