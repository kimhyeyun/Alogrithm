public class 자릿수더하기 {
    public static void main(String[] args) {
        System.out.println(solution(987));   
    }

    public static int solution(int n) {
        int answer = 0;

        while(n != 0){
            answer += n%10;
            n /= 10;
        }

        return answer;
    }
}
