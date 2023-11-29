import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1202_보석_도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jeweleries = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jeweleries[i] = new int[]{m, v};
        }

        Arrays.sort(jeweleries, Comparator.comparingInt(o -> o[0]));

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<int[]> puts = new PriorityQueue<>(((o1, o2) -> o2[1] - o1[1]));

        int index = 0;
        long answer = 0;

        for (int i = 0; i < K; i++) {
            int cur = bags[i];

            while (index < N) {
                if (jeweleries[index][0] <= cur) {
                    puts.add(jeweleries[index]);
                    index += 1;
                }else break;
            }

            if(!puts.isEmpty()) answer += puts.poll()[1];
        }
        System.out.println(answer);
    }
}
