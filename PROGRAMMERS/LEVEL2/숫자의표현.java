public class 숫자의표현 {

    public static int solution(int n) {
        int answer = 0;

        for(int i = 1 ; i <= n ; i++){
            int naturalNum = i;
            int sum = naturalNum;

            while(true){

                if(sum == n){
                    answer++;
                    break;
                }
                else if(sum > n)
                    break;
                
                naturalNum++;
                sum += naturalNum;
                    
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(15));
    }
}
