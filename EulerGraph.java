import java.util.*;

public class EulerGraph{
    private int vertices;
    private LinkedList<Integer>[] adj;

    public EulerGraph(int v){
        vertices=v;
        adj=new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i]=new LinkedList<>();
        }
    }

    void addEdge(int u,int v){
        adj[u].add(v);
        adj[v].add(u);
    }

    boolean isConnected(){
        boolean[] visited=new boolean[vertices];

        int i;
        
        for(i=0;i<vertices;i++){
            if(adj[i].size()!=0){
                break;
            }
        }

        if(i==vertices){
            return true;
        }

        dfs(i,visited);

        for(i=0;i<vertices;i++){
            if(!visited[i] && adj[i].size()>0){
                return false;
            }
        }
        return true;
    }

    void dfs(int v,boolean[] visited){
        visited[v]=true;
        for(int u:adj[v]){
            if(!visited[u]){
                dfs(u, visited);
            }
        }
    }

    int oddDegreeCount(){
        int count=0;
        for(int i=0;i<vertices;i++){
            if(adj[i].size()%2!=0){
                count++;
            }
        }
        return count;
    }

    void findEulerType(){
        if(!isConnected()){
            System.out.println("Graph is not connected. Hence, not Eulerian.");
            return;
        }
        int oddCount=oddDegreeCount();

        if(oddCount==0){
            System.out.println("Graph has an Euler Circuit (and is Euler Graph).");
            findEulerCircuit();
        }
        if(oddCount==2){
            System.out.println("Grpah has an Euler Path (but not a Circuit).");
            findEulerPath();
        }
        else{
            System.out.println("Graph is not Eulerian.");
        }

    }

    void findEulerCircuit(){
        Stack<Integer> stack=new Stack<>();
        List<Integer> path=new ArrayList<>();
        int current=0;

        stack.push(current);

        while(!stack.isEmpty()){
            if(adj[current].size()!=0){
                stack.push(current);
                int next=adj[current].get(0);
                removeEdge(current, next);
                current=next;
            }
            else{
                path.add(current);
                current=stack.pop();
            }
        }

        System.out.println("Euler Circuit:");
        for(int v:path){
            System.out.print(v+" ");
        }
        System.out.println();
    }

    void removeEdge(int u,int v){
        adj[u].remove((Integer) v);
        adj[v].remove((Integer) u);
    }
    
    void findEulerPath(){
        Stack<Integer> stack=new Stack<>();
        List<Integer> path=new ArrayList<>();

        int start=0;
        for(int i=0;i<vertices;i++){
            if(adj[i].size()%2 != 0){
                start=i;
                break;
            }
        }

        int current=start;
        stack.push(current);

        while(!stack.isEmpty()){
            if(adj[current].size()!=0){
                stack.push(current);
                int next=adj[current].get(0);
                removeEdge(current, next);
                current=next;
            }
            else{
                path.add(current);
                current=stack.pop();
            }
        }
        System.out.println("Euler Path:");
        for(int v:path){
            System.out.print(v+" ");
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        EulerGraph g=new EulerGraph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);

        g.findEulerType();
    }
}
