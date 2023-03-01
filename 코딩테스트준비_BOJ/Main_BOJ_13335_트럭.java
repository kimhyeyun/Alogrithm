import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13335_트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> trucks = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        int weight = 0;
        Deque<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        while (!bridge.isEmpty()) {
            time += 1;
            weight -= bridge.pollFirst();

            if (!trucks.isEmpty()) {
                if (trucks.peek() + weight <= L) {
                    weight += trucks.peek();
                    bridge.addLast(trucks.poll());
                } else {
                    bridge.addLast(0);
                }
            }
        }
        System.out.println(time);
    }
}
