import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18429_근손실 {
    static int N, K;
    static int answer;
    static int[] kits;
    static boolean[] isUsed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        answer = 0;

        kits = new int[N];
        isUsed = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 500);

        System.out.println(answer);
    }

    private static void DFS(int idx, int weight) {
        if (idx == N) {
            answer += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            if(isUsed[i] || (weight + kits[i] - K) < 500) continue;

            isUsed[i] = true;
            DFS(idx + 1, weight + kits[i] - K);
            isUsed[i] = false;
        }
    }

}
