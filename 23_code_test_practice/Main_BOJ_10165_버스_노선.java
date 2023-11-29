import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_10165_버스_노선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        int last = 0;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (e < s) {
                last = Math.max(last, e);
                e += N;
            }

            list.add(new int[]{i, s, e});
        }

        Deque<int[]> q = new ArrayDeque<>();
        Collections.sort(list, (o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o2[2] - o1[2];
        });

        for (int[] r : list) {
            if (q.isEmpty() || q.getLast()[2] < r[2]) q.add(r);
        }

        while (!q.isEmpty() && q.getFirst()[2] <= last) q.removeFirst();

        List<Integer> answer = new ArrayList<>();
        while (!q.isEmpty()) {
            answer.add(q.poll()[0]);
        }

        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
