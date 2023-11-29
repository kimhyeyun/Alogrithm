import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_5014_스타트링크 {
    static int F, S, G, U, D;
    static int[] arr; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[F + 1];

        System.out.println(BFS());
    }

    private static String BFS() {
        Queue<Integer> q = new LinkedList<>();

        q.add(S);
        arr[S] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if(cur == G) return String.valueOf(arr[cur] - 1);

            if (cur + U <= F) {
                if (arr[cur + U] == 0) {
                    arr[cur + U] = arr[cur] + 1;
                    q.add(cur + U);
                }
            }

            if (cur - D > 0) {
                if (arr[cur - D] == 0) {
                    arr[cur - D] = arr[cur] + 1;
                    q.add(cur - D);
                }
            }
        }

        return "use the stairs";
    }
}
