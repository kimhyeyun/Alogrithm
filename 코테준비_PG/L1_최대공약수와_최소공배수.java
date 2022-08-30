public class L1_최대공약수와_최소공배수 {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];

        answer[0] = getGCD(n, m);
        answer[1] = getLCM(n, m, answer[0]);

        return answer;
    }

    private int getLCM(int n, int m, int g) {
        return (n * m) / g;
    }

    private int getGCD(int n, int m) {
        int g;

        while (m != 0) {
            g = n % m;
            n = m;
            m = g;
        }

        return n;
    }
}
