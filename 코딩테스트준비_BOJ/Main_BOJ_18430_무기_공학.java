import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18430_무기_공학 {
    static int N, M;
    static int answer;
    static int[][] materials;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MIN_VALUE;

        materials = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                materials[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);

        System.out.println(answer);
    }

    private static void DFS(int index, int sum) {
        if (index == N * M) {
            answer = Math.max(answer, sum);
            return;
        }

        int x = index / M;
        int y = index % M;

        if (!isVisited[x][y]) {
            if(y - 1 >= 0 && x + 1 < N && !isVisited[x][y-1] && !isVisited[x+1][y]){
                isVisited[x][y] = true;
                isVisited[x + 1][y] = true;
                isVisited[x][y - 1] = true;
                DFS(index + 1, sum + materials[x + 1][y] + materials[x][y - 1] + materials[x][y] * 2);
                isVisited[x][y] = false;
                isVisited[x + 1][y] = false;
                isVisited[x][y - 1] = false;
            }

            if (x - 1 >= 0 && y + 1 < M && !isVisited[x - 1][y] && !isVisited[x][y + 1]) {
                isVisited[x][y] = true;
                isVisited[x - 1][y] = true;
                isVisited[x][y + 1] = true;
                DFS(index + 1, sum + materials[x - 1][y] + materials[x][y + 1] + materials[x][y] * 2);
                isVisited[x][y] = false;
                isVisited[x - 1][y] = false;
                isVisited[x][y + 1] = false;
            }

            if (x - 1 >= 0 && y - 1 >= 0 && !isVisited[x - 1][y] && !isVisited[x][y - 1]) {
                isVisited[x][y] = true;
                isVisited[x - 1][y] = true;
                isVisited[x][y - 1] = true;
                DFS(index + 1, sum + materials[x - 1][y] + materials[x][y - 1] + materials[x][y] * 2);
                isVisited[x][y] = false;
                isVisited[x - 1][y] = false;
                isVisited[x][y - 1] = false;
            }

            if (y + 1 < M && x + 1 < N && !isVisited[x][y + 1] && !isVisited[x + 1][y]) {
                isVisited[x][y] = true;
                isVisited[x + 1][y] = true;
                isVisited[x][y + 1] = true;
                DFS(index + 1, sum + materials[x + 1][y] + materials[x][y + 1] + materials[x][y] * 2);
                isVisited[x][y] = false;
                isVisited[x + 1][y] = false;
                isVisited[x][y + 1] = false;
            }
        }

        DFS(index + 1, sum);
    }
}
