import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_5585_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = Integer.parseInt(br.readLine());
        money = 1000 - money;

        int[] monies = {500, 100, 50, 10, 5, 1};

        int answer = 0;
        for (int i = 0; i < monies.length; i++) {
            if(money == 0) break;

            if (money >= monies[i]) {
                int n = money / monies[i];
                answer += n;
                money -= (monies[i] * n);
            }
        }

        System.out.println(answer);
    }
}
