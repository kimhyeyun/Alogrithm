import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1781_컵라면 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> problems = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            problems.add(new int[]{i, x, y});
        }

        PriorityQueue<Integer> result = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int[] p = problems.poll();
            result.add(p[2]);

            if (p[1] < result.size()) result.poll();
        }

        int answer = 0;
        while (!result.isEmpty()) answer += result.poll();

        System.out.println(answer);
    }
}
