public class 콜라츠추측 {
    public static void main(String[] args) {
        System.out.println(solution(626331));
    }

    public static int solution(int num) {
        int answer = 0;

        while(num != 1){
            if(num % 2 == 0)
                num /= 2;
            
            else if(num % 2 == 1)
                num = num*3+1;

            answer++;

            if(answer == 500){
                answer = -1;
                break;
            }
                
        }
        return answer;
    }
}
