import java.util.LinkedList;
import java.util.Queue;

public class 두_큐_합_같게_만들기 {

    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sumOfQ1 = 0, sumOfQ2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sumOfQ1 += queue1[i];

            q2.add(queue2[i]);
            sumOfQ2 += queue2[i];
        }

        long sum = sumOfQ1 + sumOfQ2;
        if(sum % 2 != 0) return -1;

        sum /= 2;
        int limit = queue1.length * 2;
        int count1 = 0, count2 = 0;

        while (count1 <= limit && count2 <= limit) {
            if(sumOfQ1 == sumOfQ2) return count1 + count2;
            if (sumOfQ1 < sumOfQ2) {
                sumOfQ2 -= q2.peek();
                sumOfQ1 += q2.peek();
                q1.add(q2.poll());
                count2 += 1;
            } else {
                sumOfQ1 -= q1.peek();
                sumOfQ2 += q1.peek();
                q2.add(q1.poll());
                count1 += 1;
            }
        }
        return -1;
    }
}
