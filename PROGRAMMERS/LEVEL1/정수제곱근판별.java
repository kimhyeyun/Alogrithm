public class 정수제곱근판별 {
    public static void main(String[] args) {
        System.out.println(solution(121));
    }

    public static long solution(long n) {
        long answer = -1;

        // 내 코드 -> 시간초과
        /* for(long i = 2; i < n; i++){
            if(n % i  == 0){
                if(n / i == i){
                    answer = (long) Math.pow(i+1, 2);
                }
            }
        } */

        int a = (int) Math.sqrt(n);
        double b = Math.sqrt(n);

        if(a == b){
            answer = (long) Math.pow(a+1, 2);
        }

        return answer;
    }

    // 다른 사람 풀이
    public long Sqrt(long n){
        if(Math.pow((int)Math.sqrt(n), 2) == n){
            return (long) Math.pow(Math.sqrt(n)+1, 1);
        }
        return -1;
    }
}
