import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1027_고층_건물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            double prev = 0;

            for (int j = i - 1; j >= 0; j--) {
                double tmp = (double) (buildings[j] - buildings[i]) / (double) (j - i);

                if (prev > tmp || j == i - 1) {
                    count += 1;
                    prev = tmp;
                }
            }
            prev = 0;

            for (int j = i + 1; j < N; j++) {
                double tmp = (double) (buildings[j] - buildings[i]) / (double) (j - i);

                if (prev < tmp || j == i + 1) {
                    count += 1;
                    prev = tmp;
                }
            }

            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}
