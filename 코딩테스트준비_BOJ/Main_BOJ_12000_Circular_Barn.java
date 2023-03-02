import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12000_Circular_Barn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cows = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }


        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) { // 외부 문을 여는 칸
            int temp = 0;
            for (int j = 1; j <= N; j++) { // 이동해야 하는 칸
                if(j >= i) temp += (j - i) * cows[j];
                else temp += (j + N - i) * cows[j];
            }

            answer = answer < temp ? answer : temp;
        }
        System.out.println(answer);
    }
}
