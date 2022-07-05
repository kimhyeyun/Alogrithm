import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18808_스티커_붙이기 {
    static int N, M, K, R, C, ans = 0;
    static int[][] laptop, sticker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        laptop = new int[N][M];
        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());

            R = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            sticker = new int[R][C];

            for (int i = 0; i < R; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            findLocation();
        }

        System.out.println(ans);

    }

    private static void findLocation() {
        for (int d = 0; d < 4; d++) {
            if(d != 0) rotateSticker();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(i + R > N || j + C > M) break;
                    if(attackSticker(i,j)) return;
                }
            }
        }
    }

    private static boolean attackSticker(int x, int y) {
        for (int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                if(laptop[i][j] == 1 && sticker[i-x][j-y] == 1) return false;
            }
        }

        for (int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                if (sticker[i - x][j - y] == 1) {
                    laptop[i][j] = 1;
                    ans += 1;
                }
            }
        }
        return true;
    }

    private static void rotateSticker() {
        int[][] tmpSticker = new int[C][R];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmpSticker[j][R - i - 1] = sticker[i][j];
            }
        }

        int tmp = R;
        R = C;
        C = tmp;
        sticker = new int[R][C];

        for (int i = 0; i < R; i++) {
            System.arraycopy(tmpSticker[i], 0, sticker[i], 0, C);
        }
    }
}
