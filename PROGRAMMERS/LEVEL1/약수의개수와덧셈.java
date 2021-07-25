public class 약수의개수와덧셈 {
    public static void main(String[] args) {
        System.out.println(solution(24,27));
    }
    public static int solution(int left, int right) {
        int answer = 0;
        for(int i = left; i <= right; i++){
            int num = i;
            int cnt = 0;
            for(int j = 1;j<=num;j++){
                if(num%j == 0)
                    cnt++;
            }
            if(cnt % 2 == 0)
                answer += num;
            else
                answer -= num;
        }
        return answer;
    }
}
