import java.util.*;

public class FloydWarshall{

    static final int INF=1000000000;

    public static void floydWarshall(int[][] dist,int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]<INF && dist[k][j]<INF){
                        dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        int n=4;

        int[][] dist={
            {0,5,INF,10},
            {INF,0,3,INF},
            {INF,INF,0,1},
            {INF,INF,INF,0}
        };

        floydWarshall(dist, n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dist[i][j]==INF){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(dist[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
