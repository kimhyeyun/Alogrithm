import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1911_흙길_보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<int[]> pools = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pools.add(new int[]{start, end});
        }

        pools.sort(((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        }));

        int answer = 0, range = 0;
        for (int i = 0; i < N; i++) {
            if(pools.get(i)[0] > range) range = pools.get(i)[0];
            if (pools.get(i)[1] >= range) {
                while (pools.get(i)[1] > range) {
                    range += L;
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
