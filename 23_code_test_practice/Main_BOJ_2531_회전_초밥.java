import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2531_회전_초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        int[] choice = new int[d + 1];

        int left = 0, right = k - 1;
        for (int i = left; i <= right; i++) {
            if(choice[sushi[i]] == 0) count += 1;
            choice[sushi[i]] += 1;
        }

        int answer = Integer.MIN_VALUE;

        if(choice[c] == 0) answer = Math.max(answer, count + 1);
        else answer = Math.max(answer, count);

        while (left < N) {
            choice[sushi[left]] -= 1;
            if(choice[sushi[left]] == 0) count -= 1;

            left += 1;
            right = right + 1 == N ? 0 : right + 1;

            if(choice[sushi[right]] == 0) count += 1;
            choice[sushi[right]] += 1;

            if(choice[c] == 0) answer = Math.max(answer, count + 1);
            else answer = Math.max(answer, count);
        }

        System.out.println(answer);

    }
}
