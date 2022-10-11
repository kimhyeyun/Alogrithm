import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_20057_마법사_상어와_토네이도 {
    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] percent = {2, 10, 7, 1, 5, 10, 7, 1, 2};
    static int[][] dxOfP = {{-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, 1, 0, -1, 2, 1, 0, -1, 0}, {-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, -1, 0, 1, -2, -1, 0, 1, 0}};
    static int[][] dyOfP = {{0, -1, 0, 1, -2, -1, 0, 1, 0}, {-2, -1, -1, -1, 0, 1, 1, 1, 2}, {0, 1, 0, -1, 2, 1, 0, -1, 0}, {2, 1, 1, 1, 0, -1, -1, -1, -2}};
    static int[] numberOfMove = {1, 1, 2, 2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(moveTornado(N / 2, N / 2));

    }

    private static int moveTornado(int x, int y) {
        int answer = 0;
        int now[] = new int[]{x, y};

        while (true) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < numberOfMove[d]; i++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) return answer;

                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int totalOfSpread = 0;

                    for (int s = 0; s < 9; s++) {
                        int sandX = nx + dxOfP[d][s];
                        int sandY = ny + dyOfP[d][s];
                        int spread = (sand * percent[s]) / 100;

                        if(sandX < 0 || sandY < 0 || N <= sandX || N <= sandY) answer += spread;
                        else map[sandX][sandY] += spread;

                        totalOfSpread += spread;
                    }

                    int alphaX = nx + dx[d];
                    int alphaY = ny + dy[d];
                    int sandOfAlpha = sand - totalOfSpread;

                    if(alphaX < 0 || alphaY < 0 || N <= alphaX || N <= alphaY) answer += sandOfAlpha;
                    else map[alphaX][alphaY] += sandOfAlpha;

                    now = new int[]{nx, ny};
                }
            }
            for (int i = 0; i < 4; i++) {
                numberOfMove[i] += 2;
            }
        }

    }
}
