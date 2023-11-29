import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;

public class Main_BOJ_2478_자물쇠 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        List<Integer> q = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            q.add(arr[i]);
        }

        List<int[]> candidates = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            q.add(0, q.get(q.size() - 1));
            q.remove(q.size() - 1);
            List<Integer> tmp = new ArrayList<>();

            for (int j = 1; j < N; j++) {
                int t = Math.abs(q.get(j) - q.get(j - 1));
                if (t != 1 && t != N - 1) tmp.add(j);
            }

            if (tmp.size() == 2) candidates.add(new int[]{tmp.get(0) + 1, tmp.get(1), i});
        }

        int time = -1;
        for (int[] candidate : candidates) {
            q = Arrays.stream(arr).boxed().collect(Collectors.toList());

            for (int t = 0; t < candidate[2]; t++) {
                q.add(0, q.get(q.size() - 1));
                q.remove(q.size() - 1);
            }

            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < candidate[0] - 1; i++) {
                tmp.add(q.get(i));
            }
            for (int i = candidate[1] - 1; i >= candidate[0] - 1; i--) {
                tmp.add(q.get(i));
            }
            for (int i = candidate[1]; i < N; i++) {
                tmp.add(q.get(i));
            }

            time = -1;
            for (int i = 0; i < N - 1; i++) {
                tmp.add(0, tmp.get(tmp.size() - 1));
                tmp.remove(tmp.size() - 1);

                if (tmp.get(0) == 1 && tmp.get(tmp.size() - 1) == N) {
                    time = i + 1;
                    break;
                }
            }

            if (time > 0) {
                System.out.println(time);
                System.out.println(candidate[0] + " " + candidate[1]);
                System.out.println(candidate[2]);
                break;
            }
        }

        if (time == -1) {
            for (int i = 1; i < N; i++) {
                q.add(0, q.get(q.size() - 1));
                q.remove(q.size() - 1);

                if (q.get(0) == N && q.get(q.size() - 1) == 1) {
                    time = i;
                }
            }
            System.out.println(1);
            System.out.println("1 " + N);
            System.out.println(time);
        }
    }
}