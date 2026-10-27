public class DisjointSet {
    private final int[] parent, size;
    private int components;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        components = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        int root = x;

        while (root != parent[root]) {
            root = parent[root];
        }
        while (x != root) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }
        return root;
    }
    public boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return false;
        }
        if (size[ra] < size[rb]) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        parent[rb] = ra;
        size[ra] += size[rb];
        components--;
        return true;
    }
    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
    public int count() {
        return components;
    }
}
