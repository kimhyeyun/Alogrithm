public class L1_평균_구하기 {
    public double solution(int[] arr) {
        double sum = 0;
        for(int a : arr) sum += a;

        return sum / arr.length;
    }
}
