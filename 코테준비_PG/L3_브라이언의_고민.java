import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class L3_브라이언의_고민 {

    public static String solution(String sentence) {
        StringBuilder answerList = new StringBuilder();

        Map<Character, List<Integer>> specialCharIndex = new LinkedHashMap<>();
        String invalid = "invalid";
        try {
            int size = sentence.length();

            for (int i = 0; i < size; i++) {
                char speicalChar = sentence.charAt(i);

                if (Character.isLowerCase(speicalChar)) {
                    if (!specialCharIndex.containsKey(speicalChar)) {
                        specialCharIndex.put(speicalChar, new ArrayList<>());
                    }
                    specialCharIndex.get(speicalChar).add(i);
                }
            }

            int stringIdx = 0;
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
                        if (tmp.get(i) - tmp.get(i - 1) != 2) {
                            return invalid;
                        }
                    }
                    rule = 1;
                } else if (count == 2) {
                    distance = endSpecial - startSpecial;

                    if (distance == 2 && (endSpecial < endPreSpecial && startSpecial > startPreSpecial)) {
                        rule = 1;
                    } else if (distance >= 2) {
                        rule = 2;
                    } else {
                        return invalid;
                    }
                }

                if (rule == 1) {
                    startWord = startSpecial - 1;
                    endWord = endSpecial + 1;

                    if (startPreWord < startWord && endPreWord > endWord) {
                        if (startSpecial - startPreSpecial == 2 && endPreSpecial - endSpecial == 2) {
                            continue;
                        } else {
                            return invalid;
                        }
                    }
                } else if (rule == 2) {
                    startWord = startSpecial;
                    endWord = endSpecial;

                    if (startPreWord < startWord && endPreWord > endWord) {
                        return invalid;
                    }
                }

                if (endPreWord >= startWord) {
                    return invalid;
                }

                if (stringIdx < startWord) {
                    answerList.append(makeWord(sentence, stringIdx, startWord - 1));
                    answerList.append(" ");
                }

                answerList.append(makeWord(sentence, startWord, endWord));
                answerList.append(" ");

                startPreWord = startWord;
                endPreWord = endWord;
                startPreSpecial = startSpecial;
                endPreSpecial = endSpecial;

                stringIdx = endWord + 1;
            }

            if (stringIdx < size) {
                answerList.append(makeWord(sentence, stringIdx, size - 1));
            }
        } catch (Exception e) {
            return invalid;
        }
        return answerList.toString().trim();
    }

    private static String makeWord(String sentence, int start, int end) {
        String word = sentence.substring(start, end + 1);
        return word.replaceAll("[a-z]", "");
    }

    @Test
    public void test() {
        assertEquals("HELLO WORLD", solution("HaEaLaLaObWORLDb"));
        assertEquals("SIGONG J O A", solution("SpIpGpOpNpGJqOqA"));
        assertEquals("invalid", solution("AxAxAxAoBoBoB"));
        assertEquals("I AM",solution("aIaAM"));
    }
}
