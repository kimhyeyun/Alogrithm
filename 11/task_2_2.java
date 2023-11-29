public class task_2_2 {
    int answer;
    public int solution(int N, int K) {
        // write your code in Java SE 8
        answer = Integer.MAX_VALUE;
        pickGlasses(N, K, N, 0, 0, new boolean[N + 1]);

        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private void pickGlasses(int N, int K, int idx, int cnt, int sum, boolean[] isPicked) {
        if (sum == K) {
            answer = Math.min(answer, cnt);
            return;
        }else if(sum > K) return;

        for (int i = idx; i > 0; i--) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            pickGlasses(N, K, i - 1, cnt + 1, sum + i, isPicked);
            isPicked[i] = false;
        }
    }
}
