package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1967_트리의_지름 {

    static int n, maxNum, maxDist = 0;
    static List<int[]>[] tree;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n + 1];
        tree = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(stringTokenizer.nextToken());
            int v2 = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            tree[v1].add(new int[]{v2, d});
            tree[v2].add(new int[]{v1, d});
        }

        DFS(1, 0);

        isVisited = new boolean[n + 1];
        DFS(maxNum, 0);
        System.out.println(maxDist);
    }

    private static void DFS(int now, int dist) {
        if (maxDist < dist) {
            maxDist = dist;
            maxNum = now;
        }

        isVisited[now] = true;

        for (int[] next : tree[now]) {
            if (!isVisited[next[0]]) {
                DFS(next[0], dist + next[1]);
            }
        }
    }
}
