import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17199_Milk_Factory {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] floyd = new int[N + 1][N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            floyd[x][y] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if(k == j || i == j) continue;
                    if(floyd[i][k] == 1 && floyd[k][j] == 1) floyd[i][j] = 1;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if(floyd[j][i] == 1) cnt += 1;
            }
            if (cnt == N - 1) {
                answer = i;
                break;
            }
        }

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}
