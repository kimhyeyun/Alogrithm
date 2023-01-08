import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2531_회전_초밥 {
    static int N, d, K, c;
    static int[] sushi, isEated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        sushi = new int[N];
        isEated = new int[d + 1];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < K; i++) {
            if (isEated[sushi[i]] == 0) {
                count += 1;
            }
            isEated[sushi[i]] += 1;
        }

        int answer = count;
        for (int i = 1; i < N; i++) {
            if (answer <= count) {
                if (isEated[c] == 0) {
                    answer = count + 1;
                } else {
                    answer = count;
                }
            }

            int end = (i + K - 1) % N;
            if (isEated[sushi[end]] == 0) {
                count += 1;
            }
            isEated[sushi[end]] += 1;

            isEated[sushi[i - 1]] -= 1;
            if (isEated[sushi[i - 1]] == 0) {
                count -= 1;
            }
        }

        System.out.println(answer);
    }
}
