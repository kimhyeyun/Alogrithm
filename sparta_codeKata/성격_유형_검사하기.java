import java.util.*;

public class 성격_유형_검사하기 {

    public static String solution(String[] survey, int[] choices) {
        String answer = "";

        char[][] types = new char[][]{{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int[][] scores = new int[4][2];
        Map<Character, int[]> indexes = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                indexes.put(types[i][j], new int[]{i, j});
            }
        }

        for (int i = 0; i < choices.length; i++) {
            char left = survey[i].charAt(0);
            char right = survey[i].charAt(1);
            int choice = choices[i];

            int score = 0;
            int[] index = new int[2];

            switch (choice) {
                case 1 -> {
                    score = 3;
                    index = indexes.get(left);
                }
                case 2 -> {
                    score = 2;
                    index = indexes.get(left);
                }
                case 3 -> {
                    score = 1;
                    index = indexes.get(left);
                }
                case 5 -> {
                    score = 1;
                    index = indexes.get(right);
                }
                case 6 -> {
                    score = 2;
                    index = indexes.get(right);
                }
                case 7 -> {
                    score = 3;
                    index = indexes.get(right);
                }
            }
            scores[index[0]][index[1]] += score;
        }

        for (int i = 0; i < 4; i++) {
            if (scores[i][0] >= scores[i][1]) {
                answer += types[i][0];
            } else {
                answer += types[i][1];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
        System.out.println(solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3}));
    }
}
