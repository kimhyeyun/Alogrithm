public class L1_x만큼_간격이_있는_n개의_숫자 {
    public long[] solution(long x, int n) {
        long[] answer = new long[n];
        for (int i = 1; i <= n; i++) {
            answer[i - 1] = x * i;
        }

        return answer;
    }
}
