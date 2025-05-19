import java.util.*;

class Edge {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class JohnsonAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    int V; // Number of vertices
    List<Edge> edges;
    List<List<int[]>> adj;

    JohnsonAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
        adj.get(u).add(new int[]{v, w});
    }

    int[] bellmanFord(int src) {
        int[] dist = new int[V + 1]; // Include dummy node
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge e : edges) {
            if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                return null; // Negative cycle detected
            }
        }

        return dist;
    }

    int[] dijkstra(int src, List<List<int[]>> newAdj) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];

            if (d > dist[u]) continue;

            for (int[] neighbor : newAdj.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }

    int[][] johnson() {
        // Step 1: Add a dummy node
        int dummy = V;
        for (int i = 0; i < V; i++) {
            edges.add(new Edge(dummy, i, 0));
        }

        // Step 2: Run Bellman-Ford from dummy node
        int[] h = bellmanFord(dummy);
        if (h == null) {
            System.out.println("Graph contains a negative-weight cycle");
            return null;
        }

        // Step 3: Reweight edges
        List<List<int[]>> newAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) newAdj.add(new ArrayList<>());
        for (Edge e : edges) {
            if (e.from == dummy) continue;
            int newWeight = e.weight + h[e.from] - h[e.to];
            newAdj.get(e.from).add(new int[]{e.to, newWeight});
        }

        // Step 4 & 5: Run Dijkstra for all vertices
        int[][] result = new int[V][V];
        for (int u = 0; u < V; u++) {
            int[] dist = dijkstra(u, newAdj);
            for (int v = 0; v < V; v++) {
                if (dist[v] < INF) {
                    result[u][v] = dist[v] - h[u] + h[v]; // Re-adjust weights
                } else {
                    result[u][v] = INF;
                }
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        JohnsonAlgorithm g = new JohnsonAlgorithm(5);
        g.addEdge(0, 1, -1);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 4, 2);
        g.addEdge(3, 2, 5);
        g.addEdge(3, 1, 1);
        g.addEdge(4, 3, -3);

        int[][] result = g.johnson();

        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    if (result[i][j] == INF) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(result[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
