import java.util.Arrays;

public class L1_정수_내림차순으로_배치하기 {
    public static long solution(long n) {
        char[] strArr = String.valueOf(n).toCharArray();

        Arrays.sort(strArr);

        StringBuilder sb = new StringBuilder(new String(strArr));
        String str = sb.reverse().toString();
        long answer = Long.parseLong(str);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(118372));
    }
}
