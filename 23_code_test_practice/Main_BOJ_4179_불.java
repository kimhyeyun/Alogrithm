import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_4179_불 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<int[]> jihoon = new LinkedList<>();
        Queue<int[]> fires = new LinkedList<>();
        char[][] maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(maze[i][j] == 'J') jihoon.add(new int[]{i, j, 0});
                else if(maze[i][j] == 'F') fires.add(new int[]{i, j, 0});
            }
        }

        int answer = BFS(maze, jihoon, fires, N, M);
        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);

    }

    private static int BFS(char[][] maze, Queue<int[]> jihoon, Queue<int[]> fires, int N, int M) {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, -1, 1, 0};

        while (!jihoon.isEmpty()) {
            int size = fires.size();

            for (int i = 0; i < size; i++) {
                int[] cur = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                    if(maze[nx][ny] == 'F' || maze[nx][ny] == '#') continue;

                    maze[nx][ny] = 'F';
                    fires.add(new int[]{nx, ny, cur[2] + 1});
                }
            }

            size = jihoon.size();
            for (int i = 0; i < size; i++) {
                int[] cur = jihoon.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || M <= ny) return cur[2] + 1;
                    if(maze[nx][ny] == 'F' || maze[nx][ny] == '#' || maze[nx][ny] == 'J') continue;

                    maze[nx][ny] = 'J';
                    jihoon.add(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return -1;
    }
}
