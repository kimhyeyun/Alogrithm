import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1543_문서_검색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();

        int answer = 0;
        int len = word.length();

        int index = 0;
        while (true) {
            if (index + len > doc.length()) break;
            if (doc.charAt(index) != word.charAt(0)) {
                index += 1;
                continue;
            }

            String temp = doc.substring(index, index + len);
            if (temp.equals(word)) {
                answer += 1;
                index += len;
            } else index += 1;
        }

        System.out.println(answer);
    }
}
