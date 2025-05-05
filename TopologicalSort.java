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
        }

        void topologicalSort(){
            boolean[] visited=new boolean[vertices];
            Stack<Integer> stack=new Stack<>();

            for(int i=0;i<vertices;i++){
                if(!visited[i]){
                    dfs(i, visited, stack);
                }
            }
            while(!stack.isEmpty()){
                System.out.print(stack.pop()+" ");
            }
        }

        void dfs(int node,boolean[] visited,Stack<Integer> stack){
            visited[node]=true;

            for(int neighbour:adj.get(node)){
                if(!visited[neighbour]){
                    dfs(neighbour, visited, stack);
                }
            }

            stack.push(node);
        }

        public static void main(String[] args){
            Graph g=new Graph(6);

            g.addEdge(5, 2);
            g.addEdge(5, 0);
            g.addEdge(4, 0);
            g.addEdge(4, 1);
            g.addEdge(2, 3);
            g.addEdge(3, 1);

            g.topologicalSort();
        }
    }
}
