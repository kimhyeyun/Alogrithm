import java.util.HashMap;
import java.util.Map;

public class L1_숫자_문자열과_영단어 {
    public static int solution(String s) {
        String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Map<String, Integer> numToStr = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            numToStr.put(str[i], i);
        }

        String ans = "";
        String string = "";
        for (char c : s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                ans += c;
            } else {
                string += c;
                if (numToStr.containsKey(string)) {
                    ans += numToStr.get(string);
                    string = "";
                }
            }
        }

        return Integer.parseInt(ans);
    }

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"	));
    }
}
