# Assignment 3. Maze Escape

Student ID: 253941
Name: Assylzhan Kishibayeva
Group: SE-2512
Maze size (rows x columns):

## Project Description

This project implements graph algorithms to generate,
analyze, and solve a maze.

The main algorithms are:
- Disjoint Set Union (DSU)
- Kruskal's algorithm
- Prim's algorithm
- Depth-First Search (DFS)
- Kosaraju's algorithm
- Dynamic Programming on a DAG


## Program output
```
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
```

My maze:

<img width="873" height="647" alt="Screenshot 2026-10-27 at 19 21 17" src="https://github.com/user-attachments/assets/4816fe2c-87cc-4971-ade3-d80185aaa43a" />


## Questions

### Mission 0. 
The find operation returns the root of the set containing an element. Union merges
two different sets. Path compression makes find faster, while
union by size keeps the trees shallow.

### Mission 1. 
A maze with R*C cells needs exactly R*C - 1 broken walls to connect all cells without cycles.
The original grid has R(C-1) + C(R-1) internal walls. After breaking R*C - 1 walls, the number remaining is (R-1)*(C-1).

### Mission 2. 
Kruskal MST cost: 31167
Prim MST cost: 31167
Both algorithms produce the same minimum
spanning tree cost because they find
an MST of the same connected weighted graph.
Kruskal sorts edges by weight and uses DSU.
Prim grows a tree using a priority queue.

### Mission 3. 
There is exactly one path between any two cells because the generated maze is a tree. A tree is connected and contains no cycles.
I used iterative DFS with an explicit stack instead of recursion to prevent stack overflow on large mazes.

### Mission 4. 
Kosaraju's algorithm uses two DFS passes.The first pass finds the finishing order. The second pass uses the reversed graph to discover strongly connected components.Reversing the graph allows each DFS to identify one SCC in the required order.Because the input contains no self-loops, the slides form a DAG exactly when every SCC contains one cell.

### Mission 5. 
Kosaraju numbers the zones in topological order, so all edges between different zones go from smaller to larger zone numbers.
This allows dynamic programming to find the longest chain and shortest route without using a priority queue.
The processing time is O(V + E) for the zone graph, where V is the number of zones and E is the number of inter-zone edges.

## AI use
I used ChatGPT to help understand the requirements, develop and review the DSU and graph algorithms, and check the implementation against the examples. I reviewed the explanations and algorithms to prepare for the defense.

## Timing (Mission 7)

Milliseconds, 5 runs each, from `java -cp out Bench`.

| maze | Kruskal | Prim |
|---|---|---|
| 100 x 100 | 8 | 8 |
| 300 x 300 | 36 | 74 |
| 1000 x 1000 | 519 | 1692 |

## Analysis
The execution time of both algorithms increases as the maze size grows. Kruskal was faster than Prim on the larger mazes. For the 1000 x 1000 maze, Kruskal took 519 ms, while Prim took 1692 ms.
This happens because Kruskal sorts the edges once and uses an efficient Disjoint Set Union (DSU), while Prim performs many priority queue operations.


## Complexity Analysis

Here n is the number of cells, m is the
number of walls or slides, and alpha(n)
is the inverse Ackermann function.

| Algorithm | Time | Space |
|---|---|---|
| DSU find/union | O(alpha(n)) amortized | O(n) total DSU storage |
| Kruskal | O(m log m + m alpha(n)) | O(n + m) |
| Prim | O(m log(m+1)) | O(n + m) |
| DFS | O(n + m) | O(n + m) |
| Kosaraju | O(n + m) | O(n + m) |
| DAG route | O(V + E) | O(V + E) |

## Conclusion

This assignment demonstrates how graph algorithms can solve different problems using the same maze representation.
DSU and MST algorithms build the maze. DFS finds the escape path. Kosaraju identifies strongly connected zones, while dynamic programming finds the fastest route through the zone graph.
