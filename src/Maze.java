import java.util.*;
public class Maze {
    public static int[] breakWalls(Input.Walls w) {
        DisjointSet dsu = new DisjointSet(w.cells());
        int[] result = new int[w.cells() - 1];
        int used = 0;
        for (int k = 0; k < w.count(); k++) {
            if (dsu.union(w.a()[k], w.b()[k])) {
                result[used++] = k;
            }
            if (used == result.length) {
                break;
            }
        }
        return Arrays.copyOf(result, used);
    }
    public static int[] cheapestMaze(Input.Walls w) {
        Integer[] order = new Integer[w.count()];

        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, Comparator.comparingInt(i -> w.cost()[i]));
        DisjointSet dsu = new DisjointSet(w.cells());
        int[] result = new int[w.cells() - 1];
        int used = 0;

        for (int k : order) {
            if (dsu.union(w.a()[k], w.b()[k])) {
                result[used++] = k;
            }
            if (used == result.length) {
                break;
            }
        }
        return Arrays.copyOf(result, used);
    }
    public static long primCost(Input.Walls w) {
        int n = w.cells();
        int[] degree = new int[n];
        for (int k = 0; k < w.count(); k++) {
            degree[w.a()[k]]++;
            degree[w.b()[k]]++;
        }
        int[][] edges = new int[n][];

        for (int i = 0; i < n; i++) {
            edges[i] = new int[degree[i]];
        }
        int[] fill = new int[n];
        for (int k = 0; k < w.count(); k++) {
            int a = w.a()[k];
            int b = w.b()[k];

            edges[a][fill[a]++] = k;
            edges[b][fill[b]++] = k;
        }
        boolean[] visited = new boolean[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(k -> w.cost()[k]));
        visited[0] = true;
        for (int k : edges[0]) {
            pq.add(k);
        }

        long cost = 0;
        int seen = 1;

        while (!pq.isEmpty() && seen < n) {
            int k = pq.poll();
            int a = w.a()[k];
            int b = w.b()[k];
            if (visited[a] && visited[b]) {
                continue;
            }
            int next = visited[a] ? b : a;
            visited[next] = true;
            seen++;
            cost += w.cost()[k];
            for (int edge : edges[next]) {
                pq.add(edge);
            }
        }
        return cost;
    }
}
