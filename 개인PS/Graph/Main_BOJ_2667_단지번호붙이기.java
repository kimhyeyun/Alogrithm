package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2667_단지번호붙이기 {
    static int N;
    static char[][] isApartMent;
    static boolean[][] isVisited;
    static List<Integer> ans = new ArrayList<>();
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        isApartMent = new char[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            isApartMent[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j] && isApartMent[i][j] == '1') {
                    BFS(i, j);
                }
            }
        }

        Collections.sort(ans);
        System.out.println(ans.size());
        for (int a : ans) {
            System.out.println(a);
        }
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        isVisited[x][y] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny] || isApartMent[nx][ny] == '0') continue;

                cnt += 1;
                q.offer(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }

        ans.add(cnt);
    }
}
