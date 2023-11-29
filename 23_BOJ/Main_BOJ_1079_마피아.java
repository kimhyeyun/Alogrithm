import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1079_마피아 {
    static int N, eunjin, answer;
    static int[] guiltiness;
    static int[][] R;
    static boolean[] isAlived;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        guiltiness = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guiltiness[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        eunjin = Integer.parseInt(br.readLine());
        isAlived = new boolean[N];
        Arrays.fill(isAlived, true);

        answer = Integer.MIN_VALUE;

        play(N, 0);

        System.out.println(answer);

    }

    private static void play(int count, int day) {
        if (!isAlived[eunjin] || count == 1) {
            answer = Math.max(answer, day);
            return;
        }

        if (count % 2 == 0) {
            //밤
            for (int i = 0; i < N; i++) {
                if(!isAlived[i] || i == eunjin) continue;

                for (int j = 0; j < N; j++) {
                    if(!isAlived[j]) continue;
                    guiltiness[j] += R[i][j];
                }

                isAlived[i] = false;
                play(count - 1, day + 1);
                isAlived[i] = true;

                for (int j = 0; j < N; j++) {
                    if(!isAlived[j]) continue;
                    guiltiness[j] -= R[i][j];
                }
            }
        } else {
            // 낮
            int max = 0, index = N - 1;
            for (int i = 0; i < N; i++) {
                if (isAlived[i] && max < guiltiness[i]) {
                    max = guiltiness[i];
                    index = i;
                } else if (isAlived[i] && max == guiltiness[i]) {
                    max = guiltiness[i];
                    index = Math.min(index, i);
                }
            }

            isAlived[index] = false;
            play(count - 1, day);
            isAlived[index] = true;
        }
    }
}
