import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17136_색종이_붙이기 {
    static int[][] map;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[] remains = {0, 5, 5, 5, 5, 5};
        DFS(0, 0, 0, remains);

        if(ans == Integer.MAX_VALUE){ System.out.println(-1);}
        else{ System.out.println(ans);}


    }

    private static void DFS(int x, int y, int cnt, int[] remains) {
        if (x == 9 && y == 10) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (y == 10) {
            DFS(x + 1, 0, cnt, remains);
            return;
        }

        if(cnt >= ans) return;

        if (map[x][y] == 1) {
            for (int j = 5; j >= 1; j--) {
                if (isAttach(x, y, j) && remains[j] > 0) {
                    attach(x, y, j);
                    remains[j] -= 1;
                    DFS(x, y + 1, cnt + 1, remains);
                    detach(x, y, j);
                    remains[j] += 1;
                }
            }
        } else {
            DFS(x, y + 1, cnt, remains);
        }
    }

    private static void detach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = 1;
            }
        }
    }

    private static void attach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = 2;
            }
        }
    }

    private static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= 10 || j >= 10) return false;
                if(map[i][j] != 1) return false;
            }
        }
        return true;
    }
}
