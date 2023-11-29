import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BOJ_2258_정육점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int totalKg = 0;
        int[][] meats = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            meats[i] = new int[]{k, p};

            totalKg += k;
        }

        if (totalKg < M) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(meats, (o1, o2) -> {
            if(o1[1] == o2[1]) return o2[0] - o1[0];
            return o1[1] - o2[1];
        });

        int price = Integer.MAX_VALUE;
        int priceSum = 0, weightSum = 0, bfPrice = 0;
        for (int i = 0; i < N; i++) {
            weightSum += meats[i][0];

            if(bfPrice != meats[i][1]) priceSum = bfPrice = meats[i][1];
            else priceSum += meats[i][1];

            if(weightSum >= M) price = Math.min(price, priceSum);
        }

        System.out.println(price);
    }
}
