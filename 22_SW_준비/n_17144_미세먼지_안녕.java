import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_17144_미세먼지_안녕 {
    static int R, C, T;
    static int[][] space;
    static int top,bottom;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        space = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if(space[i][j] == -1) bottom = i;
            }
        }
        top = bottom - 1;

        while (T-- > 0) {
            spreadFineDust();
//            print();
            workingAirConditioner();
//            print();
        }

        System.out.println(cntOfFineDust());
    }

    private static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(space[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int cntOfFineDust() {
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(space[i][j] == -1) continue;
                answer += space[i][j];
            }
        }
        return answer;
    }

    private static void workingAirConditioner() {
        for (int i = top - 1; i > 0; i--) {
            space[i][0] = space[i - 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            space[0][j] = space[0][j + 1];
        }
        for (int i = 0; i < top; i++) {
            space[i][C - 1] = space[i + 1][C - 1];
        }
        for (int j = C - 1; j > 0; j--) {
            space[top][j] = space[top][j - 1];
        }
        space[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) {
            space[i][0] = space[i + 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            space[R - 1][j] = space[R - 1][j + 1];
        }
        for (int i = R - 1; i > bottom - 1; i--) {
            space[i][C - 1] = space[i - 1][C - 1];
        }
        for (int j = C - 1; j > 0; j--) {
            space[bottom][j] = space[bottom][j - 1];
        }
        space[bottom][1] = 0;
    }

    private static void spreadFineDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp[i][j] += space[i][j];

                if(space[i][j] < 5) continue;

                int fineDust = space[i][j] / 5;
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                    if(space[nx][ny] == -1) continue;

                    tmp[nx][ny] += fineDust;
                    cnt += 1;
                }
                tmp[i][j] -= (fineDust * cnt);
            }
        }
        for (int i = 0; i < R; i++) {
            System.arraycopy(tmp[i], 0, space[i], 0, C);
        }
    }
}
