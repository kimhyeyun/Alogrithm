import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18186_라면_사기_Large {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        int[] ramyeon = new int[N + 2];
        long sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ramyeon[i] = Integer.parseInt(st.nextToken());
            sum += ramyeon[i];
        }

        long answer = 0;

        // 1 -> B, 2 -> B+C, 3 -> B+2C
        if (B <= C) {
            System.out.println(sum * B);
            return;
        }

        for (int i = 0; i < N; i++) {
            int first = i, second = i + 1, third = i + 2;

            if (i + 2 < N) {
                if (ramyeon[second] > ramyeon[third]) {
                    int diff = Math.min(ramyeon[second] - ramyeon[third], ramyeon[first]);
                    answer += diff * (B + C);
                    ramyeon[first] -= diff;
                    ramyeon[second] -= diff;
                }

                int diff = Math.min(ramyeon[first], Math.min(ramyeon[second], ramyeon[third]));
                ramyeon[first] -= diff;
                ramyeon[second] -= diff;
                ramyeon[third] -= diff;

                answer += diff * (B + 2 * C);
                answer += ramyeon[first] * B;
            } else if (i + 1 < N) {
                int diff = Math.min(ramyeon[first], ramyeon[second]);
                ramyeon[first] -= diff;
                ramyeon[second] -= diff;

                answer += diff * (B + C);
                answer += ramyeon[first] * B;
            } else answer += ramyeon[first] * B;
        }
        System.out.println(answer);
    }

}
