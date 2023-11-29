import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_2258_정육점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            list.add(new int[]{m, p});
            total += m;
        }

        if (total < M) {
            System.out.println(-1);
            return;
        }

        list.sort(((o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        }));

        int answer = Integer.MAX_VALUE;
        int priceSum = 0, weightSum = 0;
        for (int i = 0; i < N; i++) {
            weightSum += list.get(i)[0];

            if (i > 0 && list.get(i - 1)[1] == list.get(i)[1]) priceSum += list.get(i)[1];
            else priceSum = list.get(i)[1];

            if (weightSum >= M) answer = Math.min(answer, priceSum);
        }

        System.out.println(answer);
    }
}
