public class L1_정수_제곱근_판별 {
    public long solution(long n) {
        double x = Math.sqrt(n);

        if(x == (int)x) return (long) Math.pow(x + 1, 2);
        else return -1;
    }
}
