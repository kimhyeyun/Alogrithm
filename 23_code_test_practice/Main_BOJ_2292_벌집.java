import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = 1, range = 1;
        while (true) {
            if(N <= range) break;

            range += 6 * answer;
            answer += 1;
        }

        System.out.println(answer);

    }
}
