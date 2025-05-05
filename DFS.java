import java.util.*;

public class Main{
    static class Graph{
        int vertices;
        List<List<Integer>> adj;
        Graph(int v){
            vertices=v;
            adj=new ArrayList<>();
            for(int i=0;i<v;i++){
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u,int v){
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        void dfs(int start){
            boolean[] visited=new boolean[vertices];
            dfsUtil(start, visited);
        }

        void dfsUtil(int node,boolean[] visited){
            visited[node]=true;
            System.out.print(node+" ");
            for(int neighbour: adj.get(node)){
                if(!visited[neighbour]){
                    dfsUtil(neighbour, visited);
                }
            }
        }

        public static void main(String[] args){
            Graph g=new Graph(5);

            g.addEdge(0, 1);
            g.addEdge(0, 2);
            g.addEdge(1, 3);
            g.addEdge(1, 4);

            g.dfs(0);
        }
    }

}
