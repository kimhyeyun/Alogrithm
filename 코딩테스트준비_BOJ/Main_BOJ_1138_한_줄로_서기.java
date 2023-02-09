import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1138_한_줄로_서기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] tall = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tall[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = N; i >= 1; i--) {
            answer.add(tall[i], i);
        }

        for (int a : answer) {
            System.out.print(a + " ");
        }

        System.out.println();
    }
}
