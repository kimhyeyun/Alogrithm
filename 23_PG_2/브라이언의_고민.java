import org.junit.jupiter.api.Test;

import java.util.*;

public class 브라이언의_고민 {
    public static String solution(String sentence) {
        StringBuilder sb = new StringBuilder();

        Map<Character, List<Integer>> specialCharIndex = new LinkedHashMap<>();
        String invalid = "invalid";

        try {
            int size = sentence.length();

            for (int i = 0; i < size; i++) {
                char specialChar = sentence.charAt(i);

                if (Character.isLowerCase(specialChar)) {
                    if (!specialCharIndex.containsKey(specialChar)) {
                        specialCharIndex.put(specialChar, new ArrayList<>());
                    }
                    specialCharIndex.get(specialChar).add(i);
                }
            }

            int stringIndex = 0;
            int count = 0, rule = 0, distance = 0;
            int startWord = 0, endWord = 0;
            int startPreWord = -1, endPreWord = -1;
            int startSpecial, endSpecial;
            int startPreSpecial = -1, endPreSpecial = -1;

            List<Integer> tmp;

            for (char c : specialCharIndex.keySet()) {
                tmp = specialCharIndex.get(c);

                count = tmp.size();
                startSpecial = tmp.get(0);
                endSpecial = tmp.get(count - 1);

                if (count == 1 || count >= 3) {
                    for (int i = 1; i < count; i++) {
                        if(tmp.get(i) - tmp.get(i-1) != 2) return invalid;
                    }
                    rule = 1;
                } else if (count == 2) {
                    distance = endSpecial - startSpecial;

                    if(distance == 2 && (endSpecial < endPreSpecial && startSpecial > startPreSpecial)) rule = 1;
                    else if(distance >= 2) rule = 2;
                    else return invalid;
                }

                if (rule == 1) {
                    startWord = startSpecial - 1;
                    endWord = endSpecial + 1;

                    if (startPreWord < startWord && endWord < endPreWord) {
                        if(startSpecial - startPreSpecial == 2 && endPreSpecial - endSpecial == 2) continue;
                        else return invalid;
                    }
                } else if (rule == 2) {
                    startWord = startSpecial;
                    endWord = endSpecial;

                    if(startPreWord < startWord && endWord < endPreWord) return invalid;
                }

                if (endPreWord >= startWord) return invalid;
                if (stringIndex < startWord) {
                    sb.append(makeWord(sentence, stringIndex, startWord - 1));
                    sb.append(" ");
                }

                sb.append(makeWord(sentence, startWord, endWord));
                sb.append(" ");

                startPreWord = startWord;
                endPreWord = endWord;
                startPreSpecial = startSpecial;
                endPreSpecial = endSpecial;

                stringIndex = endWord + 1;
            }

            if(stringIndex < size) sb.append(makeWord(sentence, stringIndex, size - 1));

        }catch (Exception e) {
            return invalid;
        }

        return sb.toString().trim();
    }

    private static String makeWord(String sentence, int start, int end) {
        String word = sentence.substring(start, end + 1);
        return word.replaceAll("[a-z]", "");
    }

    @Test
    void test() {
        solution("SpIpGpOpNpGJqOqA");
    }
}


