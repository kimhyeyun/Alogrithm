import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] time = new int[100001];
        boolean[] isVisited = new boolean[100001];

        if(N == K) System.out.println(0);
        else {
            Queue<Integer> q = new LinkedList<>();

            q.add(N);
            time[N] = 0;
            isVisited[N] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                if(cur == K){
                    System.out.println(time[K]);
                    return;
                }

                if (cur * 2 < 100001 && !isVisited[2 * cur]) {
                    time[cur * 2] = time[cur];
                    q.add(cur * 2);
                    isVisited[cur * 2] = true;
                }

                if (0 <= cur - 1 && !isVisited[cur - 1]) {
                    time[cur - 1] = time[cur] + 1;
                    q.add(cur - 1);
                    isVisited[cur - 1] = true;
                }

                if (cur + 1 < 100001 && !isVisited[cur + 1]) {
                    time[cur + 1] = time[cur] + 1;
                    q.add(cur + 1);
                    isVisited[cur + 1] = true;
                }
            }

        }
    }
}
