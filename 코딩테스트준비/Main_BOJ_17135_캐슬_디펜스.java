import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17135_캐슬_디펜스 {
    static int N, M, D, ans = Integer.MIN_VALUE;
    static int[][] enemyOfMap, copyOfMap;
    static int[] archer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());

        enemyOfMap = new int[N][M];
        copyOfMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                enemyOfMap[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        archer = new int[M];
        for (int i = 0; i < M; i++) {
            archer[i] = i;
        }

        int[] combiOfArcher = new int[3];
        selArchers(0, 0, combiOfArcher);

        System.out.println(ans);
    }

    private static void selArchers(int cnt, int idx, int[] combiOfArcher) {
        if (cnt == 3) {
            ans = ans < attack(combiOfArcher) ? attack(combiOfArcher) : ans;
            return;
        }
        for (int i = idx; i < archer.length; i++) {
            combiOfArcher[cnt] = archer[i];
            selArchers(cnt + 1, idx + 1, combiOfArcher);
        }
    }

    private static int attack(int[] combiOfArcher) {
        for(int i = 0 ; i < N ; i++)
            copyOfMap[i] = Arrays.copyOf(enemyOfMap[i], M);

        int killOfTurn = 0;
        int turn = 0;
        Queue<int[]> q = new LinkedList<>();

        while (turn < N) {
            for (int k = 0; k < 3; k++) {
                int x = N - turn;
                int y = combiOfArcher[k];

                int min = Integer.MAX_VALUE;
                int minX = -1;
                int minY = -1;

                for (int i = N - turn - 1; i >= 0; i--) {
                    for (int j = 0; j < M; j++) {
                        if (copyOfMap[i][j] == 1) {
                            int dist = Math.abs(i - x) + Math.abs(j - y);

                            if(dist > D) continue;

//                            System.out.println("M : " + M + " dist: " + dist);
                            if (dist < min) {
                                min = dist;
                                minX = i;
                                minY = j;
                            } else if (dist == min) {
                                if (j < minY) {
                                    minX = i;
                                    minY = j;
                                }
                            }
                        }
                    }
                }

                if(minX == -1 || minY == -1) continue;

                q.offer(new int[]{minX, minY});
//                System.out.println("turn : " + turn + " x : " + minX + " y : " + minY + " dist : " + min);
            }

            while (!q.isEmpty()) {
                int x = q.peek()[0];
                int y = q.poll()[1];

                if(copyOfMap[x][y] == 1){
                    copyOfMap[x][y] = 0;
                    killOfTurn += 1;
                }
//                System.out.println("turn : " + turn + " x : " + x + " y : " + y + " kill : " + killOfTurn);
            }
            turn += 1;
        }
        return killOfTurn;
    }
}
