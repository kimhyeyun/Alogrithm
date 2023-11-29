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

        List<int[]> pool = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pool.add(new int[]{s, e});
        }

        Collections.sort(pool, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int answer = 0;
        int range = 0;

        for (int i = 0; i < N; i++) {
            if(pool.get(i)[0] > range) range = pool.get(i)[0];
            if (pool.get(i)[1] >= range) {
                while (pool.get(i)[1] > range) {
                    range += L;
                    answer += 1;
                }
            }
        }

        System.out.println(answer);

    }
}
