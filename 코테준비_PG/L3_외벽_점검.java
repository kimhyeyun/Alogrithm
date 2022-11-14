import java.util.List;

public class L3_외벽_점검 {
    int n, answer;
    int[] weak, dist;
    int[][] weakCases;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        answer = Integer.MAX_VALUE;

        weakCases = new int[weak.length][weak.length];

        makeWeakCases();
        makeDistCases(new boolean[dist.length], new int[dist.length], 0);

        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private void makeDistCases(boolean[] isVisited, int[] distCase, int index) {
        if (index == dist.length) {
            for (int[] weakCase : weakCases) {
                check(distCase, weakCase);
            }
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                distCase[index] = dist[i];
                makeDistCases(isVisited, distCase, index + 1);
                distCase[index] = 0;
                isVisited[i] = false;
            }
        }
    }

    private void check(int[] distCase, int[] weakCase) {
        int cur = 0;
        int index = 0;
        int next;
        while (cur < weakCase.length && index < distCase.length) {
            next = cur + 1;
            while (next < weakCase.length && weakCase[cur] + distCase[index] >= weakCase[next]) {
                next += 1;
            }
            cur = next;
            index += 1;
        }

        if (cur == weakCase.length && index < answer) {
            answer = index;
        }
    }

    private void makeWeakCases() {
        int[] weakCase = this.weak.clone();
        weakCases[0] = weakCase.clone();
        for (int i = 1; i < weak.length; i++) {
            int temp = weakCase[0];
            for (int j = 1; j < weak.length; j++) {
                weakCase[j - 1] = weakCase[j];
            }
            weakCase[weak.length - 1] = temp + n;
            weakCases[i] = weakCase.clone();
        }
    }


}
