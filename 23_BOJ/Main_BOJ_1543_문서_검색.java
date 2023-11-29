import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1543_문서_검색 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String dic = br.readLine();
        String target = br.readLine();

        int answer = 0;
        int len = target.length();

        for (int i = 0; i < dic.length(); i++) {
            if(dic.charAt(i) != target.charAt(0)) continue;
            if(i + len > dic.length()) break;
            if (target.equals(dic.substring(i, i + len))) {
                i = i + len - 1;
                answer += 1;
            }
        }


        System.out.println(answer);
    }
}
