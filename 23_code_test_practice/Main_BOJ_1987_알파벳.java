import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1987_알파벳 {
    static int R, C, answer;
    static char[][] map;
    static boolean[] visitedAlphabets;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visitedAlphabets = new boolean[26];
        isVisited = new boolean[R][C];

        answer = Integer.MIN_VALUE;

        visitedAlphabets[map[0][0] - 'A'] = true;
        isVisited[0][0] = true;

        DFS(0, 0, 1);

        System.out.println(answer);
    }

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    private static void DFS(int x, int y, int count) {

        answer = Math.max(answer, count);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || R  <= nx || C <= ny) continue;
            if(isVisited[nx][ny] || visitedAlphabets[map[nx][ny] - 'A']) continue;

            isVisited[nx][ny] = true;
            visitedAlphabets[map[nx][ny] - 'A'] = true;

            DFS(nx, ny, count + 1);

            isVisited[nx][ny] = false;
            visitedAlphabets[map[nx][ny] - 'A'] = false;
        }

        return;
    }

}
