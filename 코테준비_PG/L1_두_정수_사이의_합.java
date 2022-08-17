public class L1_두_정수_사이의_합 {
    public long solution(int a, int b) {
        long answer = 0L;

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        for (int i = min; i <= max; i++) {
            answer += i;
        }

        if(max == min) return max;
        return answer;
    }
}
