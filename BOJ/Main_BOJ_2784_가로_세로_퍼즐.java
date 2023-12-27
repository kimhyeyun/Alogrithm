import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ_2784_가로_세로_퍼즐 {

    private static List<String> words;
    private static List<List<String>> answer;
    private static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        words = new ArrayList<>();
        isSelected = new boolean[6];
        answer = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            words.add(br.readLine());
        }

        perm(new int[3], 0, 3);

        if (answer.isEmpty()) {
            System.out.println(0);
        } else {
            for (int i = 0; i < 3; i++) {
                System.out.println(answer.get(0).get(i));
            }
        }
    }

    private static void perm(int[] indexes, int count, int depth) {
        if (count == depth) {
            List<String> temp = new ArrayList<>();
            List<String> copy = new ArrayList<>();
            copy.addAll(words);

            for (int i = 0; i < 3; i++) {
                temp.add(words.get(indexes[i]));
                copy.remove(words.get(indexes[i]));
            }

            for (int i = 0; i < 3; i++) {
                String tempStr = "" +
                        temp.get(0).charAt(i) +
                        temp.get(1).charAt(i) +
                        temp.get(2).charAt(i);

                if (copy.contains(tempStr)) copy.remove(tempStr);
                else return;
            }
            answer.add(temp);
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (isSelected[i]) continue;

            isSelected[i] = true;
            indexes[count] = i;
            perm(indexes, count + 1, depth);
            isSelected[i] = false;
        }
    }
}
