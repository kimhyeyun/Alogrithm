import java.util.Arrays;
import java.util.PriorityQueue;

public class L2_더_맵게 {
    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville) pq.add(s);

        if(pq.size() < 1) return -1;
        else if (pq.size() == 1) {
            if(pq.peek() >= K) return 0;
            else return -1;
        }

        while (!pq.isEmpty()) {
            int first = pq.poll();
            int second = pq.poll();

            int mix = first + second * 2;
            pq.add(mix);

            answer += 1;

            if(pq.peek() >= K) return answer;
            if(pq.size() < 2) return -1;
        }
        return -1;
    }

    private static boolean isOk(PriorityQueue<Integer> pq, int K) {
        for (int p : pq) {
            if(p < K) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] s = {1,2};
        System.out.println(solution(s, 7));
    }
}
