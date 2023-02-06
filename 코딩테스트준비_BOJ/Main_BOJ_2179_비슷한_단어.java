import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_BOJ_2179_비슷한_단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> words = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }


        int max = 0;
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                    continue;
                }
                int cnt = 0;
                int size = Math.min(words.get(i).length(), words.get(j).length());

                for (int k = 0; k < size; k++) {
                    if (words.get(i).charAt(k) == words.get(j).charAt(k)) {
                        cnt += 1;
                    } else {
                        break;
                    }
                }

                if (max < cnt) {
                    answer.clear();

                    answer.add(words.get(i));
                    answer.add(words.get(j));

                    max = cnt;
                } else if (max == cnt) {

                }
            }
        }

        System.out.println(answer.get(0));
        System.out.println(answer.get(1));

    }
}
