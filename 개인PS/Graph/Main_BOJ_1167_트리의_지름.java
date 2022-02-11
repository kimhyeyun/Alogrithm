package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1167_트리의_지름 {

    static int V, maxDist = 0, maxNum;
    static List<int[]>[] adj;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        V = Integer.parseInt(br.readLine());

        isVisited = new boolean[V + 1];
        adj = new List[V + 1];
        for (int i = 0; i < V + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            while (true) {
                int v = Integer.parseInt(stringTokenizer.nextToken());
                if (v == -1) break;
                int d = Integer.parseInt(stringTokenizer.nextToken());
                adj[x].add(new int[]{v, d});
            }
        }

        DFS(1, 0);

        isVisited = new boolean[V + 1];
        DFS(maxNum, 0);

        System.out.println(maxDist);



    }

    private static void DFS(int now, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            maxNum = now;
        }
        isVisited[now] = true;
        for (int[] next : adj[now]) {
            if (!isVisited[next[0]]) {
                DFS(next[0], next[1] + dist);
            }
        }
    }

}