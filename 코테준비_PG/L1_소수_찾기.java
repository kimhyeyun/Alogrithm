public class L1_소수_찾기 {
    public int solution(int n) {
        int[] prime = new int[n + 1];

        for (int i = 1; i <= n; i++) prime[i] = i;

        for (int i = 2; i <= n; i++) {
            if(prime[i] == 0) continue;

            for (int j = i * 2; j <= n; j += i) {
                if(prime[j] == 0) continue;
                else prime[j] = 0;
            }
        }

        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if(prime[i] != 0) answer += 1;
        }
        return answer;

    }
}
