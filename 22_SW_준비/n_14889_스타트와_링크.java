import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class n_14889_스타트와_링크 {
    static int N, answer;
    static int[][] abilities;
    static int[] start, link;
    static boolean[] isPicked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());

        abilities = new int[N + 1][N + 1];
        start = new int[N / 2];
        link = new int[N / 2];
        isPicked = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, 1);
        System.out.println(answer);
    }

    private static void solution(int cnt, int idx) {
        if (cnt == N / 2) {
            int arrIdx = 0;
            int startSum = 0;
            int linkSum = 0;
            for (int i = 1; i <= N; i++) {
                if(!isPicked[i]) link[arrIdx++] = i;
            }

            for (int i = 0; i < start.length; i++) {
                for (int j = i + 1; j < start.length; j++) {
                    if(i == j) continue;
                    startSum += (abilities[start[i]][start[j]] + abilities[start[j]][start[i]]);
                    linkSum += (abilities[link[i]][link[j]] + abilities[link[j]][link[i]]);
                }
            }

            answer = Math.min(answer, Math.abs(startSum - linkSum));
            return;
        }

        for (int i = idx; i <= N; i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            start[cnt] = i;
            solution(cnt + 1, i + 1);
            isPicked[i] = false;
        }
    }
}
