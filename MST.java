import java.util.*;

public class Prim{
    static class Pair{
        int vertex,weight;

        Pair(int v,int w){
            vertex=v;
            weight=w;
        }
    }

    public static int primMST(int n,List<List<Pair>> adj){
        boolean[] visited=new boolean[n];
        PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
        pq.add(new Pair(0, 0));
        int mstWeight=0;

        while(!pq.isEmpty()){
            Pair current=pq.poll();
            if(visited[current.vertex]) continue;

            visited[current.vertex]=true;
            mstWeight+=current.weight;

            for(Pair neighbor:adj.get(current.vertex)){
                if(!visited[neighbor.vertex]){
                    pq.add(new Pair(neighbor.vertex, neighbor.weight));
                }
            }
        }
        return mstWeight;
    }

    public static void main(String[] args){
        int n=4;
        List<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 1));
        adj.get(1).add(new Pair(0, 1));

        adj.get(0).add(new Pair(2, 3));
        adj.get(2).add(new Pair(0, 3));

        adj.get(1).add(new Pair(2, 1));
        adj.get(2).add(new Pair(1, 1));

        adj.get(1).add(new Pair(3, 4));
        adj.get(3).add(new Pair(1, 4));

        adj.get(2).add(new Pair(3, 1));
        adj.get(3).add(new Pair(2, 1));

        System.out.println("Prim MST Total Weight: "+primMST(n,adj));
    }
}
