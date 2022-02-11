package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9466_텀_프로젝트 {
    static int T, n, cnt = 0;
    static int[] arr;
    static boolean[] isVisited, isFinished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n + 1];
            isVisited = new boolean[n + 1];
            isFinished = new boolean[n + 1];

            cnt = 0;

            stringTokenizer = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                DFS(i);
            }

            System.out.println(n - cnt);
        }
    }

    private static void DFS(int start) {
        isVisited[start] = true;
        int next = arr[start];

        if (!isVisited[next]) {
            DFS(next);
        } else {
            if (!isFinished[next]) {
                cnt += 1;
                while (next != start) {
                    cnt += 1;
                    next = arr[next];
                }
            }
        }

        isFinished[start] = true;
    }
}
