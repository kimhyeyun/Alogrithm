import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_4179_불 {
    static int N, M, answer;
    static char[][] maze;
    static Queue<int[]> jihoon, fire;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jihoon = new LinkedList<>();
        fire = new LinkedList<>();
        answer = 0;

        maze = new char[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 'J') {
                    jihoon.add(new int[]{i, j, 0});
                } else if (maze[i][j] == 'F') {
                    fire.add(new int[]{i, j, 0});
                }
            }
        }
        if (BFS()) {
            System.out.println(answer);
        } else {
            System.out.println("IMPOSSIBLE");
        }

    }

    private static boolean BFS() {
        while (!jihoon.isEmpty()) {

            int size = fire.size();
            for (int i = 0; i < size; i++) {
                int[] now = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                    if(maze[nx][ny] == 'F' || maze[nx][ny] == '#') continue;

                    maze[nx][ny] = 'F';
                    fire.add(new int[]{nx, ny, now[2] + 1});
                }
            }

            size = jihoon.size();
            for (int i = 0; i < size; i++) {
                int[] now = jihoon.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                        answer = now[2] + 1;
                        return true;
                    }
                    if(maze[nx][ny] == 'F' || maze[nx][ny] == 'J' || maze[nx][ny] == '#') continue;

                    maze[nx][ny] = 'J';
                    jihoon.add(new int[]{nx, ny, now[2] + 1});
                }
            }
        }
        return false;
    }
}
