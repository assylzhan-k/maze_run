import java.util.*;
public class Zones {
    public static int[] kosaraju(Input.Slides s) {
        int n = s.n();
        int[][] out = Graphs.slidesFrom(s);
        int[][] in = Graphs.slidesInto(s);
        boolean[] seen = new boolean[n];
        int[] finish = new int[n];
        int[] next = new int[n];
        int[] stack = new int[n];
        int finished = 0;

        // first dfs pass
        for (int start = 0; start < n; start++) {
            if (seen[start]) {
                continue;
            }
            int top = 0;
            stack[0] = start;
            seen[start] = true;
            while (top >= 0) {
                int u = stack[top];
                if (next[u] == out[u].length) {
                    finish[finished++] = u;
                    top--;
                    continue;
                }
                int v = s.to()[out[u][next[u]++]];

                if (!seen[v]) {
                    seen[v] = true;
                    stack[++top] = v;
                }
            }
        }
        // second dfs pass
        int[] zone = new int[n];
        Arrays.fill(zone, -1);
        int count = 0;
        for (int i = finished - 1; i >= 0; i--) {
            int start = finish[i];
            if (zone[start] != -1) {
                continue;
            }
            int top = 0;
            stack[0] = start;
            zone[start] = count;
            while (top >= 0) {
                int u = stack[top--];

                for (int edge : in[u]) {
                    int v = s.from()[edge];

                    if (zone[v] == -1) {
                        zone[v] = count;
                        stack[++top] = v;
                    }
                }
            }
            count++;
        }
        return zone;
    }
    public static long[] routes(
            Input.Slides s,
            int[] zoneOf,
            int zoneCount
    ) {
        int[] stage = new int[zoneCount];
        Arrays.fill(stage, 1);
        long inf = Long.MAX_VALUE / 4;
        long[] distance = new long[zoneCount];
        Arrays.fill(distance, inf);
        distance[zoneOf[0]] = 0;

        // count outgoing edges
        int[] degree = new int[zoneCount];

        for (int i = 0; i < s.count(); i++) {
            int a = zoneOf[s.from()[i]];
            int b = zoneOf[s.to()[i]];

            if (a != b) {
                degree[a]++;
            }
        }

        // build graph
        int[][] edges = new int[zoneCount][];

        for (int z = 0; z < zoneCount; z++) {
            edges[z] = new int[degree[z]];
        }

        int[] fill = new int[zoneCount];

        for (int i = 0; i < s.count(); i++) {
            int a = zoneOf[s.from()[i]];
            int b = zoneOf[s.to()[i]];

            if (a != b) {
                edges[a][fill[a]++] = i;
            }
        }
        // dynamic programming
        int longest = 1;

        for (int z = 0; z < zoneCount; z++) {
            longest = Math.max(longest, stage[z]);

            for (int edge : edges[z]) {
                int to = zoneOf[s.to()[edge]];

                stage[to] = Math.max(
                        stage[to],
                        stage[z] + 1
                );

                if (distance[z] != inf) {
                    distance[to] = Math.min(
                            distance[to],
                            distance[z] + s.time()[edge]
                    );
                }
            }
        }
        for (int v : stage) {
            longest = Math.max(longest, v);
        }
        return new long[]{
                longest,
                distance[zoneOf[s.n() - 1]]
        };
    }
}
