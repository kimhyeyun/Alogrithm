public class L1_3진법_뒤집기 {
    public static int solution(int n) {
        String trit = Integer.toString(n, 3);

        StringBuilder sb = new StringBuilder(trit);
        trit = sb.reverse().toString();

        return Integer.parseInt(trit, 3);
    }

    public static void main(String[] args) {
        System.out.println(solution(45));
    }

}
