import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2632_피자판매 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int wantSize = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] aPizza = new int[m];
        int maxAPizza = 0;
        for (int i = 0; i < m; i++) {
            aPizza[i] = Integer.parseInt(br.readLine());
            maxAPizza += aPizza[i];
        }

        int[] bPizza = new int[n];
        int maxBPizza = 0;
        for (int i = 0; i < n; i++) {
            bPizza[i] = Integer.parseInt(br.readLine());
            maxBPizza += bPizza[i];
        }

        int[] aCount = new int[Math.max(maxAPizza, wantSize) + 1];
        aCount[0] = 1;
        aCount[maxAPizza] = 1;
        count(aPizza, aCount, wantSize);

        int[] bCount = new int[Math.max(maxBPizza, wantSize) + 1];
        bCount[0] = 1;
        bCount[maxBPizza] = 1;
        count(bPizza, bCount, wantSize);

        int answer = 0;
        for (int i = 0; i <= wantSize; i++) {
            answer += (aCount[i] * bCount[wantSize - i]);
        }
        System.out.println(answer);
    }

    private static void count(int[] pizza, int[] count, int wantSize) {
        for (int i = 0; i < pizza.length; i++) {
            int sum = 0;
            for (int j = 1; j < pizza.length; j++) {
                int tmpSum = pizza[(i + j) % pizza.length];
                if(sum + tmpSum > wantSize) break;
                sum += tmpSum;
                count[sum] += 1;
            }
        }
    }
}
