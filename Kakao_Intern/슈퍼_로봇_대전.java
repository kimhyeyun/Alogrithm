import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 슈퍼_로봇_대전 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[][] robots = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int win = Integer.parseInt(stringTokenizer.nextToken());
            int lose = Integer.parseInt(stringTokenizer.nextToken());

            robots[win][lose] = 1;
            robots[lose][win] = -1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(i == k) continue;

                for (int j = 1; j <= N; j++) {
                    if(i == j || j == k) continue;

                    if(robots[i][k] == 1 && robots[k][j] == 1)
                        robots[i][j] = 1;

                    else if(robots[i][k] == -1 && robots[k][j] == -1)
                        robots[i][j] = -1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            boolean flag = false;
            for (int j = 1; j <= N; j++) {
                if(i == j) continue;

                if (robots[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
            if(!flag) ans += 1;
        }

        System.out.println(ans);
    }
}
