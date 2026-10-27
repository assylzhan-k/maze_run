import java.util.*;
public class Escape {
    public record Result(int[] path, int visited, int deadEnds)
    {}
    public static Result escape(Input.Walls w, int[] broken)
    {
        int n = w.cells();
        int cols = w.cols();
        int[][] g = Graphs.open(w, broken);
        int deadEnds = 0;

        for (int[] neighbours : g) {
            if (neighbours.length == 1) {
                deadEnds++;
            }
        }
        boolean[] seen = new boolean[n];

        int[] parent = new int[n];
        int[] nextDirection = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);
        int top = 0;
        int visited = 1;
        stack[0] = 0;
        seen[0] = true;

        while (top >= 0 && !seen[n - 1]) {
            int u = stack[top];

            if (nextDirection[u] == 4) {
                top--;
                continue;
            }
            int dir = nextDirection[u]++;
            int v = switch (dir) {
                case 0 -> u - cols;
                case 1 -> u + 1;
                case 2 -> u + cols;
                default -> u - 1;
            };
            if (v < 0 || v >= n || (dir == 1 && u % cols == cols - 1) || (dir == 3 && u % cols == 0) || seen[v]) {
                continue;
            }
            boolean open = false;

            for (int neighbor : g[u]) {
                if (neighbor == v) {
                    open = true;
                    break;
                }
            }
            if (!open) {
                continue;
            }
            parent[v] = u;
            seen[v] = true;
            visited++;

            stack[++top] = v;
        }
        if (!seen[n - 1]) {
            return new Result(new int[0], visited, deadEnds);
        }
        int length = 0;

        for (int v = n - 1; v != -1; v = parent[v]) {
            length++;
        }
        int[] path = new int[length];
        for (int v = n - 1, i = length - 1; i >= 0; v = parent[v], i--) {
            path[i] = v;
        }
        return new Result(path, visited, deadEnds);
    }
}
