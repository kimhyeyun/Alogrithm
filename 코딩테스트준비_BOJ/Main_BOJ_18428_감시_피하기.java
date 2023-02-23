import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_18428_감시_피하기 {
    static int N, studentCount;
    static int[][] map;
    static List<int[]> teachers;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static final int STUDENT = 1, TEACHER = 2, EMTPY = 0, OBSTACLE = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        teachers = new ArrayList<>();
        studentCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String s = st.nextToken();
                if (s.equals("S")) {
                    map[i][j] = STUDENT;
                    studentCount += 1;
                } else if (s.equals("T")) {
                    map[i][j] = TEACHER;
                    teachers.add(new int[]{i, j});
                } else map[i][j] = EMTPY;
            }
        }


        setObstacles(0);

        System.out.println("NO");
    }

    private static void setObstacles(int count) {
        if (count == 3) {
            if (isPossible()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == EMTPY) {
                    map[i][j] = OBSTACLE;
                    setObstacles(count + 1);
                    map[i][j] = EMTPY;
                }
            }
        }
    }

    private static boolean isPossible() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, N);
        }

        for (int[] teacher : teachers) {
            for (int d = 0; d < 4; d++) {
                int nx = teacher[0];
                int ny = teacher[1];

                while (true) {
                    nx += dx[d];
                    ny += dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) break;
                    if(tmp[nx][ny] == OBSTACLE) break;
                    if(tmp[nx][ny] == STUDENT) return false;
                }
            }
        }
        return true;
    }
}
