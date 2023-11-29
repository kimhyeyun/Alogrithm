import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class 연속된_부분_수열의_합 {

    public int[] solution(int[] sequence, int k) {
        int left = 0, right = 0;
        int sum = sequence[0];
        List<subSequence> subSequences = new ArrayList<>();

        while (true) {
            if (sum == k) {
                subSequences.add(new subSequence(left, right, right - left));
            }
            if(left == sequence.length && right == sequence.length) break;

            if (sum <= k && right < sequence.length) {
                right += 1;
                if (right < sequence.length) sum += sequence[right];
            } else {
                if(left < sequence.length) sum -= sequence[left];
                left += 1;
            }
        }

        Collections.sort(subSequences);

        return new int[]{subSequences.get(0).start, subSequences.get(0).end};
    }

    class subSequence implements Comparable<subSequence>{
        int start, end, len;

        public subSequence(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(subSequence o) {
            if(this.len == o.len) return this.start - o.start;
            return this.len - o.len;
        }
    }

    @Test
    void test() {
        assertArrayEquals(solution(new int[]{1, 2, 3, 4, 5}, 7), new int[]{2, 3});
    }
}
