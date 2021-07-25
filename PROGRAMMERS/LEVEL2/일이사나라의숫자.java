public class 일이사나라의숫자 {
    public static void main(String[] args) {
        System.out.println(solution(10));
    }

    /* 
        1, 2, 4, 11, 12, 14, 21, 22, 24, 41 ....
    */
    public static String solution(int n) {
        String answer = "";

        while(n > 0){
            if(n%3 == 0){
                answer = "4"+answer;
                n = n/3 - 1;
            }
            else{
                answer = String.valueOf(n%3) + answer;
                n /= 3;
            }
        }

        return answer;
    }
}
