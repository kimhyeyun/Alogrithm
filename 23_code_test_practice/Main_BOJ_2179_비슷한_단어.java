import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main_BOJ_2179_비슷한_단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<String> words = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String w = br.readLine();
            if(!words.contains(w)) words.add(w);
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            String str1 = words.get(i);
            for (int j = i + 1; j < N; j++) {
                String str2 = words.get(j);

                int count = 0;
                int size = Math.min(str1.length(), str2.length());
                for (int k = 0; k < size; k++) {
                    if (str1.charAt(k) == str2.charAt(k)) count += 1;
                    else break;
                }

                if (max < count) {
                    sb = new StringBuilder(str1).append("\n").append(str2);
                    max = count;
                }
            }
        }
        System.out.println(sb);
    }
}
