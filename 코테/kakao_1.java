import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kakao_1 {
    public int solution(String[] friends, String[] gifts) {

        int len = friends.length;
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            index.put(friends[i], i);
        }

        int[][] countOfGift = new int[len][len];
        int[] getCount = new int[len];
        int[] giveCount = new int[len];
        for (String gift : gifts) {
            String[] s = gift.split(" ");

            countOfGift[index.get(s[0])][index.get(s[1])] += 1;

            getCount[index.get(s[1])] += 1;
            giveCount[index.get(s[0])] += 1;
        }

        int[] reg = new int[len];
        for (int i = 0; i < len; i++) {
            reg[i] = giveCount[i] - getCount[i];
        }

        int[] next = new int[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                if (countOfGift[i][j] > countOfGift[j][i]){
                    next[i] += 1;
                }
                if (countOfGift[i][j] ==  countOfGift[j][i]) {

                    if (reg[i] > reg[j]) next[i] += 1;
                }
            }
        }

        Arrays.sort(next);

        return next[next.length - 1];
    }

    @Test
    void test() {
//        solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
        solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"});
    }
}


