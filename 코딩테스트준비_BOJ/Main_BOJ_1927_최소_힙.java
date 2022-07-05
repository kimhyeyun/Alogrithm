import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_1927_최소_힙 {
    static int N, x;
    static PriorityQueue<Integer> q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        q = new PriorityQueue<>();
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            x = Integer.parseInt(br.readLine());
            if(x == 0) printMinValue();
            else addValue(x);
        }
        System.out.println(sb);
    }

    private static void addValue(int x) {
        q.add(x);

        return;
    }

    private static void printMinValue() {
        if(q.isEmpty()) sb.append(0).append("\n");
        else sb.append(q.poll()).append("\n");

        return;
    }
}
