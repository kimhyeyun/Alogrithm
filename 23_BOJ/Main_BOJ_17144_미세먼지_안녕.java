import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지_안녕 {
    static int N, M, T;
    static int[][] map;
    static int top, bottom;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1) bottom = i;
            }
        }

        top = bottom - 1;

        while (T-- > 0) {
            spreadFineDust();
//            printMap();
            workingAirConditioner();
//            printMap();
        }

        System.out.println(sumOfFineDust());
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static int sumOfFineDust() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != -1) sum += map[i][j];
            }
        }
        return sum;
    }

    private static void workingAirConditioner() {
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int j = 0; j < M - 1; j++) {
            map[0][j] = map[0][j + 1];
        }
        for (int i = 0; i < top; i++) {
            map[i][M - 1] = map[i + 1][M - 1];
        }
        for (int j = M - 1; j > 0; j--) {
            map[top][j] = map[top][j - 1];
        }
        map[top][1] = 0;

        for (int i = bottom + 1; i < N - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int j = 0; j < M - 1; j++) {
            map[N - 1][j] = map[N - 1][j + 1];
        }
        for (int i = N - 1; i > bottom; i--) {
            map[i][M - 1] = map[i - 1][M - 1];
        }
        for (int j = M - 1; j > 0; j--) {
            map[bottom][j] = map[bottom][j - 1];
        }
        map[bottom][1] = 0;
    }

    private static void spreadFineDust() {
        int[][] tmp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;

                int count = 0;
                int quantityOfDust = map[i][j] / 5;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                    if(map[nx][ny] == -1) continue;

                    count += 1;
                    tmp[nx][ny] += quantityOfDust;
                }

                tmp[i][j] -= (quantityOfDust * count);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] += tmp[i][j];
            }
        }
    }
}
