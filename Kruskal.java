import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Sort edges by weight
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Subset {
    int parent, rank;
}

public class KruskalAlgorithm {

    // Find set of an element i (uses path compression)
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Union of two sets (uses union by rank)
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public void kruskalMST(List<Edge> edges, int V) {
        Collections.sort(edges); // Sort all edges

        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        List<Edge> result = new ArrayList<>();
        int e = 0, i = 0;

        while (e < V - 1 && i < edges.size()) {
            Edge next = edges.get(i++);

            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);

            if (x != y) {
                result.add(next);
                union(subsets, x, y);
                e++;
            }
        }

        // Print the result
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }

    public static void main(String[] args) {
        int V = 4; // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Example edges
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        kruskal.kruskalMST(edges, V);
    }
}
