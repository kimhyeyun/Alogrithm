import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1765_닭싸움_팀_정하기 {
    static int N, M;
    static int[][] friends, enemies;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        friends = new int[N + 1][N + 1];
        enemies = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (s.equals("E")) {
                enemies[p][q] = 1;
                enemies[q][p] = 1;
            }else{
                friends[p][q] = 1;
                friends[q][p] = 1;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(i == k) continue;
                for (int j = 1; j <= N; j++) {
                    if(k == j || i == j) continue;

                    if(enemies[i][k] == 1 && enemies[k][j] == 1) friends[i][j] = 1;
                    if(friends[i][k] == 1 && friends[k][j] == 1) friends[i][j] = 1;
                }
            }
        }

        int answer = 0;
        isVisited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i]) {
                makeTeam(i);
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    private static void makeTeam(int start) {
        isVisited[start] = true;

        for (int i = 1; i <= N; i++) {
            if(i == start || isVisited[i]) continue;
            if(friends[start][i] == 1) isVisited[i] = true;
        }
        return;
    }
}
