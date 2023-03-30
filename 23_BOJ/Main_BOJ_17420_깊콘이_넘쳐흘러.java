import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BOJ_17420_깊콘이_넘쳐흘러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] giftCards = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            giftCards[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            giftCards[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(giftCards, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        long answer = 0;
        int preMax = giftCards[0][1];
        int curMax = -1;

        for (int i = 0; i < N; i++) {
            if (preMax > giftCards[i][0]) {
                if (preMax < giftCards[i][1]) {
                    preMax = giftCards[i][1];
                }

                int count = ((preMax - giftCards[i][0]) + 29) / 30;
                giftCards[i][0] += (count * 30);

                answer += count;
            }

            curMax = Math.max(curMax, giftCards[i][0]);

            if (i + 1 < N && giftCards[i][1] != giftCards[i + 1][1]) {
                preMax = curMax;
            }
        }
        System.out.println(answer);
    }
}
