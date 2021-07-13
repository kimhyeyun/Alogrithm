public class x만큼간격이있는n개의숫자 {
    public static void main(String[] args) {
        long[] ans = solution(-4, 2);

        for(long a: ans){
            System.out.println(a);
        }
    }

    public static long[] solution(long x, int n) {
        long[] answer = new long[n];

        for(int i = 0;i<n;i++){
            answer[i] = x + (x * i);
        }
        return answer;
    }
    
}
