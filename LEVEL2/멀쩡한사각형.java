public class 멀쩡한사각형 {
    public static void main(String[] args) {
        System.out.println(solution(8, 12));
    }

    public static long solution(int w, int h) {
        long answer = (long)w * (long)h;

        int gcd = gcd(w,h);

        answer -= (w + h - gcd);

        return answer;
    }

    private static int gcd(int w, int h) {
        int a ;
        while(h != 0){
            a = w % h;
            w = h;
            h = a;
        }

        return w;
    }
}
