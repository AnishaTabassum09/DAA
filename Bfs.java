import java.util.*;

class Graph{
    private int V;
    private LinkedList<Integer>[] adj;
    public Graph(int V){
        this.V=V;
        adj=new LinkedList[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<>();
        }
    }
    public void addEdge(int v,int w){
        adj[v].add(w);
    }
    public void BFS(int start){
        boolean[] visited=new boolean[V];
        Queue<Integer> queue=new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            int node=queue.poll();
            System.out.print(node+" ");

            for(int neighbor : adj[node]){
                if(!visited[neighbor]){
                    visited[neighbor]=true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args){
        Graph g=new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        System.out.println("BFS starting from node 0:");
        g.BFS(0); 
    }
}
