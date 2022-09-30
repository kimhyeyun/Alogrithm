import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n_14502_연구소 {
    static int N, M, answer, numOfEmpty;
    static int[][] laboratory;
    static boolean[][] isSetWall;
    static List<int[]> viruses;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isSetWall = new boolean[N][M];
        laboratory = new int[N][M];
        answer = Integer.MIN_VALUE;
        numOfEmpty = 0;

        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                laboratory[i][j] = Integer.parseInt(st.nextToken());
                if(laboratory[i][j] == 2) viruses.add(new int[]{i, j});
                else if(laboratory[i][j] == 0) numOfEmpty += 1;
            }
        }

        numOfEmpty -= 3;
        setWall(0);

        System.out.println(answer);
    }

    private static void setWall(int count) {
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(isSetWall[i][j] || laboratory[i][j] != 0) continue;

                isSetWall[i][j] = true;
                laboratory[i][j] = 1;
                setWall(count + 1);
                isSetWall[i][j] = false;
                laboratory[i][j] = 0;
            }
        }
    }

    private static void spreadVirus() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) tmp[i] = laboratory[i].clone();

        int numOfVirus = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][M];

        for (int[] virus : viruses) q.add(virus);

        while (!q.isEmpty()) {
            int[] now = q.poll();
            isVisited[now[0]][now[1]] = true;

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(tmp[nx][ny] != 0) continue;

                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                numOfVirus += 1;
                tmp[nx][ny] = 2;
            }
        }

        answer = Math.max(answer, numOfEmpty - numOfVirus);


    }
}
