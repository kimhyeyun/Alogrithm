public class 다음큰숫자 {

    public static int solution(int n) {
        int answer = 0;
        int oneOriginal = Integer.bitCount(n);
        int num = n + 1;

        while(true){
            if(oneOriginal == Integer.bitCount(num)){
                answer = num;
                break;
            }
            num++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(78));
    }
}
