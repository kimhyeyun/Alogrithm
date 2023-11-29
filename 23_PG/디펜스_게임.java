import java.util.PriorityQueue;

public class 디펜스_게임 {

    public int solution(int n, int k, int[] enemy) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int round = 0; round < enemy.length; round++) {
            pq.add(enemy[round]);

            if (pq.size() > k) n -= pq.poll();
            if(n < 0) return round;
        }

        return enemy.length;
    }

}
