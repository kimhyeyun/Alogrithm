import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_22233_가희와_키워드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> memos = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String keyword = br.readLine();

            memos.add(keyword);
        }

        for (int i = 0; i < M; i++) {
            String[] keywords = br.readLine().split(",");

            for (String keyword : keywords) {
                memos.remove(keyword);
            }
            System.out.println(memos.size());
        }

    }
}
