import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1461_도서관 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Queue<Integer> pos = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> neg = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (n < 0) neg.offer(Math.abs(n));
            else pos.offer(n);
        }

        int max = 0;
        if(pos.isEmpty()) max = neg.peek();
        else if(neg.isEmpty()) max = pos.peek();
        else max = Math.max(pos.peek(), neg.peek());

        int answer = 0;

        while (!pos.isEmpty()) {
            int t = pos.poll();
            for (int i = 0; i < M - 1; i++) {
                pos.poll();

                if(pos.isEmpty()) break;
            }

            answer += t * 2;
        }

        while (!neg.isEmpty()) {
            int t = neg.poll();
            for (int i = 0; i < M - 1; i++) {
                neg.poll();

                if(neg.isEmpty()) break;
            }

            answer += t * 2;
        }

        answer -= max;

        System.out.println(answer);
    }
}
