package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_11725_트리의_부모_찾기 {

    static int N;
    static List<Integer>[] tree;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        result = new int[N + 1];
        tree = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            tree[p].add(c);
            tree[c].add(p);
        }

        BFS();
        for (int i = 2; i <= N; i++) {
            System.out.println(result[i]);
        }

    }

    private static void BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int start = q.poll();
            for (int next : tree[start]) {
                if (result[next] == 0) {
                    result[next] = start;
                    q.add(next);
                }
            }
        }
    }
}
