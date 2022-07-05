import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17135_캐슬_디펜스_re {
    static int N, M, D, ans = Integer.MIN_VALUE;
    static int[][] castle;
    static int[] archers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());

        castle = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        archers = new int[M];
        for (int i = 0; i < M; i++) {
            archers[i] = i;
        }

        int[] selArchers = new int[3];
        DFS(0, 0, selArchers);

        System.out.println(ans);
    }

    private static void DFS(int cnt, int idx, int[] selArchers) {
        if (cnt == 3) {
            int kill = attack(selArchers);
            ans = ans < kill ? kill : ans;
            return;
        }

        for (int i = idx; i < archers.length; i++) {
            selArchers[cnt] = archers[i];
            DFS(cnt + 1, i, selArchers);
        }
    }

    private static int attack(int[] selArchers) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(castle[i], 0, tmp[i], 0, M);
        }

        int killOfTurn = 0, turn = 0;
        Queue<int[]> q = new LinkedList<>();

        while (turn < N) {
            for (int k = 0; k < 3; k++) {
                int x = N - turn;
                int y = selArchers[k];

                int min = Integer.MAX_VALUE;
                int minX = -1, minY = -1;

                for (int i = N - turn - 1; i >= 0; i--) {
                    for (int j = 0; j < M; j++) {
                        if (tmp[i][j] == 1) {
                            int dist = Math.abs(i - x) + Math.abs(j - y);

                            if(dist > D) continue;

                            if (dist < min) {
                                min = dist;
                                minX = i;
                                minY = j;
                            } else if (dist == min){
                                if (j < minY) {
                                    min = dist;
                                    minX = i;
                                    minY = j;
                                }
                            }

                        }
                    }
                }

                if(minX == -1 || minY == -1) continue;
                q.add(new int[]{minX, minY});
            }

            while (!q.isEmpty()) {
                int[] now = q.poll();

                if (tmp[now[0]][now[1]] == 1) {
                    tmp[now[0]][now[1]] = 0;
                    killOfTurn += 1;
                }
            }

            turn += 1;
        }

        return killOfTurn;
    }
}
