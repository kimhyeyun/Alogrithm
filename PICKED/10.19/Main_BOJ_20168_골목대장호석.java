import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_20168_골목대장호석 {

    static class road{
        int from;
        int to;
        int cost;

        road(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    static int N, M, A, B, C;
    static ArrayList<road> adj[];
    static boolean[] isVisited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        A = Integer.parseInt(stringTokenizer.nextToken());
        B = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        adj = new ArrayList[N+1];
        for(int i = 0 ; i < N+1; i++){
            adj[i] = new ArrayList<>();
        }

        isVisited = new boolean[N+1];
        answer = Integer.MAX_VALUE;

        for(int i = 0 ; i < M ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            adj[a].add(new road(a, b, c));
            adj[b].add(new road(b, a, c));
        }

        isVisited[A] = true;
        DFS(A, C, -1);

        answer = answer == Integer.MAX_VALUE ? -1 : answer;

        System.out.println(answer);

    }

    private static void DFS(int nowNode, int haveMoney, int maxCost) {
        if(nowNode == B){
            answer = Math.min(answer, maxCost);
            return;
        }

        if(haveMoney <= 0)
            return;

        for(road nextNode : adj[nowNode]){
            if(!isVisited[nextNode.to] && nextNode.cost <= haveMoney){
                isVisited[nextNode.to] = true;
                DFS(nextNode.to, haveMoney - nextNode.cost, Math.max(nextNode.cost, maxCost));
                isVisited[nextNode.to] = false;
            }
        }
    }
}