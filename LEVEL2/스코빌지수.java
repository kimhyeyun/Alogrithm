import java.util.PriorityQueue;

public class 스코빌지수 {
    public static void main(String[] args) {
        int[] s = {1,2,3};
        System.out.println(solution(s, 11));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int s : scoville){
            pq.add(s);
        }

        while(pq.peek() < K){
            int one = pq.poll();
            int two = pq.poll();

            pq.add(one + 2*two);
            answer++;

            if(pq.size() < 2 && pq.peek() < K){
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
