import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_5585_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = 1000 - Integer.parseInt(br.readLine());
        int answer = 0;

        if (N >= 500) {
            answer += N / 500;
            N %= 500;
        }

        if (N >= 100) {
            answer += N / 100;
            N %= 100;
        }

        if (N >= 50) {
            answer += N / 50;
            N %= 50;
        }

        if (N >= 10) {
            answer += N / 10;
            N %= 10;
        }

        if (N >= 5) {
            answer += N / 5;
            N %= 5;
        }

        System.out.println(answer + N);
    }
}
