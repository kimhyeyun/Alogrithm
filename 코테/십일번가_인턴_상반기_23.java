import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 십일번가_인턴_상반기_23 {
    public int sol_1(String S){
        int[] letter = new int[26];

        for(char c : S.toCharArray()){
            letter[c - 'a'] += 1;
        }

        int answer = 0;
        for (int i = 0; i < 26; i++) {
            if(letter[i] % 2 == 0) continue;
            answer +=1;
        }
        return answer;
    }

    public int sol_3(String S) {
        int answer = 0;

        String tmp = S.replaceAll("\\?", "");
        int len = S.length();
        if (tmp.isEmpty()) {
            if (len % 2 == 0) return len;
            else return len - 1;
        }

        int start = 0, end = 0, option = 0;
        for (int i = 0; i < S.length() - 1; i++) {
            start = 0;
            end = 0;
            option = 0;

            if (S.charAt(i) == '>') continue;
            else if (S.charAt(i) == '<') start += 1;
            else option += 1;

            for (int j = i + 1; j < S.length(); j++) {
                if (S.charAt(j) == '<') start += 1;
                else if (S.charAt(j) == '?') option += 1;
                else {
                    end += 1;
                    if (end == start && option % 2 == 0) {
                        answer = Math.max(answer, start + end + option);
                    } else if (start == 0 && option == end) {
                        answer = Math.max(answer, option + end);
                    }

                    if(end == start && option == 0)break;

                }
            }
        }

        if (end == 0) {
            if (start > option) answer = Math.max(answer, option + option);
            else answer = Math.max(answer, start + start);
        }
        return answer;
    }

    @Test
    void test() {
      /*  assertEquals(sol_1("acbcbba"), 1);
        assertEquals(sol_1("axxaxa"), 2);
        assertEquals(sol_1("aaaa"), 0);*/

        assertEquals(sol_3("<><??>>"), 4);
        assertEquals(sol_3("??????"), 6);
        assertEquals(sol_3("<<?"), 2);
    }
}
