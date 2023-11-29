import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_BOJ_1205_등수_구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        if(N == 0) {
            System.out.println(1);
            return;
        }

        int[] scores = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        if (N == P && scores[P - 1] >= newScore) {
            System.out.println(-1);
            return;
        }

        if (scores[0] == newScore) {
            System.out.println(1);
            return;
        }

        int rank = 1;
        for (int i = 0; i < N; i++) {
            if(scores[i] > newScore) rank += 1;
            else break;
        }

        System.out.println(rank);
    }
}
