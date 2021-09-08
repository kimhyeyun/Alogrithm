import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_18352_특정거리의도시찾기 {

    static ArrayList<Integer>[] adj;
    static int[] dist;
    static int N, M, K, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer sTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(sTokenizer.nextToken());
        M = Integer.parseInt(sTokenizer.nextToken());
        K = Integer.parseInt(sTokenizer.nextToken());
        X = Integer.parseInt(sTokenizer.nextToken());

        adj = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i = 0; i < N+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0  ; i < M ; i++){
            sTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(sTokenizer.nextToken());
            int y = Integer.parseInt(sTokenizer.nextToken());

            adj[x].add(y);
        }
        
        Dijkstra();

        boolean flag = false;

        for(int i = 1 ; i < N+1 ; i++){
            if(dist[i] == K){
                sb.append(i).append("\n");
                flag = true;
            }
        }

        if(flag)
            System.out.print(sb);
        else
            System.out.println(-1);

    }

    private static void Dijkstra() {

        Queue<Integer> q = new LinkedList<>();
        q.offer(X);

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        while(!q.isEmpty()){
            int start = q.poll();

            for(int next : adj[start]){
                if(dist[next] > dist[start] + 1){
                    dist[next] = dist[start]+1;
                    q.offer(next);
                }
            }
        }

    }
}
