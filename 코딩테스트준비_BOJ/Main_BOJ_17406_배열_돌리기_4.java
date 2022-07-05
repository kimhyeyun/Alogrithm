import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_17406_배열_돌리기_4 {
    static int N, M, K, ans = Integer.MAX_VALUE;
    static int[][] arr, rcs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[N][M];
        rcs = new int[K][3];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            rcs[i][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            rcs[i][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            rcs[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Perm(new boolean[K], new LinkedList<>());

        System.out.println(ans);
    }

    private static void Perm(boolean[] isChoose, LinkedList<Integer> list) {
        if (list.size() == K) {
            int[][] tmp = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(arr[i], 0, tmp[i], 0, M);
            }

            for (int l : list) {
                rotateArray(rcs[l][0], rcs[l][1], rcs[l][2], tmp);
            }
            ans = sumOfRow(tmp) < ans ? sumOfRow(tmp) : ans;
        }
        for (int i = 0; i < K; i++) {
            if (!isChoose[i]) {
                isChoose[i] = true;
                list.add(i);
                Perm(isChoose, list);
                isChoose[i] = false;
                list.removeLast();
            }
        }
    }

    private static int sumOfRow(int[][] tmp) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = 0;
            for (int j = 0; j < M; j++) {
                sum += tmp[i][j];
            }

            min = sum < min ? sum : min;
        }

        return min;
    }


    private static void rotateArray(int r, int c, int s, int[][] tmp) {
        for (int i = 0; i < s; i++) {
            int r1 = r - s + i;
            int c1 = c - s + i;
            int r2 = r + s - i;
            int c2 = c + s - i;

            rotate(r1, c1, r2, c2, tmp);
        }


    }

    private static void rotate(int r1, int c1, int r2, int c2, int[][] tmp) {
        int t = tmp[r1][c1];

        for (int i = r1+1; i <= r2; i++) {
            tmp[i - 1][c1] = tmp[i][c1];
        }
        for (int j = c1; j < c2; j++) {
            tmp[r2][j] = tmp[r2][j + 1];
        }
        for (int i = r2; i > r1; i--) {
            tmp[i][c2] = tmp[i - 1][c2];
        }
        for (int j = c2; j > c1; j--) {
            tmp[r1][j] = tmp[r1][j - 1];
        }

        tmp[r1][c1 + 1] = t;
    }
}
