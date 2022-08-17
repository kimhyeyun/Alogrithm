import java.util.Arrays;

public class L1_문자열_내림차순_배치하기 {
    public static String solution(String str) {
        char[] arr = str.toCharArray();

        Arrays.sort(arr);

        return new StringBuilder(new String(arr)).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("Zbcdefg"));
    }
}
