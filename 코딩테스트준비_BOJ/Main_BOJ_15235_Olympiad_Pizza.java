import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_15235_Olympiad_Pizza {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] wantPizzas = new int[N + 1];
        int[] answer = new int[N + 1];
        Deque<Integer> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            wantPizzas[i] = Integer.parseInt(st.nextToken());
            q.addLast(i);
        }

        int time = 0;
        while (!q.isEmpty()) {
            time += 1;

            int now = q.pollFirst();
            wantPizzas[now] -= 1;

            if(wantPizzas[now] == 0) answer[now] = time;
            else if(wantPizzas[now] > 0) q.addLast(now);
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}
