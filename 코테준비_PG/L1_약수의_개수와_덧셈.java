public class L1_약수의_개수와_덧셈 {
    public static int solution(int left, int right) {
        int cnt = 0;
        int answer = 0;
        for (int i = left; i <= right; i++) {
            cnt = 0;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if(i % j == 0) cnt += 2;
                if(j *j == i) cnt -= 1;
            }

            System.out.println(i +" " + cnt);
            if(cnt % 2 == 0) answer += i;
            else answer -= i;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(13, 17));
    }
}
