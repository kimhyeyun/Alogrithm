import java.util.HashMap;
import java.util.Map;

public class 숫자_문자열과_영단어 {

    static String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static Map<String, Integer> numberStr;

    public static int solution(String s) {
        String ans = "";
        numberStr = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            numberStr.put(str[i], i);
        }

        String n = "";
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) & s.charAt(i) <= '9') {
                ans += s.charAt(i);
            } else {
                n += s.charAt(i);
                if (numberStr.containsKey(n)) {
                    ans += numberStr.get(n);
                    n = "";
                }
            }
        }

        return Integer.parseInt(ans);
    }

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
    }
}
