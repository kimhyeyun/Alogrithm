import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 상담원_인원 {

    int answer;
    int consultantCount;
    int[][] requestList;
    public int solution(int k, int n, int[][] reqs) {
        answer = Integer.MAX_VALUE;
        consultantCount = k;
        requestList = reqs;

        comb(0, n - 1, k - 1, new boolean[n - 1]);

        return answer;
    }

    private void comb(int start, int n, int r, boolean[] ary) {
        if (r == 0) {
            int[] consultantList = new int[consultantCount];
            Arrays.fill(consultantList, 1);

            int index = 0;
            for (int i = 0; i < ary.length; i++) {
                if (ary[i]) {
                    index += 1;
                    continue;
                }

                consultantList[index] += 1;
            }

            measureTime(consultantList);
        }

        for (int i = start; i < n; i++) {
            ary[i] = true;
            comb(i + 1, n, r - 1, ary);
            ary[i] = false;
        }
    }

    private void measureTime(int[] consultantList) {
        int waitTime = 0;
        PriorityQueue<Integer>[] pq = new PriorityQueue[consultantList.length];
        for (int i = 0; i < consultantList.length; i++) {
            pq[i] = new PriorityQueue<>();
        }

        for (int[] req : requestList) {
            int start = req[0];
            int end = req[1];
            int type = req[2] - 1;

            if (pq[type].size() < consultantList[type]) {
                pq[type].offer(start + end);
                continue;
            }

            int next = pq[type].poll();
            if (next > start) {
                waitTime += next - start;
                pq[type].offer(next + end);
            } else {
                pq[type].offer(start + end);
            }
        }

        answer = Math.min(answer, waitTime);
    }


    @Test
    void test() {
        solution(3, 5, new int[][]{{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}});
    }
}
